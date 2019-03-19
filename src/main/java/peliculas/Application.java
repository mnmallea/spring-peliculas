package peliculas;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import peliculas.models.Actor;
import peliculas.models.Movie;
import peliculas.repositories.MoviesRepository;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;


@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(MoviesRepository moviesRepository) {
        return (args -> {
            moviesRepository.saveAll(DemoData.getMovies());
        });
    }
}
