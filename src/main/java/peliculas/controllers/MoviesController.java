package peliculas.controllers;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import peliculas.models.Movie;
import peliculas.repositories.MovieNotFoundException;
import peliculas.repositories.MoviesRepository;

import javax.validation.Valid;
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
                              @RequestParam(name = "titleLike", required = false) String titleLike,
                              @RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "title");

        Page<Movie> moviePage;

        if (title != null)
            moviePage = moviesRepository.findAllByTitle(title, pageable);
        else if (titleLike != null)
            moviePage = moviesRepository.findAllByTitleContaining(titleLike, pageable);
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
    @ResponseStatus(HttpStatus.CREATED)
    public Movie createMovie(@RequestBody @Valid Movie movie) {
        return moviesRepository.save(movie);
    }

    @PutMapping("/{idMovie}")
    public Movie replaceMovie(@RequestBody @Valid Movie newMovie, @PathVariable Long idMovie) {

        return moviesRepository.findById(idMovie)
                .map(movie -> {
                    movie.setTitle(newMovie.getTitle());
                    movie.setCountry(newMovie.getCountry());
                    movie.setReleaseDate(newMovie.getReleaseDate());
                    movie.setDirector(newMovie.getDirector());
                    movie.setCast(newMovie.getCast());
                    return moviesRepository.save(movie);
                }).orElseThrow(() -> new MovieNotFoundException(idMovie));
    }

    @PatchMapping("/{idMovie}")
    public Movie modifyMovie(@RequestBody Movie modifiedMovie, @PathVariable Long idMovie) {
        return moviesRepository.findById(idMovie)
                .map(movie -> {
                    if (modifiedMovie.getTitle() != null)
                        movie.setTitle(modifiedMovie.getTitle());
                    if (modifiedMovie.getCountry() != null)
                        movie.setCountry(modifiedMovie.getCountry());
                    if (modifiedMovie.getReleaseDate() != null)
                        movie.setReleaseDate(modifiedMovie.getReleaseDate());
                    if (modifiedMovie.getDirector() != null)
                        movie.setDirector(modifiedMovie.getDirector());
                    if (modifiedMovie.getCast() != null)
                        movie.setCast(modifiedMovie.getCast());
                    return moviesRepository.save(movie);
                }).orElseThrow(() -> new MovieNotFoundException(idMovie));
    }

    @DeleteMapping("/{idMovie}")
    public void deleteMovie(@PathVariable Long idMovie) {
        if (moviesRepository.existsById(idMovie))
            moviesRepository.deleteById(idMovie);
        else
            throw new MovieNotFoundException(idMovie);
    }
}
