package com.example.Librarymanagementsystem.ServicesTest;

import com.example.Librarymanagementsystem.Models.Book;
import com.example.Librarymanagementsystem.Services.BookService;
import com.example.Librarymanagementsystem.Services.Impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookService bookService;



    private Book book;
    private List<Book> books;

    @BeforeEach
    public void setup() {
        book = new Book(1L, "Book Title", "Author Name");
        books = List.of(book);
    }

    @Test
    public void testAddBook() {
        when(bookService.addBook(book)).thenReturn(true);
        assertTrue(bookService.addBook(book));
    }

    @Test
    public void testDeleteBook() {
        when(bookService.deleteBook(book.getId())).thenReturn(true);
        assertTrue(bookService.deleteBook(book.getId()));
    }

    @Test
    public void testUpdateBook() {
        Book updatedBook = new Book(1L, "Updated Book Title", "Updated Author Name");
        when(bookService.updateBook(updatedBook)).thenReturn(true);
        assertTrue(bookService.updateBook(updatedBook));
    }

    @Test
    public void testGetBookById() {
        when(bookService.getBookById(book.getId())).thenReturn(book);
        assertEquals(book, bookService.getBookById(book.getId()));
    }

    @Test
    public void testGetAllBooks() {
        when(bookService.getAllbooks()).thenReturn(books);
        assertEquals(books, bookService.getAllbooks());
    }
}