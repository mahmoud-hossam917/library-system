package com.example.Librarymanagementsystem.Repositories;

import com.example.Librarymanagementsystem.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    @Query(nativeQuery = true,value = "select b.* from books b where b.title=:title ")
    public Book findByTitle(String title);

}
