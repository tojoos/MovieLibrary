package dev.tojoos.movieLibrary.controller;

import dev.tojoos.movieLibrary.model.Movie;
import dev.tojoos.movieLibrary.repository.MovieRepository;
import dev.tojoos.movieLibrary.services.MovieService;
import dev.tojoos.movieLibrary.services.MovieServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    MovieRepository movieRepository;

    MovieService movieService;

    public MovieController(MovieRepository movieRepository, MovieService movieService) {
        this.movieRepository = movieRepository;
        this.movieService = movieService;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.FOUND)
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Movie getMovieById(@PathVariable Integer id) {
        return movieService.getMovieById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Movie saveMovie(@RequestBody Movie movie) {
        return movieService.saveMovie(movie);
    }

    @PostMapping("/addAll")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Movie> saveAllMovies(@RequestBody List<Movie> movies) {
        return movieService.saveAllMovies(movies);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Movie updateMovie(@PathVariable Integer id, @RequestBody Movie movie) {
        return movieService.updateMovie(id, movie);
    }

    @PatchMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Movie updateMoviePartially(@PathVariable Integer id, @RequestBody Movie movie) {
        return movieService.updateMoviePartially(id, movie);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie(@PathVariable Integer id) {
        movieService.deleteMovie(id);
    }
}
