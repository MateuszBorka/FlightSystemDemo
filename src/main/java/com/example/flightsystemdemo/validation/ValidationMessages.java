package com.example.flightsystemdemo.validation;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidationMessages {

    public static final String NAME = "Must contain only alphanumeric characters";
    public static final String SURNAME = "Must contain only alphanumeric characters";


}
