package com.ab.templateApi.api;

import com.ab.templateApi.dao.entity.Movie;
import com.ab.templateApi.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/movies")
public class MovieApi {

    private final MovieService movieService;

    @Autowired
    public MovieApi(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    public Movie addMovie(@Valid @RequestBody Movie movie){
        return movieService.save(movie);
    }

    @PutMapping
    public Movie updateMovie(@Valid @RequestBody Movie movie){
        return movieService.update(movie);
    }
}
