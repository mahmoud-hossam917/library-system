package com.example.Librarymanagementsystem.Controllers;

import com.example.Librarymanagementsystem.Models.Patron;
import com.example.Librarymanagementsystem.Responses.GenaricResponse;
import com.example.Librarymanagementsystem.Responses.ResponseWithList;
import com.example.Librarymanagementsystem.Services.PatronService;
import com.example.Librarymanagementsystem.Services.ValidationService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patrons")
public class PatronController {

    private final PatronService patronService;
    private final ValidationService validationService;


    @Autowired
    public PatronController(PatronService patronService,ValidationService validationService) {
        this.patronService = patronService;
        this.validationService=validationService;
    }

    @PostMapping("")
    public ResponseEntity<GenaricResponse> addPatron(@RequestBody Patron patron) {
        if(!validationService.validateEmail(patron.getEmail())){
            return new ResponseEntity<>(new GenaricResponse(HttpStatus.BAD_REQUEST.value(), "email is not valid"),HttpStatus.BAD_REQUEST);
        }
        boolean isAdded = patronService.addPatron(patron);
        if (isAdded) {
            GenaricResponse genaricResponse = new GenaricResponse(HttpStatus.CREATED.value(), "patron added successfully");
            return new ResponseEntity<>(genaricResponse, HttpStatus.CREATED);
        }
        GenaricResponse genaricResponse = new GenaricResponse(HttpStatus.CONFLICT.value(), "patron is already added");
        return new ResponseEntity<>(genaricResponse, HttpStatus.CONFLICT);
    }
    @PutMapping("")
    public ResponseEntity<GenaricResponse> updatePatron(@RequestBody Patron patron) {
        boolean isUpdated = patronService.updatePatron(patron);
        if (isUpdated) {
            GenaricResponse genaricResponse = new GenaricResponse(HttpStatus.OK.value(), "patron updated successfully");
            return new ResponseEntity<>(genaricResponse, HttpStatus.OK);
        }
        GenaricResponse genaricResponse = new GenaricResponse(HttpStatus.NOT_FOUND.value(), "patron is not updated");
        return new ResponseEntity<>(genaricResponse, HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<GenaricResponse> deletePatron(@PathVariable long id) {

        boolean isDeleted = patronService.deletePatron(id);
        if (isDeleted) {
            GenaricResponse genaricResponse = new GenaricResponse(HttpStatus.NO_CONTENT.value(), "patron deleted successfully");
            return new ResponseEntity<>(genaricResponse, HttpStatus.NO_CONTENT);
        }
        GenaricResponse genaricResponse = new GenaricResponse(HttpStatus.NOT_FOUND.value(), "patron is not deleted");
        return new ResponseEntity<>(genaricResponse, HttpStatus.NOT_FOUND);
    }
    @GetMapping("")
    public ResponseEntity<ResponseWithList<Patron>> getAllPatrons(){
        List<Patron> patrons = patronService.getAllPatrons();
        ResponseWithList<Patron> response = new ResponseWithList<>(patrons,HttpStatus.OK.value(),"Patrons retrived successfully");
        return new ResponseEntity<>(response,HttpStatus.OK);

    }
    @GetMapping("patron")
    public ResponseEntity getPatronByEmail(@NonNull @RequestParam String email){
        Optional<Patron> patron = patronService.getPatronByEmail(email);
        if(patron.isPresent()) {
            ResponseWithList<Patron> response = new ResponseWithList<>(List.of(patron.get()), HttpStatus.OK.value(), "Patron retrived successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(new GenaricResponse(HttpStatus.NO_CONTENT.value(),"patron not found"), HttpStatus.NOT_FOUND);
    }
    @GetMapping("{id}")
    public ResponseEntity<GenaricResponse> getPatronByid(@PathVariable long id) {

        Optional<Patron> patron = patronService.findById(id);
        if (patron.isPresent()) {
            GenaricResponse genaricResponse = new GenaricResponse(HttpStatus.OK.value(), "patron found successfully");
            return new ResponseEntity<>(genaricResponse, HttpStatus.OK);
        }
        GenaricResponse genaricResponse = new GenaricResponse(HttpStatus.NOT_FOUND.value(), "patron is not found");
        return new ResponseEntity<>(genaricResponse, HttpStatus.NOT_FOUND);
    }
}
