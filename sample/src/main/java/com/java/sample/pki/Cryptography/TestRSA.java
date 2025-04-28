package com.java.sample.pki.Cryptography;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

/**
 * ClassName: TestRSA
 * Package: com.java.sample.pki.Cryptography
 * Description:
 */
public class TestRSA {

    public static void main(String[] args) throws Exception {
        // 原始明文
        String originalText = "這是測試訊息";
        System.out.println("原始明文: " + originalText);

        // 1. 生成 RSA 金鑰對
        SecureRandom secureRandom = new SecureRandom();
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048, secureRandom);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

//        // 2. 公鑰加密
//        Cipher encryptCipher = Cipher.getInstance("RSA");
//        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
//        byte[] encryptedBytes = encryptCipher.doFinal(originalText.getBytes(StandardCharsets.UTF_8));
//        String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
//        System.out.println("加密後: " + encryptedText);
//
//        // 3. 私鑰解密
//        Cipher decryptCipher = Cipher.getInstance("RSA");
//        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
//        byte[] decryptedBytes = decryptCipher.doFinal(Base64.getDecoder().decode(encryptedText));
//        String decryptedText = new String(decryptedBytes, StandardCharsets.UTF_8);
//        System.out.println("解密後: " + decryptedText);


        // 4. 私鑰加密
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] encryptedBytes = encryptCipher.doFinal(originalText.getBytes(StandardCharsets.UTF_8));
        String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
        System.out.println("加密後: " + encryptedText);

        // 5. 公鑰解密
        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] decryptedBytes = decryptCipher.doFinal(Base64.getDecoder().decode(encryptedText));
        String decryptedText = new String(decryptedBytes, StandardCharsets.UTF_8);
        System.out.println("解密後: " + decryptedText);




    }

}
