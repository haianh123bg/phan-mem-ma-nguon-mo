package com.haianh123.library.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "tblcategory")
public class Category extends BaseEntity<Integer> {

    @Column(name = "category_name")
    private String name;

    @Column(name = "category_total_book")
    private int totalBook;

    @OneToMany(mappedBy = "category")
    private List<Book> books;
}
