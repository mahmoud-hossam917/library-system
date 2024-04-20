package com.example.Librarymanagementsystem.Services;

import java.time.LocalDate;
import java.util.Date;

public interface BorrowRecordService {
    public boolean borrowBook(long bookId, long patronId, LocalDate expectedReturnDate );
    public boolean returnBook(long bookId,long patronId,LocalDate returnDate);
}
