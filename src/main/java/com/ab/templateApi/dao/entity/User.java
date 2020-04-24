package com.ab.templateApi.dao.entity;

import com.ab.templateApi.view.UserView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Users", uniqueConstraints = @UniqueConstraint(columnNames = {"Email", "Phone"}))
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_Id", nullable = false, unique = true, updatable = false)
    @JsonView(UserView.WithUserId.class)
    private Long userId;

    @Column(name = "Name", length = 20)
    @JsonView(UserView.WithoutRelations.class)
    private String name;

    @Column(name = "Surname", length = 20)
    @JsonView(UserView.WithoutRelations.class)
    private String surname;

    @Column(name = "Email", nullable = false, unique = true)
    @JsonView(UserView.WithoutRelations.class)
    private String email;

    @Column(name = "Password", nullable = false)
    @JsonView(UserView.WithPassword.class)
    private String password;

    @Column(name = "Phone", unique = true)
    @JsonView(UserView.WithoutRelations.class)
    private String phone;

    @Column(name = "Role", nullable = false)
    @JsonView(UserView.WithoutRelations.class)
    private String role;

    public User(String name, String surname, String email, String password, String phone, String role) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role = role;
    }
}
