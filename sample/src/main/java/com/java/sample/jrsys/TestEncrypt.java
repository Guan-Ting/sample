package com.java.sample.jrsys;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.iv.RandomIvGenerator;
import org.jasypt.salt.RandomSaltGenerator;
import org.jasypt.util.text.StrongTextEncryptor;

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

        String a="$enc:PZ37mR9UvBR0TugUm2Hn+BsCpzbhxqn9";
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
        StrongTextEncryptor encryptor = new StrongTextEncryptor();
        encryptor.setPasswordCharArray("TVVZJK2qadaDF^".toCharArray());
        return  encryptor.encrypt(data);


    }

    public static String decryptRaw(String data) {
        StrongTextEncryptor encryptor = new StrongTextEncryptor();
        encryptor.setPasswordCharArray("TVVZJK2qadaDF^".toCharArray());
        return  encryptor.decrypt(data);
    }


}
