package com.example.Librarymanagementsystem.Controllers;

import com.example.Librarymanagementsystem.Responses.GenaricResponse;
import com.example.Librarymanagementsystem.Services.BorrowRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;

@RestController

public class BorrowBookController {

    private final BorrowRecordService borrowRecordService;

    @Autowired
    public BorrowBookController(BorrowRecordService borrowRecordService) {
        this.borrowRecordService = borrowRecordService;
    }

    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<GenaricResponse> borrowBook(@PathVariable long bookId, @PathVariable long patronId,
                                                      @RequestParam int days) {
        LocalDate expectedDate = LocalDate.now().plusDays(days);
        if (borrowRecordService.borrowBook(bookId, patronId, expectedDate)) {

            return new ResponseEntity<>(new GenaricResponse(HttpStatus.OK.value(), "success book borrowed"),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(new GenaricResponse(HttpStatus.NOT_FOUND.value(), "no enough quantity"), HttpStatus.NOT_FOUND);
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<GenaricResponse> returnBook(@PathVariable long bookId, @PathVariable long patronId
    ) {
        LocalDate returnDate=LocalDate.now();
        if (borrowRecordService.returnBook(bookId, patronId, returnDate)) {
            return new ResponseEntity<>(new GenaricResponse(HttpStatus.OK.value(), "success book returned"),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(new GenaricResponse(HttpStatus.NOT_FOUND.value(), "no such book"), HttpStatus.NOT_FOUND);
    }
}
