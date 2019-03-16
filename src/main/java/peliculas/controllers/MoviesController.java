package peliculas.controllers;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import peliculas.models.Movie;
import peliculas.repositories.MovieNotFoundException;
import peliculas.repositories.MoviesRepository;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MoviesController {

    private final MoviesRepository moviesRepository;

    @Autowired
    public MoviesController(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    @GetMapping
    public List<Movie> movies(@RequestParam(name = "title", required = false) String title,
                              @RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "title");

        Page<Movie> moviePage;

        if (title != null)
            moviePage = moviesRepository.findAllByTitle(title, pageable);
        else
            moviePage = moviesRepository.findAll(pageable);

        return moviePage.getContent();
    }

    @GetMapping("/{idMovie}")
    public Movie one(@PathVariable Long idMovie) {
        return moviesRepository.findById(idMovie)
                .orElseThrow(() -> new MovieNotFoundException(idMovie));
    }

    @PostMapping
    public Movie createMovie(@RequestBody Movie movie) {
        return moviesRepository.save(movie);
    }

    @DeleteMapping("/{idMovie}")
    public void deleteMovie(@PathVariable Long idMovie) {
        moviesRepository.deleteById(idMovie);//todo que no tire 500 si no esta
    }
}
