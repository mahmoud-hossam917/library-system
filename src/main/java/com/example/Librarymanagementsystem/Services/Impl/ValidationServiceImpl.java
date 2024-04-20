package com.example.Librarymanagementsystem.Services.Impl;

import com.example.Librarymanagementsystem.Models.RegisterRequest;
import com.example.Librarymanagementsystem.Services.ValidationService;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ValidationServiceImpl implements ValidationService {
    // Regex to validate email
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    // Pre-compiled Pattern instance
    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);

    /**
     * Validates an email address with regular expression.
     * @param email the email to validate
     * @return true if the email is valid, false otherwise
     */
    @Override
    public  boolean validateEmail(String email) {
        if (email == null) {
            return false;
        }
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @Override
    public boolean validateRegisterRequest(RegisterRequest request) {
        if(request==null ||request.getPassword()==null ||request.getPassword().length()<2||
                request.getFirstName()==null||request.getFirstName().length()<2||
                request.getLastName()==null||request.getLastName().length()<2){
            return false;
        }
        return true;
    }
}
