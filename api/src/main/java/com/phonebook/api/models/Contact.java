package com.phonebook.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Entity @Data
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Size(min = 3)
    private String fullName;
    @Size(min = 5)
    private String email;
    @Size(min = 5)
    private String address;
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contact")
    private Set<Phone> phones;
}
