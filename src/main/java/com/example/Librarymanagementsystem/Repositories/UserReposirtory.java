package com.example.Librarymanagementsystem.Repositories;

import com.example.Librarymanagementsystem.Models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserReposirtory extends JpaRepository<User,Long> {


    Optional<User> findByEmail(String email);
}
