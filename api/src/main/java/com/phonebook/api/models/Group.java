package com.phonebook.api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Entity(name = "contact_group") @Data
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    @Size(min = 3)
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "group")
    private Set<Contact> contacts;
}