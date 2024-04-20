package com.example.Librarymanagementsystem.Services;

import com.example.Librarymanagementsystem.Models.Patron;

import java.util.List;
import java.util.Optional;

public interface PatronService {
    public boolean addPatron(Patron patron);
    public boolean deletePatron(long id);
    public boolean updatePatron(Patron patron);
    public Optional<Patron> getPatronByEmail(String email);
    public List<Patron> getAllPatrons();
    public Optional<Patron> findById(long id);
}
