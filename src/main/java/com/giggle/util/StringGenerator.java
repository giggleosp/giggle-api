package com.giggle.util;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by enda on 06/03/16.
 */
//http://stackoverflow.com/questions/41107
///how-to-generate-a-random-alpha-numeric-string
public class StringGenerator {
    private static SecureRandom random = new SecureRandom();

    public static String generateString() {
        return new BigInteger(130, random).toString(32);
    }
}
