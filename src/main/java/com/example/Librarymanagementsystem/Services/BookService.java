package com.example.Librarymanagementsystem.Services;


import com.example.Librarymanagementsystem.Models.Book;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BookService {

    public boolean addBook(Book book);
    public boolean deleteBook(long id);
    public boolean updateBook(Book updatedBook);
    public Book getBookById(long id);
    public List<Book> getAllbooks();
}
