package com.example.Librarymanagementsystem.Repositories;

import com.example.Librarymanagementsystem.Models.BorrowRecord;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;

@Repository
public interface BorrowRecordRepository extends JpaRepository<BorrowRecord,Long> {

    @Query(value = "select *from borrowing_records b where b.patron_id=:patronId and " +
            "b.book_id=:bookId and b.return_date is null",nativeQuery = true)
    public BorrowRecord getBorrowRecord(long patronId , long bookId);

    @Transactional
    @Modifying
    @Query(value = "update borrowing_records  set return_date=:returnDate where book_id=:bookId and " +
            "patron_id=:patronId and return_date is null",nativeQuery = true)
    public void returnBook(long patronId, long bookId, LocalDate returnDate);
}
