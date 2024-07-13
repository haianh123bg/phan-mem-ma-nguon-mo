package com.haianh123.library.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "tblborrowing_book")
public class BorrowingBook extends BaseEntity<Integer> {

    @Column(name = "borrowing_date")
    private Date borrowingDate;

    @Column(name = "borrowing_due_date")
    private Date borrowingDueDate;

    @Column(name = "borrowing_money")
    private Double borrowingMoney;

    @Column(name = "is_approve")
    private Boolean isApprove;

    @Column(name = "book_status")
    private String bookStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
