package dev.tojoos.movieLibrary.services;

import dev.tojoos.movieLibrary.model.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> getAllMovies();

    Movie getMovieById(Integer id);

    Movie saveMovie(Movie movie);

    List<Movie> saveAllMovies(List<Movie> movies);

    Movie updateMovie(Integer id, Movie movie);

    Movie updateMoviePartially(Integer id, Movie movie);

    void deleteMovie(Integer id);
}
