package com.haianh123.library.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "tblimage_book")
public class ImageBook extends BaseEntity<Long> {

    @Column(name = "url_drive")
    private String urlDrive;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
