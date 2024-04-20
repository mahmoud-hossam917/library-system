package com.example.Librarymanagementsystem.Services.Impl;

import com.example.Librarymanagementsystem.Models.Book;
import com.example.Librarymanagementsystem.Repositories.BookRepository;
import com.example.Librarymanagementsystem.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository){
        this.bookRepository=bookRepository;
    }

    @Override
    public boolean addBook(Book book) {

        if(bookRepository.findByTitle(book.getTitle())!=null)return  false;

        try {
            bookRepository.save(book);
        }catch (Exception exception){
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteBook(long id) {

        Optional<Book> book=bookRepository.findById(id);
        if(book.isPresent()) {

            bookRepository.delete(book.get());
            return  true;
        }
        return false;
    }

    @Override
    public boolean updateBook(Book updateBook) {
        Optional<Book> book=bookRepository.findById(updateBook.getId());
        if(book.isPresent()) {

            book.get().setTitle(updateBook.getTitle());
            book.get().setAuthor(updateBook.getAuthor());
            book.get().setIsbn(updateBook.getIsbn());
            book.get().setPublicationYear(updateBook.getPublicationYear());
            try {
                bookRepository.save(book.get());
            }
            catch (Exception e){
                return false;
            }
            return  true;
        }
        return false;

    }

    @Override
    public Book getBookById(long id) {

        Optional<Book> book=bookRepository.findById(id);
        return book.orElseGet(Book::new);
    }

    @Override
    public List<Book> getAllbooks() {


        return bookRepository.findAll();
    }
}
