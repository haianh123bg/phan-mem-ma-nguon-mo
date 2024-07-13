package com.haianh123.library.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "tblauthor")
public class Author extends BaseEntity<Integer> {

    @Column(name = "author_name")
    private String name;

    @Column(name = "author_date_of_birth")
    private Date dateOfBirth;

    @Column(name = "author_description")
    private String description;

    @Column(name = "author_total_book")
    private int totalBook;

    @OneToMany(mappedBy = "author")
    private List<Book> books;
}
