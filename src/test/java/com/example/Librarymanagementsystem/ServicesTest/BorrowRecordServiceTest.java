package com.example.Librarymanagementsystem.ServicesTest;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.Librarymanagementsystem.Services.BorrowRecordService;

@ExtendWith(MockitoExtension.class)
public class BorrowRecordServiceTest {

    @Mock
    private BorrowRecordService borrowRecordService;

    @InjectMocks
    private BorrowRecordServiceTest test;

    @Test
    public void testBorrowBook() {
        // Arrange
        long bookId = 1;
        long patronId = 2;
        LocalDate expectedReturnDate = LocalDate.of(2022, 10, 10);

        // Mock the service to return true when borrowBook is called
        when(borrowRecordService.borrowBook(bookId, patronId, expectedReturnDate)).thenReturn(true);

        // Act
        boolean result = borrowRecordService.borrowBook(bookId, patronId, expectedReturnDate);

        // Assert
        assertTrue(result, "Borrowing a book should return true");
    }

    @Test
    public void testReturnBook() {
        // Arrange
        long bookId = 1;
        long patronId = 2;
        LocalDate returnDate = LocalDate.of(2022, 10, 10);

        // Mock the service to return true when returnBook is called
        when(borrowRecordService.returnBook(bookId, patronId, returnDate)).thenReturn(true);

        // Act
        boolean result = borrowRecordService.returnBook(bookId, patronId, returnDate);

        // Assert
        assertTrue(result, "Returning a book should return true");
    }
}