package com.phonebook.api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity @Data
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    @Size(min = 5, max = 15)
    private String number;
    @ManyToOne
    @JoinColumn(name = "contact_id", nullable = false)
    private Contact contact;
    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private Type type;
}
