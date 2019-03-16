package peliculas.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import peliculas.models.Movie;

import java.util.List;
import java.util.Optional;

public interface MoviesRepository extends PagingAndSortingRepository<Movie, Long> {
    Optional<Movie> findById(Long aLong);

    List<Movie> findByTitle(String title);

    Page<Movie> findAllByTitle(String title, Pageable pageable);

    Page<Movie> findAll(Pageable pageable);

    <S extends Movie> S save(S s);

    void deleteById(Long aLong);

}
