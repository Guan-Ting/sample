package com.java.sample.pki.AESTest;


import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.paddings.PKCS7Padding;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class CryptoUtil {
    public static byte[] pkcs7Padding(byte[] unpadded, int paddingLength) {
        PKCS7Padding padding = new PKCS7Padding();

        byte[] padded = new byte[unpadded.length + (paddingLength - unpadded.length % paddingLength)];
        System.arraycopy(unpadded, 0, padded, 0, unpadded.length);
        padding.addPadding(padded, unpadded.length);

        return padded;
    }


    public static byte[] pkcs7DePadding(byte[] padded) throws InvalidCipherTextException {
        PKCS7Padding padding = new PKCS7Padding();
        int padcount = padding.padCount(padded);
        byte[] unpadded = new byte[padded.length - padcount];
        System.arraycopy(padded, 0, unpadded, 0, unpadded.length);

        return unpadded;
    }

    public static SecretKey convertToAESSecretKey(byte[] keyEncoded, KeyAlgorithm keyAlg) {
        SecretKey secretKey = new SecretKeySpec(keyEncoded, 0, keyEncoded.length, keyAlg.toString());  //keyAlg should be 'AES'
        return secretKey;
    }

    public static PublicKey convertToPublicKey(byte[] publicKeyEncoded, KeyAlgorithm keyAlg) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PublicKey publicKey =
                KeyFactory.getInstance(keyAlg.toString()).generatePublic(new X509EncodedKeySpec(publicKeyEncoded));

        return publicKey;
    }

    public static PrivateKey convertToPrivateKey(byte[] privateKeyEncoded, KeyAlgorithm keyAlg) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PrivateKey privateKey = KeyFactory.getInstance(keyAlg.toString()).generatePrivate(new PKCS8EncodedKeySpec(privateKeyEncoded));

        return privateKey;
    }
}
