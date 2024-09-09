package com.java.sample.pki.Cryptography;

import java.io.UnsupportedEncodingException;
import java.security.*;

public class TestSignature {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, SignatureException {
        SecureRandom secureRandom=new SecureRandom();
        KeyPairGenerator keyPairGenerator=KeyPairGenerator.getInstance("RSA");
        KeyPair keypair =keyPairGenerator.generateKeyPair();

        Signature signature =Signature.getInstance("SHA256WithRSA");
        //私鑰簽章
        signature.initSign(keypair.getPrivate(),secureRandom);

        byte[] data = "abcdefghijklmnopqrstuvxyz".getBytes("UTF-8");
        signature.update(data);
        byte[] digitalSignature =signature.sign();

        Signature signature2 = Signature.getInstance("SHA256WithRSA");
        //公鑰去驗章
        signature2.initVerify(keypair.getPublic());
        signature2.update(data);
        boolean verified =signature2.verify(digitalSignature);
        System.out.println("verify:"+verified);


    }
}
