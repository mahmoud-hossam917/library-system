package com.example.Librarymanagementsystem.Services;

import com.example.Librarymanagementsystem.Models.RegisterRequest;

public interface ValidationService {
    public boolean validateEmail(String email);
    public boolean validateRegisterRequest(RegisterRequest request);
}
