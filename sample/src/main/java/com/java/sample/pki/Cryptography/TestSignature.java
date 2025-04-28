package com.java.sample.pki.Cryptography;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

public class TestSignature {

    public static void main(String[] args) {
        try {
            // 1. 初始化安全隨機數生成器
            SecureRandom secureRandom = new SecureRandom();

            // 2. 建立 RSA 金鑰對產生器
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            // 設定金鑰長度為 2048 位元，增強安全性
            keyPairGenerator.initialize(2048, secureRandom);
            // 產生一對公鑰和私鑰
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            System.out.println("已生成 RSA 金鑰對");

            // 3. 原始資料（待簽署的訊息）
            String originalMessage = "Hello, Digital Signature World!";
            byte[] dataBytes = originalMessage.getBytes(StandardCharsets.UTF_8);
            System.out.println("原始訊息: " + originalMessage);

            // 4. 使用私鑰創建數位簽章
            byte[] signatureBytes = createDigitalSignature(dataBytes, keyPair.getPrivate(), secureRandom);
            String signatureBase64 = Base64.getEncoder().encodeToString(signatureBytes);
            System.out.println("數位簽章 (Base64): " + signatureBase64);

            // 5. 使用公鑰驗證數位簽章
            boolean isValid = verifyDigitalSignature(dataBytes, signatureBytes, keyPair.getPublic());
            System.out.println("驗證結果: " + (isValid ? "簽章有效 ✓" : "簽章無效 ✗"));

            // 6. 嘗試驗證被篡改的資料
            byte[] tamperedData = ("Hello, Modified " + originalMessage).getBytes(StandardCharsets.UTF_8);
            boolean isTamperedValid = verifyDigitalSignature(tamperedData, signatureBytes, keyPair.getPublic());
            System.out.println("篡改資料驗證: " + (isTamperedValid ? "簽章有效 ✓" : "簽章無效 ✗"));

        } catch (NoSuchAlgorithmException e) {
            System.err.println("找不到指定的演算法: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("發生錯誤: " + e.getMessage());
            e.printStackTrace();
        }

    }

    /**
     * 使用私鑰創建數位簽章
     *
     * @param data 要簽署的資料
     * @param privateKey 私鑰
     * @param random 隨機數生成器
     * @return 簽章的位元組陣列
     * @throws NoSuchAlgorithmException 找不到指定的演算法
     * @throws InvalidKeyException 金鑰無效
     * @throws SignatureException 簽章操作失敗
     */
    private static byte[] createDigitalSignature(byte[] data, PrivateKey privateKey, SecureRandom random)
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {

        // 創建 SHA-256 與 RSA 的簽章物件
        Signature signature = Signature.getInstance("SHA256withRSA");

        // 使用私鑰初始化簽章物件進行簽署操作
        signature.initSign(privateKey, random);

        // 提供要簽署的資料
        signature.update(data);

        // 生成簽章並返回
        return signature.sign();
    }

    /**
     * 使用公鑰驗證數位簽章
     *
     * @param data 原始資料
     * @param signatureBytes 數位簽章
     * @param publicKey 公鑰
     * @return 如果簽章有效則返回 true，否則返回 false
     * @throws NoSuchAlgorithmException 找不到指定的演算法
     * @throws InvalidKeyException 金鑰無效
     * @throws SignatureException 簽章操作失敗
     */
    private static boolean verifyDigitalSignature(byte[] data, byte[] signatureBytes, PublicKey publicKey)
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {

        // 創建 SHA-256 與 RSA 的簽章物件
        Signature signature = Signature.getInstance("SHA256withRSA");

        // 使用公鑰初始化簽章物件進行驗證操作
        signature.initVerify(publicKey);

        // 提供要驗證的原始資料
        signature.update(data);

        // 驗證簽章並返回結果
        return signature.verify(signatureBytes);
    }

}
