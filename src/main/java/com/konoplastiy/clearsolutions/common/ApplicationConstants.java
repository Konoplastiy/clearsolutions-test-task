package com.konoplastiy.clearsolutions.common;

import lombok.experimental.UtilityClass;

import java.util.regex.Pattern;

/**
 * Contains various constants used in the application.
 */
@UtilityClass
public class ApplicationConstants {

    /**
     * Inner utility class for validation-related constants.
     */
    @UtilityClass
    public class Validation {
        /**
         * A regular expression for validating email addresses.
         */
        public static final String EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?``{|}~^.-]+@[a-zA-Z0-9.-]+$";
        public static final Pattern EMAIL_PATTERN = Pattern.compile(Validation.EMAIL_REGEX);
    }

    /**
     * Inner utility class for defining test data constants.
     */
    @UtilityClass
    public class UserTestData {
        public static final String USER_TEST_EMAIL = "user@example.com";
        public static final String USER_TEST_FIRSTNAME = "John";
        public static final String USER_TEST_LASTNAME = "Doe";
        public static final String USER_TEST_ADDRESS = "123 Main St";
        public static final String USER_TEST_PHONE = "+1234567890";
    }
}