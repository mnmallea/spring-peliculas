package peliculas;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import peliculas.models.Actor;
import peliculas.models.Movie;
import peliculas.repositories.ActorsRepository;
import peliculas.repositories.MoviesRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;


@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(MoviesRepository moviesRepository, ActorsRepository actorsRepository) {
        return (args -> {
            Actor theRock = new Actor("Dwayne", "Johnson");
            Actor actor = new Actor("Sebastian", "Estevanez");

            actorsRepository.save(theRock);
            actorsRepository.save(actor);
            Movie movie = new Movie("Jumanju", "Argentina ahre", LocalDate.of(2018, 1, 1),
                    "Director", Collections.singletonList(theRock));
            Movie worstMovie = new Movie("asdfa", "usa", LocalDate.now(), "asdf", Arrays.asList(actor, theRock));

            moviesRepository.save(movie);
            moviesRepository.save(worstMovie);

        });
    }
}
