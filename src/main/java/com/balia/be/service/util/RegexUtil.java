package com.balia.be.service.util;

import java.util.regex.Pattern;

public class RegexUtil {

    private static final String STRICT_EMAIL_REGEX = "^(?=.{1,256}$)(?=.{1,64}@.{1,255}$)([A-Za-z0-9_.%+-]+)@([A-Za-z0-9.-]+)\\.([A-Za-z]{2,})$";

    public static boolean isValidEmail(String email) {
        return Pattern.matches(STRICT_EMAIL_REGEX, email);
    }
}
