package com.example.Librarymanagementsystem.Services.Impl;

import com.example.Librarymanagementsystem.Models.Book;
import com.example.Librarymanagementsystem.Models.BorrowRecord;
import com.example.Librarymanagementsystem.Models.Patron;
import com.example.Librarymanagementsystem.Repositories.BookRepository;
import com.example.Librarymanagementsystem.Repositories.BorrowRecordRepository;
import com.example.Librarymanagementsystem.Services.BookService;
import com.example.Librarymanagementsystem.Services.BorrowRecordService;
import com.example.Librarymanagementsystem.Services.PatronService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Service
public class BorrowRecordServiceImpl implements BorrowRecordService {

    private final BookService bookService;
    private final BookRepository bookRepository;
    private final BorrowRecordRepository borrowRecordRepository;
    private final PatronService patronService;

    @Autowired
    public  BorrowRecordServiceImpl(PatronService patronService,BookService bookService, BorrowRecordRepository borrowRecordRepository , BookRepository bookRepository){
        this.bookService=bookService;
        this.bookRepository=bookRepository;
        this.borrowRecordRepository=borrowRecordRepository;
        this.patronService=patronService;
    }
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public boolean borrowBook(long bookId, long patronId, LocalDate expectedReturnDate ){
        Book book=bookService.getBookById(bookId);
        Optional<Patron> patron=patronService.findById(patronId);
        if(book==null||book.getQuantity()==0|| patron.isEmpty()) return false;
        book.setQuantity(book.getQuantity()-1);
        bookService.updateBook(book);
        BorrowRecord borrowRecord=BorrowRecord.builder()
                .bookId(bookId)
                .patronId(patronId)
                .borrowDate(LocalDate.now())
                .expectedReturnDate(expectedReturnDate)
                .build();
        borrowRecordRepository.save(borrowRecord);
        return  true;
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public boolean returnBook(long bookId,long patronId,LocalDate returnDate){
        BorrowRecord borrowRecord=borrowRecordRepository.getBorrowRecord(patronId,bookId);
        if(borrowRecord!=null) {
            borrowRecordRepository.returnBook(patronId, bookId, returnDate);
            Book book = bookService.getBookById(bookId);
            book.setQuantity(book.getQuantity() + 1);
            bookRepository.save(book);
            return true;
        }
        return  false;
    }

}
