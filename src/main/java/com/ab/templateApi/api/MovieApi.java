package com.ab.templateApi.api;

import com.ab.templateApi.dao.entity.Movie;
import com.ab.templateApi.dao.handler.MovieDto;
import com.ab.templateApi.dao.repository.MovieRepository;
import com.ab.templateApi.response.ServerResponse;
import com.ab.templateApi.response.ServerResponseConstants;
import com.ab.templateApi.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static com.ab.templateApi.dao.handler.MovieDto.toMovieDto;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/movies")
public class MovieApi {

    private final MovieService movieService;

    @Autowired
    public MovieApi(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/all")
    public Iterable<MovieDto> getAllMovies() {
        Iterable<Movie> movieIterable = movieService.findAll();
        if (movieIterable.iterator().hasNext()) {
            return StreamSupport.stream(movieIterable.spliterator(), false)
                    .map(movies -> {
                        return new MovieDto(movies.getMovieId(), movies.getName(), movies.getCategory(), movies.getDuration(),
                                ServerResponseConstants.OK_MSG, ServerResponseConstants.OK_CODE);
                    }).collect(toList());
        }
        List<MovieDto> movieDtoList = new ArrayList<>();
        movieDtoList.add(new MovieDto(null, null, null, null, ServerResponseConstants.TAE1002_MSG, ServerResponseConstants.TAE1002_CODE));
        return movieDtoList;
    }

    @GetMapping
    public MovieDto getById(@RequestParam Long id) {
        Optional<Movie> movie = movieService.findById(id);
        if(movie.isPresent()){
            return toMovieDto(movie.get(), ServerResponseConstants.OK_MSG, ServerResponseConstants.OK_CODE);
        }
        return new MovieDto(null, null, null, null, ServerResponseConstants.TAE1002_MSG, ServerResponseConstants.TAE1002_CODE);
    }

    @PostMapping
    public Movie addMovie(@Valid @RequestBody Movie movie){
        return movieService.save(movie);
    }

    @PutMapping
    public Movie updateMovie(@Valid @RequestBody Movie movie){
        return movieService.save(movie);
    }

    @DeleteMapping
    public ServerResponse deleteMovieById(@RequestParam Long id) {
        if (movieService.findById(id).isPresent()){
            movieService.delete(id);
            return new ServerResponse(ServerResponseConstants.OK_MSG, ServerResponseConstants.OK_CODE);
        }
        return new ServerResponse(ServerResponseConstants.TAE1002_MSG, ServerResponseConstants.TAE1002_CODE);
    }
}
