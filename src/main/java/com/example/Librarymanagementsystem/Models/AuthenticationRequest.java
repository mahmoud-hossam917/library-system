package com.example.Librarymanagementsystem.Models;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthenticationRequest {

    private String email;
    private String password;
}
