package com.haianh123.library.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "tblfavorite_book")
public class FavoriteBook extends BaseEntity<Integer> {

    @Column(name = "is_bought")
    private Boolean isBought;

    @Column(name = "is_favorite_book")
    private Boolean isFavoriteBook;

    @Column(name = "is_borrowing")
    private Boolean isBorrowing;

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "user_id")
    private Long userId;
}
