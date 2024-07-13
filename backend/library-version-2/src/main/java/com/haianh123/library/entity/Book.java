package com.haianh123.library.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "tblbook")
public class Book extends BaseEntity<Long> {

    @Column(name = "book_name")
    private String name;

    @Column(name = "book_description")
    private String description;

    @Column(name = "ebook_id")
    private Long ebookId;

    @Column(name = "book_inventory_number")
    private Integer inventoryNumber;

    @Column(name = "book_number_available")
    private Integer numberAvailable;

    @Column(name = "book_page_number")
    private Integer pageNumber;

    @Column(name = "book_language")
    private String language;

    @Column(name = "book_weight")
    private Integer weight;

    @Column(name = "book_size")
    private String size;

    @Column(name = "book_ratings_star")
    private Double ratingsStar;

    @Column(name = "book_isbn")
    private String isbn;

    @Column(name = "book_listed_price")
    private BigDecimal listedPrice;

    @Column(name = "book_selling_price")
    private BigDecimal sellingPrice;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @OneToMany(mappedBy = "book", orphanRemoval = true)
    private List<ImageBook> imageBooks;

    @JsonIgnore
    @OneToMany(mappedBy = "book")
    private List<ReturnSlipBook> returnSlipBooks;

    @JsonIgnore
    @OneToMany(mappedBy = "book")
    private List<BorrowingBook> borrowingBooks;
}
