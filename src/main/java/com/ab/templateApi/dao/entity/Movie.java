package com.ab.templateApi.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Movies")
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MovieId", nullable = false, unique = true, updatable = false)
    private Long movieId;

    @Column(name = "Name", length = 64)
    private String name;

    @Column(name = "Category", nullable = false)
    private String category;

    @Column(name = "Duration")
    private Integer duration;

    public Movie(String name, String category, Integer duration) {
        this.name = name;
        this.category = category;
        this.duration = duration;
    }
}
