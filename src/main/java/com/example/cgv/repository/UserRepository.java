package com.example.cgv.repository;

import com.example.cgv.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

   User findByUserID(String userID);

   // User findById(String user1);
}
