package dev.tojoos.movieLibrary.services;

import dev.tojoos.movieLibrary.model.Movie;
import dev.tojoos.movieLibrary.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Slf4j
public class MovieServiceImpl implements MovieService {

    MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.getAllMovies();
    }

    @Override
    public Movie getMovieById(Integer id) {
        try {
            return movieRepository.getMovieById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find a movie with id=" + id);
        }
    }

    @Override
    public Movie saveMovie(Movie movie) {
        if(isMovieValid(movie, "save")) {
            return movieRepository.saveMovie(movie);
        } else {
            return null;
        }
    }

    @Override
    public List<Movie> saveAllMovies(List<Movie> movies) {
        movies.forEach(this::saveMovie);
        return movies;
    }

    @Override
    public Movie updateMovie(Integer id, Movie movie) {
        Movie toUpdate = this.getMovieById(id);
        if(isMovieValid(movie, "update")) {
            toUpdate.setName(movie.getName());
            toUpdate.setRating(movie.getRating());
            return movieRepository.updateMovie(id, movie);
        } else {
            return null;
        }
    }

    @Override
    public Movie updateMoviePartially(Integer id, Movie movie) {
        Movie toUpdate = this.getMovieById(id);
        System.out.println(toUpdate);
        if(movie.getName() != null) toUpdate.setName(movie.getName());
        if(movie.getRating() != null) toUpdate.setRating(movie.getRating());
        return movieRepository.updateMovie(id, toUpdate);
    }

    @Override
    public void deleteMovie(Integer id) {
        Movie movieToDelete = this.getMovieById(id);
        if(movieToDelete != null)
            movieRepository.deleteMovie(id);
    }

    private boolean isMovieValid(Movie movie, String action) {
        if (movie.getName() != null && movie.getRating() != null) {
            if (movie.getRating() >= 0 && movie.getRating() <= 10) {
                return true;
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Unable to " + action + " a movie - rating's value must be between 0 - 10");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Unable to " + action + " a movie - missing field(s)");
        }
    }
}
