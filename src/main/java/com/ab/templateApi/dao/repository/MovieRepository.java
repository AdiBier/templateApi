package com.ab.templateApi.dao.repository;

import com.ab.templateApi.dao.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findMovieByCategory(String category);
}
