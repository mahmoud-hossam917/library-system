package com.example.Librarymanagementsystem.ControllersTest;

import com.example.Librarymanagementsystem.Controllers.BookController;
import com.example.Librarymanagementsystem.Models.Book;
import com.example.Librarymanagementsystem.Responses.GenaricResponse;
import com.example.Librarymanagementsystem.Responses.ResponseWithList;
import com.example.Librarymanagementsystem.Services.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    private Book book;
    private List<Book> books;

    @BeforeEach
    public void setUp() {
        book = new Book(1L, "Book Title", "Author Name");
        books = Arrays.asList(book);
    }

    @Test
    public void testAddBook_whenBookAdded_thenReturnCreatedResponse() {
        // Given
        when(bookService.addBook(any(Book.class))).thenReturn(true);

        // When
        ResponseEntity<GenaricResponse> response = bookController.addBook(book);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("created successfully", response.getBody().getMessage());
    }

    @Test
    public void testDeleteBook_whenBookDeleted_thenReturnNoContentResponse() {
        // Given
        when(bookService.deleteBook(1L)).thenReturn(true);

        // When
        ResponseEntity<GenaricResponse> response = bookController.delete(1L);

        // Then
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals("Deleted successfully", response.getBody().getMessage());
    }

    @Test
    public void testUpdateBook_whenBookUpdated_thenReturnOkResponse() {
        // Given
        when(bookService.updateBook(any(Book.class))).thenReturn(true);

        // When
        ResponseEntity<GenaricResponse> response = bookController.updateBook(book);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("updated successfully", response.getBody().getMessage());
    }

    @Test
    public void testGetAllBooks_whenBooksRetrieved_thenReturnOkResponse() {
        // Given
        when(bookService.getAllbooks()).thenReturn(books);

        // When
        ResponseEntity<ResponseWithList<Book>> response = bookController.getAllBooks();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("books retrived successfully", response.getBody().getMessage());
        assertEquals(books, response.getBody().getData());
    }
}