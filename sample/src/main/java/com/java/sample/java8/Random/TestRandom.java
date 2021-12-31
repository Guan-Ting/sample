package com.java.sample.java8.Random;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

/*
隨機數Random和SecureRandom基本用法
https://www.twblogs.net/a/5e51442bbd9eee21167f63d1
 */
public class TestRandom {
    private String generateTensDigitsecureRandom() throws NoSuchAlgorithmException {
        SecureRandom random= SecureRandom.getInstance("SHA1PRNG");
        int verifiCode = (int)Math.ceil(random.nextFloat()*1000000);
        String verifiCodeStr = String.valueOf(verifiCode);
        //處理產生的隨機數不及6位的情況
        while(verifiCodeStr.length()<6){
            verifiCode = (int)Math.ceil(random.nextFloat()*1000000);
            verifiCodeStr = String.valueOf(verifiCode);
        }
        return verifiCodeStr;
    }

    private String generateTensDigitMathRandom() {
        // 產生10位數亂數
        String stringresult = "";
        for (int i = 0; i < 10; i++) {
            int num = (int) (Math.random() * 9);
            stringresult = stringresult + (int) num;
        }
        return stringresult;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException
    {
        SecureRandom secureRandomGenerator = SecureRandom.getInstance("SHA1PRNG", "SUN");

        // Get 128 random bytes
        byte[] randomBytes = new byte[128];
        secureRandomGenerator.nextBytes(randomBytes);

        //Get random integer
        int r = secureRandomGenerator.nextInt();

        //Get random integer in range
        int randInRange = secureRandomGenerator.nextInt(999999999);
        int randInRange2 = secureRandomGenerator.nextInt(9);


        System.out.println("randInRange:"+randInRange+""+"randInRange2:"+randInRange2);
    }

}
