package com.pragma.onclass.domain;

public class Constants {
    private Constants(){
        throw new IllegalStateException("utility class");
    }

    public static final String TECHNOLOGY_ALREADY_EXISTS_EXCEPTION_MESSAGE = "The technology you want to create already exists.";
    public static final String CAPABILITY_REPEATED_VALIDATIONS_EXCEPTION_MESSAGE = "Technologies should not be repeated.";
    public static final String CAPABILITY_VALIDATIONS_EXCEPTION_MESSAGE = "The capacity must be a minimum of 3 and a maximum of 20 associated technologies.";
    public static final String BOOTCAMP_VALIDATIONS_EXCEPTION_MESSAGE = "The bootcamp must have at least one associated capacity and a maximum of four.";
    public static final String ID_BOOTCAMP_VALIDATIONS_EXCEPTION_MESSAGE = "the bootcamp with the id %d was not found";
    public static final String VERSION_VALIDATIONS_EXCEPTION_MESSAGE = "The start date cannot be greater than the end date.";
}
