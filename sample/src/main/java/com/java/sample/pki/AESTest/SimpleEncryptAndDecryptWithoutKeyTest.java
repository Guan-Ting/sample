package com.java.sample.pki.AESTest;

import lombok.var;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;


import org.junit.Test;
import org.springframework.util.StringUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.time.Instant;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleEncryptAndDecryptWithoutKeyTest {

    @Test
    public void test1() {

            String input = "1|59703373367639792442264529482B4D6251655468576D5A7134743777217A25|556B58703272357538782F413F442847";
            String[] parts = input.split( "\\|",3);

            for (String part : parts) {
                System.out.println(part);
            }

    }


    @Test
    public void encryptAndDecrypt() {
        var timestamp = Instant.now().getEpochSecond();
        var orgName = "id.taipei";
        var keyData = String.format("%s|%s", timestamp, orgName);
        System.out.println("keyData:"+keyData);

        var aesKeyVer = 1;
        var aesKey = "51655468576d5a7134743777217a25432646294a404e635266556a586e327235";
        var iv = "4226452948404d6251655468576d5a71";
        var data = String.format("%s|%s|%s", aesKeyVer, aesKey, iv);

        System.out.println("data:"+data);
        var file = new File(FileUtils.getTempDirectory(), "test.dat");

        assertDoesNotThrow(() -> {
            //part1 產生加密檔
            var keyString = genKey(keyData);
            System.out.println("keyString:"+keyString);
            var encryptedByteArray = xor(data.getBytes(StandardCharsets.UTF_8), keyString.getBytes(StandardCharsets.UTF_8));
            var encryptedString = String.format("%s|%s", keyString, Base64.encodeBase64URLSafeString(encryptedByteArray));
            System.out.println("encryptedString:"+encryptedString);
            FileUtils.writeStringToFile(file, encryptedString, StandardCharsets.UTF_8);
            System.out.println("------------------用戶解密------------------");
            //part2 用戶解密
            var text = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
            System.out.println("text:"+text);
            var textSpit = StringUtils.split(text, "|");
            System.out.println("textSpit[1]:"+textSpit[1]);
            var byteArray = xor(Base64.decodeBase64(textSpit[1]), textSpit[0].getBytes(StandardCharsets.UTF_8));
            var decryptedString = new String(byteArray, StandardCharsets.UTF_8);
            //assertEquals(data, decryptedString);
            System.out.println("decryptedString:"+decryptedString);
            System.out.println("data decryptedString是否相同:"+data.equals(decryptedString));

            //part3 取得金鑰內容
            System.out.println("還原金鑰:"+getKeyData(textSpit[0]));
            System.out.println("keyData getKeyData(textSpit[0]) 是否相同:"+keyData.equals(getKeyData(textSpit[0])));
            //assertEquals(keyData, getKeyData(textSpit[0]));
        });
    }

    private String genKey(String data) {
        var size = (int) Math.ceil(data.length() / 2.0);
        var intArray = new int[size];

        var idx = 0;
        for (var i = 0; i < data.length(); i += 2) {
            var c1 = data.charAt(i);
            var c2 = i + 1 < data.length() ? data.charAt(i + 1) : ' ';
            var value = ((int) c1 << 8) | ((int) c2);
            intArray[idx] = value;
            idx++;
        }

        var sb = new StringBuilder();
        for (int i : intArray) {
            sb.append(i);
        }
        return sb.toString();
    }

    private String getKeyData(String keyString) {
        int chunkSize = 5;
        int[] intArray = new int[keyString.length() % chunkSize == 0 ? keyString.length() / chunkSize : keyString.length() / chunkSize + 1];

        for (int i = 0; i < keyString.length(); i += chunkSize) {
            int j = Math.min(i + chunkSize, keyString.length());
            intArray[i / chunkSize] = Integer.parseInt(keyString.substring(i, j));
        }

        var sb = new StringBuilder();
        for (int value : intArray) {
            var c1 = (char) ((value >> 8) & 0xff);
            var c2 = (char) (value & 0xff);
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
