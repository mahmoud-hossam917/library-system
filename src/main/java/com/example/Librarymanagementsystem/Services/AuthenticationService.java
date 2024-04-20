package com.example.Librarymanagementsystem.Services;

import com.example.Librarymanagementsystem.Models.AuthenticationRequest;
import com.example.Librarymanagementsystem.Models.AuthenticationResponse;
import com.example.Librarymanagementsystem.Models.RegisterRequest;
import com.example.Librarymanagementsystem.Responses.GenaricResponse;

public interface AuthenticationService {
    public GenaricResponse register(RegisterRequest request);
    public GenaricResponse authenticate(AuthenticationRequest request);
}
