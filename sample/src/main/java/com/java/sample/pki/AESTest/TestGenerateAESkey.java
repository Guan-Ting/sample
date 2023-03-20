package com.java.sample.pki.AESTest;


import org.junit.Test;


import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

public class TestGenerateAESkey {

    public static IvParameterSpec genIV(){
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
    }

    public static String encrypt(String algorithm, String input, SecretKey key,
                                 IvParameterSpec iv) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        byte[] cipherText = cipher.doFinal(input.getBytes());
        return Base64.getEncoder()
                .encodeToString(cipherText);
    }
    public static String decrypt(String algorithm, String cipherText, SecretKey key,
                                 IvParameterSpec iv) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        byte[] plainText = cipher.doFinal(Base64.getDecoder()
                .decode(cipherText));
        return new String(plainText);
    }

    public static SecretKey generateKey(int n) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(n);
        SecretKey key = keyGenerator.generateKey();
        return key;
    }

    @Test
    public void  testAesKey()  throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException,
            BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException{
        String input = "baeldung";
        SecretKey key = TestGenerateAESkey.generateKey(256);
        String keyString = javax.xml.bind.DatatypeConverter.printHexBinary(key.getEncoded());
        System.out.println("keyString:"+keyString);

        byte[] aesKey=key.getEncoded();
        System.out.println("keyByte"+Arrays.toString(aesKey));
        String keyStr=new String(Base64.getEncoder().encodeToString(aesKey));
        System.out.println("keyStr:"+keyStr);


        IvParameterSpec ivParameterSpec = TestGenerateAESkey.genIV();
        String IVstr=new String(Base64.getEncoder().encodeToString(ivParameterSpec.getIV()));
        System.out.println("iv:"+IVstr);//NxntZ0tsArs+0dZCXngDbA==
        byte[] iv=Base64.getDecoder().decode(IVstr);

        String hexIV = javax.xml.bind.DatatypeConverter.printHexBinary(ivParameterSpec.getIV());
        System.out.println("hexIV:"+hexIV);
        IvParameterSpec newIvParameterSpec=new IvParameterSpec(iv);

        String algorithm = "AES/CBC/PKCS5Padding";
        String cipherText = TestGenerateAESkey.encrypt(algorithm, input, key, newIvParameterSpec);
        System.out.println("cipherText:"+cipherText);//KXXnEQomYUb5NH2Z1z0ggw==
        String plainText = TestGenerateAESkey.decrypt(algorithm, cipherText, key, ivParameterSpec);
        System.out.println("input.equals(plainTest):"+input.equals(plainText));
    }

    @Test
    public void  testAesKey2()  throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException,
            BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException{
        String input = "baeldung";
        SecretKey key = TestGenerateAESkey.generateKey(256);
        byte[] aesKey=key.getEncoded();
        System.out.println("keyByte"+Arrays.toString(aesKey));
        String keyStr=new String(Base64.getEncoder().encodeToString(aesKey));
        System.out.println("keyStr:"+keyStr);


        IvParameterSpec ivParameterSpec = TestGenerateAESkey.genIV();
        String IVstr=new String(Base64.getEncoder().encodeToString(ivParameterSpec.getIV()));
        System.out.println("iv:"+IVstr);//NxntZ0tsArs+0dZCXngDbA==
        byte[] iv=Base64.getDecoder().decode(IVstr);
        IvParameterSpec newIvParameterSpec=new IvParameterSpec(iv);

        String algorithm = "AES/CBC/PKCS5Padding";
        String cipherText = TestGenerateAESkey.encrypt(algorithm, input, key, newIvParameterSpec);
        System.out.println("cipherText:"+cipherText);//KXXnEQomYUb5NH2Z1z0ggw==
        String plainText = TestGenerateAESkey.decrypt(algorithm, cipherText, key, ivParameterSpec);
        System.out.println("input.equals(plainTest):"+input.equals(plainText));
    }
}
