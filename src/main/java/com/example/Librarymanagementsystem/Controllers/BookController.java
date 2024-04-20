package com.example.Librarymanagementsystem.Controllers;


import com.example.Librarymanagementsystem.Models.Book;
import com.example.Librarymanagementsystem.Responses.GenaricResponse;
import com.example.Librarymanagementsystem.Responses.ResponseWithList;
import com.example.Librarymanagementsystem.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService=bookService;
    }
    @PostMapping("")
    public ResponseEntity<GenaricResponse> addBook(@RequestBody Book book){
       boolean isAdded= bookService.addBook(book);

       if(isAdded){
            GenaricResponse genaricResponse = new GenaricResponse(HttpStatus.CREATED.value(),"created successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(genaricResponse);
       }
        GenaricResponse genaricResponse = new GenaricResponse(HttpStatus.CONFLICT.value(),"already created");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(genaricResponse);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<GenaricResponse> delete(@PathVariable long id){
        boolean isDeleted= bookService.deleteBook(id);

        if(isDeleted){
            GenaricResponse genaricResponse = new GenaricResponse(HttpStatus.NO_CONTENT.value(),"Deleted successfully");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(genaricResponse);
        }
        GenaricResponse genaricResponse = new GenaricResponse(HttpStatus.NOT_FOUND.value(),"id not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(genaricResponse);
    }

    @PutMapping("")
    public ResponseEntity<GenaricResponse> updateBook(@RequestBody Book book){
        boolean isUpdated= bookService.updateBook(book);

        if(isUpdated){
            GenaricResponse genaricResponse = new GenaricResponse(HttpStatus.OK.value(),"updated successfully");
            return ResponseEntity.status(HttpStatus.OK).body(genaricResponse);
        }
        GenaricResponse genaricResponse = new GenaricResponse(HttpStatus.NOT_FOUND.value(),"book not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(genaricResponse);
    }

    @GetMapping("")
    public ResponseEntity<ResponseWithList<Book>> getAllBooks(){

        List<Book> books=bookService.getAllbooks();
        ResponseWithList<Book> response = new ResponseWithList<Book>(books,HttpStatus.OK.value(),"books retrived successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("{id}")
    public ResponseEntity<GenaricResponse> getBookById(@PathVariable long id){

        Book book=bookService.getBookById(id);
        if(book==null){
            return new ResponseEntity<>(new GenaricResponse(HttpStatus.NOT_FOUND.value(),"book not found"),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ResponseWithList<Book>(List.of(book),HttpStatus.OK.value(), "book found"),HttpStatus.OK);
    }



}
