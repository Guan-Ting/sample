package com.java.sample.pki.javaAuth;

import org.junit.Assert;
import org.junit.Test;

public class PasswordGeneratorTest {

    private static final String TEST_TEXT="guessGiveUp";

    @Test
    public void testPasswordGenerator() throws Exception{
        PasswordGenerator pwdGen=PasswordGenerator.getInstance();

        String password=pwdGen.generatePasswordFromText(TEST_TEXT);

        Assert.assertNotEquals(TEST_TEXT, password);

        System.out.println("generated password :" +password);

    }
}
