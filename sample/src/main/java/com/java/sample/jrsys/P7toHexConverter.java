package com.java.sample.jrsys;

import org.apache.commons.codec.binary.Hex;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * ClassName: P7toHexConverter
 * Package: com.java.sample.jrsys
 * Description:
 *
 * @Author: Howard
 * @Create: 2024/12/25
 */
public class P7toHexConverter {
    public static void main(String[] args) {
        // 設定 .p7 檔案的路徑 (請修改為你的檔案路徑)
        String filePath = "D:\\0581.p7";

        try {
            // 1. 讀取 .p7 檔案為 byte 陣列
            byte[] fileBytes = readFileToBytes(filePath);

            // 2. 將 byte 陣列轉為 Hex 字串
            String hexOutput = Hex.encodeHexString(fileBytes);

            // 3. 輸出 Hex 結果
            System.out.println("Hex Output:");
            System.out.println(hexOutput);

        } catch (IOException e) {
            // 處理可能的 IO 錯誤
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    /**
     * 將檔案讀取為 byte 陣列
     *
     * @param filePath 文件的完整路徑
     * @return 檔案內容的 byte 陣列
     * @throws IOException 當文件讀取失敗時拋出
     */
    private static byte[] readFileToBytes(String filePath) throws IOException {
        File file = new File(filePath);
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] fileBytes = new byte[(int) file.length()];
            int readBytes = fis.read(fileBytes);
            if (readBytes != fileBytes.length) {
                throw new IOException("Unable to read the entire file.");
            }
            return fileBytes;
        }
    }

}
