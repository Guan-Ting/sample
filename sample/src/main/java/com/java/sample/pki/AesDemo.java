package com.java.sample.pki;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;


public class AesDemo {

    public static void main(String[] args) throws Exception {
        String input ="微軟";

        String key = "1234567812345678";

        String transformation = "AES";

        String algorithm ="AES";

        String encryptAES = encryptAES(input,key,transformation,algorithm);
        System.out.println("加密:" + encryptAES);
        String s = decryptAES(encryptAES, key,transformation, algorithm);
        System.out.println("解密:" + s);
    }

    private static String encryptAES (String input , String key , String transformation,
                                            String algorithm)throws Exception {
        //獲取加密物件
        Cipher cipher =Cipher.getInstance(transformation);

        //建立加密規則
        //第一個引數key位元組
        //第二個引數表示加密演算法
        SecretKeySpec secretKeySpec=new SecretKeySpec(key.getBytes(),algorithm);
        // ENCRYPT_MODE：加密模式
        // DECRYPT_MODE: 解密模式
        // 初始化加密模式和演算法

        cipher.init(Cipher.ENCRYPT_MODE,secretKeySpec);
        //加密

        byte[] bytes = cipher.doFinal(input.getBytes());
        // 輸出加密後的資料
        String encode =new String (org.apache.commons.codec.binary.Base64.encodeBase64(bytes));
        System.out.println("------------------------------------");
        System.out.println("encode:"+encode);

        return encode;
    }

    //記得研究模板
    private static String decryptAES(String input,String key,String transformation,String algorithm) throws Exception{
        //１.獲取cipher物件
        Cipher cipher = Cipher.getInstance(transformation);
        //指定金鑰
        SecretKeySpec secretKeySpec=new SecretKeySpec(key.getBytes(),algorithm);
        cipher.init(Cipher.DECRYPT_MODE,secretKeySpec);
        byte[] bytes =cipher.doFinal(org.apache.commons.codec.binary.Base64.decodeBase64(input));

        return new String(bytes);


    }

}
