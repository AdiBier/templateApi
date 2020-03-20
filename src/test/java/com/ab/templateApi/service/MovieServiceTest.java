package com.ab.templateApi.service;

import com.ab.templateApi.dao.entity.Movie;
import com.ab.templateApi.dao.repository.MovieRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceTest {

    @InjectMocks
    private MovieService movieService;

    @Mock
    private MovieRepository movieRepository;

    private Movie movie;

    private final Long MOVIE_ID = 1L;

    @Before
    public void setUp() {
        movie = new Movie(MOVIE_ID, "Jobs", "Drama", 129);
    }

    //findAll BEGINS
    @Test
    public void shouldReturnFindAllMovieOK() {
        //given
        List<Movie> movieListOK = prepareMovies();
        when(movieRepository.findAll()).thenReturn(movieListOK);

        //when
        Iterable<Movie> foundAllOK = movieService.findAll();

        //then
        assertEquals(foundAllOK, movieListOK);
    }

    @Test
    public void shouldReturnFindAllMovieERR() {
        //given
        when(movieRepository.findAll()).thenReturn(Collections.emptyList());

        //when
        Iterable<Movie> foundAllERR = movieService.findAll();

        //then
        assertEquals(foundAllERR, Collections.EMPTY_LIST);
    }
    //ENDS

    //findMovieById BEGINS
    @Test
    public void shouldReturnFindMovieById() {
        //given
        when(movieRepository.findById(movie.getMovieId())).thenReturn(Optional.of(movie));

        //when
        Optional<Movie> foundMovieByIdOK = movieService.findById(1L);

        //then
        assertEquals(foundMovieByIdOK.get().getMovieId(), movie.getMovieId());
        assertEquals(foundMovieByIdOK.get().getName(), movie.getName());
        assertEquals(foundMovieByIdOK.get().getCategory(), movie.getCategory());
        assertEquals(foundMovieByIdOK.get().getDuration(), movie.getDuration());
    }
    //ENDS

    //findMovieByCategory BEGINS
    @Test
    public void shouldReturnFindMovieByCategoryOK() {
        //given
        List<Movie> movieListOK = prepareMovies().stream()
                .filter(category -> category.getCategory().equals("Drama"))
                .collect(toList());
        when(movieRepository.findMovieByCategory("Drama")).thenReturn(movieListOK);

        //when
        List<Movie> foundAllOK = movieService.findMovieByCategory("Drama");

        //then
        assertEquals(foundAllOK, movieListOK);
    }

    @Test
    public void shouldReturnFindMovieByCategoryERR(){
        //given
        when(movieRepository.findMovieByCategory("Drama")).thenReturn(Collections.EMPTY_LIST);

        //when
        List<Movie> foundAllERR = movieService.findMovieByCategory("Drama");

        //then
        assertEquals(foundAllERR, Collections.EMPTY_LIST);
    }
    //ENDS

    private List<Movie> prepareMovies() {
        Movie movie1 = new Movie("Creed", "Drama", 133);
        Movie movie2 = new Movie("Serenity", "Sci-Fi", 119);
        Movie movie3 = new Movie("Apocalypto", "Drama", 139);
        Movie movie4 = new Movie("Ship of love", "Comedy", 94);
        Movie movie5 = new Movie("The Fanatic", "Thriller", 88);

        return Arrays.asList(movie1, movie2, movie3, movie4, movie5);
    }
}
