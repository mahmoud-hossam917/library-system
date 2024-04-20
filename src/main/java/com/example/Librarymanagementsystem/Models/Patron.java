package com.example.Librarymanagementsystem.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity(name = "patrons")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Patron {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patron_seq")
    @SequenceGenerator(name = "patron_seq", sequenceName = "patron_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(unique = true)
    private String phoneNumber;

    public Patron(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Patron(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
