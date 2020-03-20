package com.ab.templateApi.dao.handler;

import com.ab.templateApi.dao.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class MovieDto {

    private Long movieId;

    private String name;

    private String category;

    private Integer duration;

    private String msg;

    private String code;

    public MovieDto(String msg, String code) {
        this.msg = msg;
        this.code = code;
    }

    public static MovieDto toMovieDto(Movie movie, String msg, String code){
        return MovieDto.builder()
                .movieId(movie.getMovieId())
                .name(movie.getName())
                .category(movie.getCategory())
                .duration(movie.getDuration())
                .msg(msg)
                .code(code)
                .build();
    }
}
