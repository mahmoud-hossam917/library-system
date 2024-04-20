package com.example.Librarymanagementsystem.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity(name = "borrowing_records")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class BorrowRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "borrow_seq")
    @SequenceGenerator(name = "borrow_seq", sequenceName = "borrow_seq", allocationSize = 1)
    private Long id;

    @Column(name = "patron_id")
    private Long patronId;

    @Column(name = "book_id")
    private Long bookId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id", nullable = false, insertable = false, updatable = false)
    @JsonIgnore
    private Book book;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patron_id", nullable = false, insertable = false, updatable = false)
    @JsonIgnore
    private Patron patron;

    @Column(name = "borrow_date", nullable = false)
    private LocalDate borrowDate;

    @Column(name = "return_date",nullable = true)
    private LocalDate returnDate;

    @Column(name = "expected_return_date",nullable = false)
    private LocalDate expectedReturnDate;
}
