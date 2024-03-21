package com.pragma.onclass.domain;

public class Constants {
    private Constants(){
        throw new IllegalStateException("utility class");
    }

    public static final String TECHNOLOGY_ALREADY_EXISTS_EXCEPTION_MESSAGE = "The technology you want to create already exists.";
    public static final String CAPABILITY_VALIDATIONS_EXCEPTION_MESSAGE = "The capacity must be a minimum of 3 and a maximum of 20 associated technologies. Technologies should not be repeated.";
}
