package com.haianh123.library.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "tblimage_person")
public class ImagePerson extends BaseEntity<Long> {

    @Column(name = "url_drive")
    private String urlDrive;

    @Column(name = "author_id")
    private Integer authorId;

    @Column(name = "publisher_id")
    private Integer publisherId;

    @Column(name = "user_id")
    private Integer userId;
}
