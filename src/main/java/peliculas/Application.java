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
            Actor samuelJackson = new Actor("Samuel L", "Jackson");
            Actor johnTravolta = new Actor("John", "Travolta");
            Actor bradPitt = new Actor("Brad", "Pitt");
            Actor diCaprio = new Actor("Leonardo", "DiCaprio");
            Actor ellenPage = new Actor("Ellen", "Page");
            Actor tomHardy = new Actor("Tom", "Hardy");
            Actor edwardNorton = new Actor("Edward", "Norton");
            Actor aneurinBarnard = new Actor("Aneurin", "Barnard");
            Actor umaThurman = new Actor("Uma", "Thurman");

            Movie pulpFiction = new Movie("Pulp Fiction", "United States",
                    LocalDate.of(1994, Month.OCTOBER, 14), "Quentin Tarantino",
                    Arrays.asList(samuelJackson, johnTravolta, umaThurman));
            Movie fightClub = new Movie("Fight Club", "United States",
                    LocalDate.of(1999, Month.OCTOBER, 15), "David Fincher",
                    Arrays.asList(bradPitt, edwardNorton));
            Movie dunkirk = new Movie("Dunkirk", "United States",
                    LocalDate.of(2017, Month.JULY, 23), "Christopher Nolan",
                    Arrays.asList(tomHardy, aneurinBarnard));
            Movie inception = new Movie("Inception", "United States",
                    LocalDate.of(2010, Month.JULY, 16), "Christopher Nolan",
                    Arrays.asList(diCaprio, ellenPage, tomHardy));
            Movie theAvengers1998 = new Movie("The Avengers", "United States",
                    LocalDate.of(1998, Month.AUGUST, 14), "Jeremiah Chechik",
                    Collections.singletonList(umaThurman));
            Movie theAvengers2012 = new Movie("The Avengers", "United States",
                    LocalDate.of(2012, Month.MAY, 4), "Joss Whedon",
                    Collections.emptyList());

            moviesRepository.save(pulpFiction);
            moviesRepository.save(fightClub);
            moviesRepository.save(dunkirk);
            moviesRepository.save(inception);
            moviesRepository.save(theAvengers1998);
            moviesRepository.save(theAvengers2012);
        });
    }
}
