package com.haianh123.library.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Entity
@Table(name = "tblhistory_payment")
public class HistoryPayment extends BaseEntity<Long> {

    @Column(name = "money")
    private BigDecimal money;

    @Column(name = "points")
    private Long points;

    @Column(name = "user_id")
    private Long userId;
}
