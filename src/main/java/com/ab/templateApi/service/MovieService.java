package com.ab.templateApi.service;

import com.ab.templateApi.dao.entity.Movie;
import com.ab.templateApi.dao.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Optional<Movie> findById(Long id) {
        return movieRepository.findById(id);
    }

    public Iterable<Movie> findAll() {
        return movieRepository.findAll();
    }

    public List<Movie> findMovieByCategory(String category) {
        return movieRepository.findMovieByCategory(category);
    }

    public Movie save(Movie newMovie){
        Movie movie = new Movie(newMovie.getName(), newMovie.getCategory(), newMovie.getDuration());
        return movieRepository.save(movie);
    }

    public Movie update(Movie movie){
        return movieRepository.save(movie);
    }

    public void delete(Long id){
        movieRepository.deleteById(id);
    }

    @EventListener(ApplicationEvent.class)
    public void fillDb(){
        save(new Movie("Matrix", "Sci-Fi", 136));
        save(new Movie("Crank", "Action", 87));
        save(new Movie("Titanic", "Melodrama", 194));
    }
}
