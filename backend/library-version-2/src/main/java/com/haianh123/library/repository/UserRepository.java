package com.haianh123.library.repository;

import com.haianh123.library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT u FROM User u WHERE u.email = :email ORDER BY u.email DESC LIMIT 1")
    Optional<User> findByEmail(@Param(value = "email") String email);
}
