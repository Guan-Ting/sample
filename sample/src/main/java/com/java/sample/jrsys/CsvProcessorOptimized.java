package com.java.sample.jrsys;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.GZIPInputStream;

/**
 * ClassName: CsvProcessorOptimized
 * Package: com.java.sample.jrsys
 * Description:
 *
 * @Author: Howard
 * @Create: 2025/1/23
 */
public class CsvProcessorOptimized {

    public static void main(String[] args) throws Exception {
        // 打 POST 請求，取得下載 URL
        String downloadUrl = sendPostRequest();

        // 不斷嘗試 GET 請求直到成功
        File downloadedGzipFile = downloadFile(downloadUrl);

        // 解壓縮 gzip 文件
        File decompressedCsvFile = decompressGzipFile(downloadedGzipFile);

        // 處理 CSV 文件
        processCsv(decompressedCsvFile);
    }

    private static String sendPostRequest() throws IOException {
        String apiUrl = "https://api.onesignal.com/players/csv_export?app_id=eb240ea7-4fee-4e8b-bddf-ed6c54738420";
        String requestBody = "{ \"extra_fields\": [\"external_user_id\", \"onesignal_id\"] }";

        // 建立連接
        HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Basic MTA4NTRiYmEtMGIzOC00MzY4LTkzNmYtNzg3YjI5NGFjNzMz");

        // 傳送 JSON Body
        try (OutputStream outputStream = connection.getOutputStream()) {
            outputStream.write(requestBody.getBytes());
        }

        // 取得 Response 並解析回傳的 URL
        if (connection.getResponseCode() == 200) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                // 假設回傳的是 JSON 格式：我們需要解析類似於 {"url": "http://example.com/myfile.csv.gz"}
                String jsonResponse = response.toString();
                System.out.println("Response: " + jsonResponse);
                return jsonResponse.replaceAll(".*\"csv_file_url\":\"([^\"]+)\".*", "$1");
            }
        } else {
            throw new RuntimeException("Failed to send POST request: HTTP " + connection.getResponseCode());
        }
    }

    private static File downloadFile(String fileUrl) throws InterruptedException, IOException {
        File outputFile = new File("downloaded_file.csv.gz");
        System.out.println(fileUrl + " -> " + outputFile.getAbsolutePath() + " ...");
        while (true) { // 不斷重試
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(fileUrl).openConnection();
                System.out.println(fileUrl + " -> " + connection.getURL() + " ...");
                connection.setRequestMethod("GET");

                if (connection.getResponseCode() == 200) {
                    System.out.println("Downloading file from: " + fileUrl);
                    // 下載文件
                    try (InputStream inputStream = connection.getInputStream();
                         FileOutputStream fileOutputStream = new FileOutputStream(outputFile)) {
                        byte[] buffer = new byte[8192];
                        int bytesRead;
                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            fileOutputStream.write(buffer, 0, bytesRead);
                        }
                    }
                    System.out.println("File downloaded successfully: " + outputFile.getAbsolutePath());
                    return outputFile;
                } else if (connection.getResponseCode() == 404) {
                    System.out.println("File not ready yet, waiting 1 minutes...");
                    Thread.sleep(  60 * 1000); // 等待 10 分鐘
                } else {
                    throw new RuntimeException("Failed to GET file: HTTP " + connection.getResponseCode());
                }
            } catch (IOException e) {
                System.out.println("Error downloading file, retrying in 1 minutes...");
                Thread.sleep( 60 * 1000); // 等待 1 分鐘後重試
            }
        }
    }

    private static File decompressGzipFile(File gzipFile) throws IOException {
        File decompressedFile = new File("decompressed_file.csv");

        try (GZIPInputStream gzipInputStream = new GZIPInputStream(new FileInputStream(gzipFile));
             FileOutputStream fileOutputStream = new FileOutputStream(decompressedFile)) {
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = gzipInputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
            }
        }

        System.out.println("File decompressed successfully: " + decompressedFile.getAbsolutePath());
        return decompressedFile;
    }

    private static void processCsv(File csvFile) throws IOException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFile.getAbsolutePath()));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

            for (CSVRecord record : csvParser) {
                String externalId = record.get("external_user_id"); // 提取 external_user_id 欄位
                String onesignalId = record.get("onesignal_id"); // 提取 onesignal_id 欄位

                if (externalId == null || externalId.trim().isEmpty()) {
                    System.out.println("Onesignal ID: " + onesignalId);
                }
            }
        }

        System.out.println("CSV processing completed.");
    }


}
