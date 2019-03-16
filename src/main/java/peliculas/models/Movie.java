package peliculas.models;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "Movies")
public class Movie {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String country;
    private LocalDate releaseDate;
    private String director;
    @ElementCollection
    private List<Actor> cast;

    public Movie(String title, String country, LocalDate releaseDate, String director, List<Actor> cast) {
        this.title = title;
        this.country = country;
        this.releaseDate = releaseDate;
        this.director = director;
        this.cast = cast;
    }

    public Movie() {

    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCountry() {
        return country;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public String getDirector() {
        return director;
    }

    public List<Actor> getCast() {
        return cast;
    }
}
