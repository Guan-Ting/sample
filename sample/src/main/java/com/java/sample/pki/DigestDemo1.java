//package com.java.sample.pki;
//
//
//import com.sun.org.apache.xml.internal.security.utils.Base64;
//
//import java.nio.charset.StandardCharsets;
//import java.security.MessageDigest;
//
//public class DigestDemo1 {
//    public static void main(String[] args)  throws Exception{
//        //原文
//        String input ="aa";
//
//        //算法
//        String algorithm="MD5";
//        //獲取數字摘要對象
//        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
//
//        byte[] digest =messageDigest.digest(input.getBytes(StandardCharsets.UTF_8));
//        //需要進行base64編碼 不然會輸出亂碼
//        System.out.println(Base64.encode(digest));
//
//
//    }
//}
