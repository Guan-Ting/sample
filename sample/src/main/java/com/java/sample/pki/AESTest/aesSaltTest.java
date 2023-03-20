package com.java.sample.pki.AESTest;


import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.salt.StringFixedSaltGenerator;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class aesSaltTest {
    public static void main(String[] args) {
        String PASS_WORD="password";
        StringFixedSaltGenerator saltGenerator = new StringFixedSaltGenerator("12345678901234567890123456789011");
        byte[] salt=saltGenerator.generateSalt(32);
        String saltBase64 = new String(Base64.getEncoder().encode(salt));
        System.out.println(saltBase64+" "+"length:"+salt.length);

        PooledPBEStringEncryptor encryptor1=new PooledPBEStringEncryptor();
        encryptor1.setAlgorithm("PBEWithHmacSHA256AndAES_256");
        encryptor1.setSaltGenerator(saltGenerator);
        encryptor1.setPassword(PASS_WORD);
        encryptor1.setPoolSize(10);
        String message = "Hello, world!";
        String encryptedMessage = encryptor1.encrypt(message);
        System.out.println("Encrypted message: " + encryptedMessage);

        StringFixedSaltGenerator saltGenerator2 = new StringFixedSaltGenerator("12345678901234567890123456789011");
        byte[] salt2=saltGenerator2.generateSalt(32);
        String encodearr2=Base64.getEncoder().encodeToString(salt2);
        System.out.println(encodearr2+" "+"length:"+salt.length);

        PooledPBEStringEncryptor encryptor2=new PooledPBEStringEncryptor();
        encryptor2.setAlgorithm("PBEWithHmacSHA256AndAES_256");
        encryptor2.setSaltGenerator(saltGenerator2);
        encryptor2.setPassword(PASS_WORD);
        encryptor2.setPoolSize(10);
        String decrptMessage=encryptor2.decrypt(encryptedMessage);
        System.out.println("Decrypt meaage:"+decrptMessage);






    }


}
