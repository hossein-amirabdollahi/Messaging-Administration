package com.administration.messaging;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationChecker {
    private Pattern pattern;
    private Matcher matcher;

    public boolean phoneNumberValidation(String phoneNumber){
        String regex = "^09\\d{9}$";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    public boolean messageRestrictionValidation(String message){
        String regex = "(?i)\\bkill\\b";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(message);
        return matcher.find();
    }

}
