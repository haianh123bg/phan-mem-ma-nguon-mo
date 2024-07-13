package com.haianh123.library.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "tblreturn_slip_book")
public class ReturnSlipBook extends BaseEntity<Long> {

    @Column(name = "return_date")
    private Date returnDate;

    @Column(name = "return_money_refund")
    private String returnMoneyRefund;

    @Column(name = "return_money_fine_fee")
    private String returnMoneyFineFee;

    @Column(name = "book_status")
    private String bookStatus;

    @Column(name = "borrowing_date")
    private Date borrowingDate;

    @Column(name = "borrowing_due_date")
    private Date borrowingDueDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
