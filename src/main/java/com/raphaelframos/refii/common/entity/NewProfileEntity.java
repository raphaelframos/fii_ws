package com.raphaelframos.refii.common.entity;

import javax.persistence.*;

@Entity
@Table(name = "new_profile")
public class NewProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
}
