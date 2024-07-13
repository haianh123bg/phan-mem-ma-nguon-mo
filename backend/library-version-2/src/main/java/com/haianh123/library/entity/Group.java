package com.haianh123.library.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "tblgroup")
public class Group extends BaseEntity<Long> {

    @Column(name = "group_name")
    private String name;

    @Column(name = "group_description")
    private String description;

    @ManyToMany
    @JoinTable(
            name = "group_has_user",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
