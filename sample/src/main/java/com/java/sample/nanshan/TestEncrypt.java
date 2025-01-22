package com.java.sample.nanshan;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.iv.ByteArrayFixedIvGenerator;
import org.jasypt.iv.FixedIvGenerator;
import org.jasypt.iv.IvGenerator;
import org.jasypt.iv.RandomIvGenerator;
import org.jasypt.salt.RandomSaltGenerator;

import javax.crypto.spec.IvParameterSpec;

/**
 * ClassName: TestEncrypt
 * Package: com.java.sample.nanshan
 * Description:
 *
 * @Author: Howard
 * @Create: 2024/9/18
 */
public class TestEncrypt {

    public static void main(String[] args) {

        String rawString= "P@ssw0rd99";
        TestEncrypt testEncrypt =new TestEncrypt();
        String encryptString =testEncrypt.encrypt(rawString);

        System.out.println("encryptString:"+encryptString);
        //String encryptString = "$enc:GM42NWtEhXjicGrjvAmjhXi1JbmyBP7OfE8BLNU4MD+q2XKdbiOUbIMNCyttADrs";
        System.out.println(testEncrypt.decrypt(encryptString));

        String a="$enc:mtxDw/R5vHACY3qSXjJkZWjsU4824odtT8DCAnJQyCBh8qKwL1hwH7CEr09Ip8f4";
        System.out.println("decrypt:"+testEncrypt.decrypt(a));
    }


    public  String encrypt(String data) {
        return  "$enc:"+encryptRaw(data);
    }
    public  String decrypt(String data) {
        if (data==null)
            return null;
        if (data.startsWith("$enc:"))
            return decryptRaw(data.substring("$enc:".length()));
        else
            return data;
    }

    public  String encryptRaw(String data) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setAlgorithm("PBEWithHmacSHA512AndAES_256");
        encryptor.setPassword("jC40J98LigfGsAEV");
        RandomIvGenerator randomIvGenerator =new RandomIvGenerator();

//        byte[] fixedIv = new byte[]{0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F};
//        IvGenerator  generator = new ByteArrayFixedIvGenerator(fixedIv);
//        encryptor.setIvGenerator(generator);
          encryptor.setIvGenerator(randomIvGenerator);
        return  encryptor.encrypt(data);

    }

    public  String decryptRaw(String data) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setAlgorithm("PBEWithHmacSHA512AndAES_256");
        encryptor.setPassword("jC40J98LigfGsAEV");

//        byte[] fixedIv = new byte[]{0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F};
//        IvGenerator  generator = new ByteArrayFixedIvGenerator(fixedIv);
//        encryptor.setIvGenerator(generator);
        RandomIvGenerator randomIvGenerator =new RandomIvGenerator();
        RandomSaltGenerator randomSaltGenerator=new RandomSaltGenerator();
        encryptor.setIvGenerator(randomIvGenerator);
        encryptor.setSaltGenerator(randomSaltGenerator);
        return  encryptor.decrypt(data);
    }

}
