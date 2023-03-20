package com.java.sample.pki.AESTest;



import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.jasypt.salt.FixedSaltGenerator;
import org.jasypt.salt.RandomSaltGenerator;
import org.jasypt.salt.StringFixedSaltGenerator;


import java.util.Base64;

public class aesSaltTest2 {
    private static final String PASSWORD = "mySecretPassword";
    public static void main(String[] args) {

        String password = "myPassword";
        String message = "Hello, world!";

        // 產生固定的 salt 值
        StringFixedSaltGenerator saltGenerator = new StringFixedSaltGenerator("12345678901234567890123456789011");
        byte[] salt=saltGenerator.generateSalt(32);
        String saltBase64 = new String(Base64.getEncoder().encode(salt));
        System.out.println(saltBase64+" "+"length:"+salt.length);

        // 產生唯一的 iv 值


        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        encryptor.setConfig(cryptor(password));
        encryptor.setSaltGenerator(saltGenerator);

        // 加密
        String encryptedMessage = encryptor.encrypt(message);
        System.out.println("Encrypted message: " + encryptedMessage);

        // 解密

        PooledPBEStringEncryptor encryptor2 = new PooledPBEStringEncryptor();
        encryptor2.setConfig(cryptor(password));
        StringFixedSaltGenerator saltGenerator2 = new StringFixedSaltGenerator("12345678901234567890123456789011");
        byte[] salt2=saltGenerator.generateSalt(32);
        String saltBase642 = new String(Base64.getEncoder().encode(salt));
        System.out.println(saltBase642+" "+"length:"+salt.length);

        encryptor2.setSaltGenerator(saltGenerator2);


        String decryptedMessage = encryptor2.decrypt(encryptedMessage);
        System.out.println("Decrypted message: " + decryptedMessage);
    }

    public static  SimpleStringPBEConfig cryptor(String password){
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(password);
        config.setAlgorithm("PBEWithHmacSHA256AndAES_256");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize(10);
        config.setProviderName("SunJCE");
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        config.setStringOutputType("base64");
        return config;
    }
}
