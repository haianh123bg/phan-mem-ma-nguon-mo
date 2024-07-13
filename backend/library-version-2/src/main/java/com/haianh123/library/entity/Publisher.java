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
@Table(name = "tblpublisher")
public class Publisher extends BaseEntity<Integer> {

    @Column(name = "publisher_name")
    private String name;

    @Column(name = "publisher_phone_number")
    private String phoneNumber;

    @Column(name = "publisher_address")
    private String address;

    @OneToMany(mappedBy = "publisher")
    private List<Book> books;
}
