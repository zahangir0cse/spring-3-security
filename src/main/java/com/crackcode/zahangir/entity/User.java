package com.crackcode.zahangir.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity(name = "user_tbl")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String name;
    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private Set<Role> roles;
}
