package peliculas.models;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "Movies")
public class Movie {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String country;

    @NotNull
    private LocalDate releaseDate;

    @NotNull
    private String director;

    @ElementCollection
    @NotNull
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setCast(List<Actor> cast) {
        this.cast = cast;
    }
}
