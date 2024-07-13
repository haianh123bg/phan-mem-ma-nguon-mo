package com.haianh123.library.repository;

import com.haianh123.library.entity.FavoriteBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteBookRepository extends JpaRepository<FavoriteBook, Integer> {
}
