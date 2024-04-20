package com.example.Librarymanagementsystem.Services.Impl;

import com.example.Librarymanagementsystem.Models.Patron;
import com.example.Librarymanagementsystem.Repositories.PatronRepository;
import com.example.Librarymanagementsystem.Services.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatronServiceImpl implements PatronService {

    private final PatronRepository patronRepository;
    @Autowired
    public PatronServiceImpl(PatronRepository patronRepository){
        this.patronRepository = patronRepository;
    }
    @Override
    public boolean addPatron(Patron patron) {
        Optional<Patron> found =patronRepository.findByEmail(patron.getEmail());
        if(found.isPresent()){
            return false;
        }
        patronRepository.save(patron);
        return true;
    }

    @Override
    public boolean deletePatron(long id) {
        Optional<Patron> patron = patronRepository.findById(id);
        if (patron.isPresent()) {
            patronRepository.delete(patron.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean updatePatron(Patron patron) {

        Optional<Patron> found = patronRepository.findById(patron.getId());
        if(found.isPresent()) {
            patronRepository.save(patron);
            return true;
        }
        return false;
    }



    @Override
    public Optional<Patron> getPatronByEmail(String email) {

        return patronRepository.findByEmail(email);
    }

    @Override
    public List<Patron> getAllPatrons() {
        return patronRepository.findAll();
    }

    public Optional<Patron> findById(long id){
        return patronRepository.findById(id);
    }
}
