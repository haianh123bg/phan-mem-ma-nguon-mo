package com.haianh123.library.repository;

import com.haianh123.library.entity.BorrowingBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowingBookRepository extends JpaRepository<BorrowingBook, Integer> {
}
