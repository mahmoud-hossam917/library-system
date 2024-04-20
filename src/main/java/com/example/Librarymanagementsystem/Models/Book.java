package com.example.Librarymanagementsystem.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "books")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq")
    @SequenceGenerator(name = "book_seq", sequenceName = "book_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(name = "publication_year")
    private Integer publicationYear;

    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(name = "quantity")
    private int quantity;

    public Book(long id, String title, String author) {
        this.id=id;
        this.title=title;
        this.author=author;
    }
}
