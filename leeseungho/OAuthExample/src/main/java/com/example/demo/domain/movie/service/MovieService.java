package com.example.demo.domain.movie.service;

import com.example.demo.domain.movie.dto.MovieInfoResponseDto;
import com.example.demo.domain.movie.dto.MovieSaveRequestDto;
import com.example.demo.domain.movie.entity.Movie;
import com.example.demo.domain.movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    @Transactional
    public MovieInfoResponseDto saveMovie(MovieSaveRequestDto movieSaveRequestDto) {
        Movie movie = Movie.builder()
                .title(movieSaveRequestDto.title())
                .editor(movieSaveRequestDto.editor())
                .build();

        movieRepository.save(movie);

        return MovieInfoResponseDto.from(movie);
    }

    @Transactional
    public void deleteMovie(Long movieId) {
        movieRepository.deleteById(movieId);
    }

    @Transactional(readOnly = true)
    public List<MovieInfoResponseDto> getAllMovie() {
        return movieRepository.findAll()
                .stream()
                .map(MovieInfoResponseDto::from)
                .toList();
    }
}
