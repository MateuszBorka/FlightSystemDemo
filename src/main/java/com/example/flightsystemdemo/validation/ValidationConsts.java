package com.example.flightsystemdemo.validation;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)

public class ValidationConsts {

    public static final int MIN_SIZE_NAME = 3;
    public static final int MAX_SIZE_NAME = 100;
    public static final String NAME_NO_SPACES_PATTERN = "^[a-zA-Z0-9]+$";


    public static final int MIN_SIZE_SURNAME = 3;
    public static final int MAX_SIZE_SURNAME = 100;
    public static final String SURNAME_NO_SPACES_PATTERN = "^[a-zA-Z0-9]+$";

    public static final int MAX_PHONE_NUMBER_LENGTH = 25;
    public static final int SIZE_CODE = 3; // IATA code system


    public static final int MIN_FREE_PLACES = 1;
    public static final int MAX_FREE_PLACES = 1000; // I am definitely not sure about this value, would better ask Product Owner
}
