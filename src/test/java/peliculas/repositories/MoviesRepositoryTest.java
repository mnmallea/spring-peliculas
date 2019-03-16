package peliculas.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import peliculas.models.Actor;
import peliculas.models.Movie;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MoviesRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MoviesRepository moviesRepository;

    @Test
    public void movieFindByTitle() {
        Actor theRock = new Actor("Dwayne", "Johnson");
        Movie movie = new Movie("Jumanju", "Argentina ahre", LocalDate.of(2018, 1, 1),
                "Director", Collections.singletonList(theRock));

        moviesRepository.save(movie);


        List<Movie> findByTitle = moviesRepository.findByTitle(movie.getTitle());

        assertThat(findByTitle).extracting(Movie::getTitle).containsOnly(movie.getTitle());

    }
}