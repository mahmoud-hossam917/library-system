package com.example.Librarymanagementsystem.Repositories;

import com.example.Librarymanagementsystem.Models.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatronRepository extends JpaRepository<Patron,Long> {

    @Query(value = "select * from patrons p where p.email=:email",nativeQuery = true)
    public Optional<Patron> findByEmail(String email);

}
