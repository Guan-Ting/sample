package com.java.sample.pki.javaAuth;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

public class PasswordGenerator {

    private static PasswordGenerator instance;

    public static PasswordGenerator getInstance(){
        if (null == instance){
            instance=new PasswordGenerator();
        }
        return instance;
    }
    private Map<Character,String> charMap;

    private PasswordGenerator() {
        initCharMap();
    }

    private void initCharMap(){
        charMap = new HashMap<Character, String>();

        charMap.put('a', "aA4");
        charMap.put('b', "bB");
        charMap.put('c', "cC");
        charMap.put('d', "dD");
        charMap.put('e', "eE3");
        charMap.put('f', "fF");
        charMap.put('g', "gG9");
        charMap.put('h', "hH");
        charMap.put('i', "iIl");
        charMap.put('j', "jJ");
        charMap.put('k', "kK");
        charMap.put('l', "lLI");
        charMap.put('m', "mM");
        charMap.put('n', "nN");
        charMap.put('o', "oO0");
        charMap.put('p', "pP");
        charMap.put('q', "qQ");
        charMap.put('r', "rR");
        charMap.put('s', "sS");
        charMap.put('t', "tT");
        charMap.put('u', "uU");
        charMap.put('v', "vV");
        charMap.put('w', "wW");
        charMap.put('x', "xX");
        charMap.put('y', "yY");
        charMap.put('z', "zZ");
        charMap.put('1', "1!");
        charMap.put('2', "2@");
        charMap.put('3', "3#E");
        charMap.put('4', "4$A");
        charMap.put('5', "5%");
        charMap.put('6', "6^");
        charMap.put('7', "7&");
        charMap.put('8', "8*");
        charMap.put('9', "9(");
        charMap.put('0', "0)");
        charMap.put('-', "-_");
        charMap.put('=', "=+");
        charMap.put('[', "[{");
        charMap.put(']', "]}");
        charMap.put(',', ",<");
        charMap.put('.', ".>");
        charMap.put('/', "/?");
        charMap.put('`', "`~");
        charMap.put('\'', "\'\"");
        charMap.put(' ', " ");
    }

    public String generatePasswordFromText(String origText){
        String trimed=trimOrigTest(origText);
        char[] origChars=trimed.toCharArray();
        String generated="";
        do {
            generated=generatedPassWord(origChars);

        }while(trimed.equals(generated));
        return generated;
    }
    private String trimOrigTest(String origText) {
        return null == origText ? "" : origText.trim().toLowerCase();
    }
    private String generatedPassWord(char[] origChars){
        if ( 0 == origChars.length){
            return "";
        }
        StringBuffer passwd=new StringBuffer();
        for(int i =0; i<origChars.length;i++){
            passwd.append(pickChar(origChars[i]));
        }
        return passwd.toString();
    }
    private char pickChar(char origChar){
        //宣告為''會導致empty character literal
        if (origChar < 32){
            return Character.MIN_VALUE;
        }
        String charBase = charMap.get(origChar);
        int rnd =random(charBase.length());
        return charBase.charAt(rnd);
    }

    private int random (int charBaseLength){
        try{
            //僅適用於windows
            return SecureRandom.getInstance("SHA1PRNG").nextInt(charBaseLength);
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace(System.err);
            return 0 ;
        }
    }
}
