package peliculas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import peliculas.models.Movie;
import peliculas.repositories.MovieNotFoundException;
import peliculas.repositories.MoviesRepository;

import java.util.List;

@RestController
public class MoviesController {

    private final MoviesRepository moviesRepository;

    @Autowired
    public MoviesController(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    @GetMapping("/movies")
    public List<Movie> movies(@RequestParam(name = "title", required = false) String title) {
        return moviesRepository.findByTitle(title);
    }

    @GetMapping("/movies/{idMovie}")
    public Movie one(@PathVariable Long idMovie) {
        return moviesRepository.findById(idMovie)
                .orElseThrow(() -> new MovieNotFoundException(idMovie));
    }

    @PostMapping(value = "/movies")
    public Movie createMovie(@RequestBody Movie movie) {
        return moviesRepository.save(movie);
    }

    @DeleteMapping(value = "/movies/{idMovie}")
    public void deleteMovie(@PathVariable Long idMovie) {
        moviesRepository.deleteById(idMovie);//todo que no tire 500 si no esta
    }
}
