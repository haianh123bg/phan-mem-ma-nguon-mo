package com.haianh123.library.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "tblaudio")
public class Audio extends BaseEntity<Long> {

    @Column(name = "url_drive")
    private String urlDrive;

    @ManyToOne
    @JoinColumn(name = "ebook_id")
    private Ebook ebook;
}
