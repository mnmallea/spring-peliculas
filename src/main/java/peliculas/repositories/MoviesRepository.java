package peliculas.repositories;


import org.springframework.data.repository.PagingAndSortingRepository;
import peliculas.models.Movie;

import java.util.List;
import java.util.Optional;

public interface MoviesRepository extends PagingAndSortingRepository<Movie, Long> {
    Optional<Movie> findById(Long aLong);

    List<Movie> findByTitle(String title);

    <S extends Movie> S save(S s);

    void deleteById(Long aLong);

}
