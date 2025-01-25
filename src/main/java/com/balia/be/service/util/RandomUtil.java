package com.balia.be.service.util;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Utility class for generating random Strings.
 */
public final class RandomUtil {

    private static final int DEF_COUNT = 20;

    private RandomUtil() {
    }

    /**
     * Generate a password.
     *
     * @return the generated password
     */
    public static String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(DEF_COUNT);
    }

    /**
     * Generate an activation key.
     *
     * @return the generated activation key
     */
    public static String generateActivationKey() {
        return RandomStringUtils.randomNumeric(DEF_COUNT);
    }

    /**
    * Generate a reset key.
    *
    * @return the generated reset key
    */
    public static String generateResetKey() {
        return RandomStringUtils.randomNumeric(DEF_COUNT);
    }


    public static String genText() {
        return RandomStringUtils.random(16, true, true);
    }

    public static String genUuid() {
        String uuid = RandomStringUtils.random(8, true, true) + "-" + RandomStringUtils.random(4, true, true) + "-"
                + RandomStringUtils.random(4, true, true) + "-" + RandomStringUtils.random(4, true, true) + "-"
                + RandomStringUtils.random(12, true, true);
        return uuid;
    }

    public static String gentracer() {
        String uuid = RandomStringUtils.random(8, true, true) + "-" + RandomStringUtils.random(4, true, true) + "-"
                + RandomStringUtils.random(4, true, true) + "-" + RandomStringUtils.random(4, true, true) + "-"
                + RandomStringUtils.random(12, true, true);
        return uuid;
    }

    public static String genfopkey() {
        String uuid = RandomStringUtils.random(10, true, true) + "-" + RandomStringUtils.random(10, true, true) + "-"
                + RandomStringUtils.random(8, true, true) + "-" + RandomStringUtils.random(4, true, true);
        return uuid;
    }
}
