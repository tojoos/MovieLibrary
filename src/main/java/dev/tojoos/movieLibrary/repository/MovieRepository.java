package dev.tojoos.movieLibrary.repository;

import dev.tojoos.movieLibrary.model.Movie;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieRepository {

    JdbcTemplate jdbcTemplate;

    public MovieRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Movie> getAllMovies() {
        return jdbcTemplate.query("SELECT id, name, rating FROM movie",
                BeanPropertyRowMapper.newInstance(Movie.class));
    }

    public Movie getMovieById(Integer id) {
        return jdbcTemplate.queryForObject("SELECT id, name, rating FROM movie WHERE id = ?",
                BeanPropertyRowMapper.newInstance(Movie.class), id);
    }

    public Movie saveMovie(Movie movie) {
        jdbcTemplate.update("INSERT INTO movie (name, rating) VALUES (?,?)",
                movie.getName(),
                movie.getRating());
        return movie;
    }

    public Movie updateMovie(Integer id, Movie movie) {
        jdbcTemplate.update("UPDATE movie SET name = ?, rating = ? WHERE id = ?",
                movie.getName(),
                movie.getRating(),
                id);
        return movie;
    }

    public void deleteMovie(Integer id) {
        jdbcTemplate.update("DELETE FROM movie WHERE id = ?", id);
    }
}