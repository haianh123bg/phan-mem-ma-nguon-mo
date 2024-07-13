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
@Table(name = "tblebook")
public class Ebook extends BaseEntity<Long> {

    @Column(name = "url_drive")
    private String urlDrive;

    @OneToMany(mappedBy = "ebook")
    private List<Audio> audio;
}
