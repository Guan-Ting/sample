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

    public static byte[] charToByte(CharSequence s) {
        int nChars = s.length();

        if (nChars % 2 != 0) {
            throw new IllegalArgumentException("Hex-encoded string must have an even number of characters");
        }

        byte[] result = new byte[nChars / 2];

        for (int i = 0; i < nChars; i += 2) {
            int msb = Character.digit(s.charAt(i), 16);
            int lsb = Character.digit(s.charAt(i+1), 16);

            if (msb < 0 || lsb < 0) {
                throw new IllegalArgumentException("Non-hex character in input: " + s);
            }
            result[i / 2] = (byte) ((msb << 4) | lsb);
        }
        return result;
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
        String input = "0988169550";
//        String iv="556b58703272357538782f413f442847";
//        String aesKey="59703373367639792442264529482b4d6251655468576d5a7134743777217a25";
        String algorithm = "AES/CBC/PKCS5Padding";
        //hex byte[]
        String iv="556B58703272357538782F413F442847";
        String aesKey="59703373367639792442264529482B4D6251655468576D5A7134743777217A25";



        byte[] ivByte = TestGenerateAESkey.charToByte(iv);
        byte[] aesKeyRaw=TestGenerateAESkey.charToByte(aesKey);
        SecretKey secretKey = CryptoUtil.convertToAESSecretKey(aesKeyRaw, KeyAlgorithm.AES);
        IvParameterSpec AesIv=new IvParameterSpec(ivByte);
        String encryptString=TestGenerateAESkey.encrypt(algorithm,input,secretKey,AesIv);
        System.out.println("decryptString:"+encryptString);
        System.out.println("encryptString.equals(plainTest):"+encryptString.equals("ZKxCOEY+HA7OmBBkbT/itA=="));


        String decryptString = TestGenerateAESkey.decrypt(algorithm, encryptString, secretKey, AesIv);
        System.out.println("decryptString:"+decryptString);
        System.out.println("input.equals(plainTest):"+input.equals(decryptString));
        System.out.println("input.equals(plainTest):"+input.equals(decryptString));

    }

    //更新139測試機
    @Test
    public void  testsqldata()  throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException,
            BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException{
        String input = "margaret@qware.com.tw";
//        String iv="556b58703272357538782f413f442847";
//        String aesKey="59703373367639792442264529482b4d6251655468576d5a7134743777217a25";
        String algorithm = "AES/CBC/PKCS5Padding";
        //hex byte[]
        String iv="556B58703272357538782F413F442847";
        String aesKey="59703373367639792442264529482B4D6251655468576D5A7134743777217A25";



        byte[] ivByte = TestGenerateAESkey.charToByte(iv);
        byte[] aesKeyRaw=TestGenerateAESkey.charToByte(aesKey);
        SecretKey secretKey = CryptoUtil.convertToAESSecretKey(aesKeyRaw, KeyAlgorithm.AES);
        IvParameterSpec AesIv=new IvParameterSpec(ivByte);
        String encryptString=TestGenerateAESkey.encrypt(algorithm,input,secretKey,AesIv);
        System.out.println("decryptString:"+encryptString);
        System.out.println("encryptString.equals(plainTest):"+encryptString.equals("/hD5e21wJw+IgvR1hvlP3A=="));


        String decryptString = TestGenerateAESkey.decrypt(algorithm, encryptString, secretKey, AesIv);
        System.out.println("decryptString:"+decryptString);
        System.out.println("input.equals(plainTest):"+input.equals(decryptString));
        System.out.println("input.equals(plainTest):"+input.equals(decryptString));

    }
}
