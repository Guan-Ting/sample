package com.java.sample.pki.AESTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.springframework.util.StringUtils;

/**
 * 此執行程式用來產生aesKey iv 的加密檔案
 * AESEncryptor.KIV依config.proerties內容產生
 * @author Bernice KUO
 *
 */
class createaeskeytool {
	 private static Properties prop = new Properties();
	 static {
			prop = new Properties();
			try {

                File initialFile = new File("D:\\guantin_workspace\\sample\\sample\\src\\main\\java\\com\\java\\sample\\pki\\AESTest\\Config.properties");
				 InputStream is;
				is = new FileInputStream(initialFile);
				
	            prop.load(is);
	      
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		}
    public static void main(String[] args) throws Exception {
       
       
        try {
        	  long timestamp = Instant.now().getEpochSecond();
        	  String orgName=prop.getProperty("orgName") ;
            System.out.println("orgName:"+orgName);
              String aesKey = prop.getProperty("aesKey") ;
              String iv = prop.getProperty("iv") ;
              String aesKeyVerString = prop.getProperty("aesKeyVer") ;
              int aesKeyVer = Integer.parseInt(aesKeyVerString);
              String data = String.format("%s|%s|%s", aesKeyVer, aesKey, iv);
              File file = new File("AESEncryptor.KIV");
              System.out.println("orgName="+orgName);
              System.out.println("aesKey="+aesKey);
              System.out.println("iv="+iv);
              System.out.println("aesKeyVer="+aesKeyVerString);
              System.out.println("檔案產生位置="+file.getAbsolutePath());
              String keyData = String.format("%s|%s", timestamp, orgName);
              //part1 產生加密檔
              String keyString;
              
			  keyString = genKey(keyData);
			
              byte[] encryptedByteArray = xor(data.getBytes(StandardCharsets.UTF_8), keyString.getBytes(StandardCharsets.UTF_8));
              String encryptedString = String.format("%s|%s", keyString, Base64.encodeBase64URLSafeString(encryptedByteArray));
              FileUtils.writeStringToFile(file, encryptedString, StandardCharsets.UTF_8);
              
              
              
              //part2 用戶解密(以下程式參考用,非此程式主要執行功能)

              String text = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
              System.out.println("KIV檔案內容:"+text);
              String[] textSpit = StringUtils.split(text, "|");
              byte[] byteArray = xor(Base64.decodeBase64(textSpit[1]), textSpit[0].getBytes(StandardCharsets.UTF_8));
              String decryptedString = new String(byteArray, StandardCharsets.UTF_8);
              System.out.println("decryptedString:"+decryptedString);
              String[] textSpitdecryptedString = decryptedString.split( "\\|",3);

              System.out.println("用戶解密 aesKey="+textSpitdecryptedString[1]);
              System.out.println("用戶解密 iv="+textSpitdecryptedString[2]);
              System.out.println("用戶解密 orgName="+getKeyData(textSpit[0]));

        
              
              
        
         } catch (Exception ex) {
            throw ex;
        }
        
      
        
    }
    
    private static String genKey(String data) {
        int size = (int) Math.ceil(data.length() / 2.0);
        int[] intArray = new int[size];

        int idx = 0;
        for (int i = 0; i < data.length(); i += 2) {
        	int c1 = data.charAt(i);
        	int c2 = i + 1 < data.length() ? data.charAt(i + 1) : ' ';
        	int value = ((int) c1 << 8) | ((int) c2);
            intArray[idx] = value;
            idx++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i : intArray) {
            sb.append(i);
        }
        return sb.toString();
    }

    private static String getKeyData(String keyString) {
        int chunkSize = 5;
        int[] intArray = new int[keyString.length() % chunkSize == 0 ? keyString.length() / chunkSize : keyString.length() / chunkSize + 1];

        for (int i = 0; i < keyString.length(); i += chunkSize) {
            int j = Math.min(i + chunkSize, keyString.length());
            intArray[i / chunkSize] = Integer.parseInt(keyString.substring(i, j));
        }

        StringBuilder sb = new StringBuilder();
        for (int value : intArray) {
        	char c1 = (char) ((value >> 8) & 0xff);
        	char c2 = (char) (value & 0xff);
            sb.append(c1);
            if (c2 != ' ') {
                sb.append(c2);
            }
        }
        return sb.toString();
    }

    private static byte[] xor(byte[] data, byte[] key) {
        if (data == null || data.length == 0 || key == null || key.length == 0) {
            throw new IllegalArgumentException();
        }
        byte[] result = new byte[data.length];
        for (int i = 0; i < data.length; i++) {
            int keyIndex = (i) % key.length;
            result[i] = (byte) (0xff & ((int) data[i]) ^ ((int) key[keyIndex]));
        }
        return result;
    }
}