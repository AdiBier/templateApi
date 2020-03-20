package com.ab.templateApi.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@Table(name = "Users", uniqueConstraints = @UniqueConstraint(columnNames = {"Email", "Phone"}))
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserId", nullable = false, unique = true, updatable = false)
    private Long userId;

    @Column(name = "Name")
    @Min(3)
    private String name;

    @Column(name = "Surname")
    @Min(3)
    private String surname;

    @Column(name = "Email", nullable = false, unique = true)
    private String email;

    @Column(name = "Password", nullable = false)
    private String password;

    @Column(name = "Phone")
    private String phone;

    @NotEmpty
    @Column(name = "Role", nullable = false)
    private String role;

    public User(String name, String surname, String email, String password, String phone, String role) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role = role;
    }

    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
