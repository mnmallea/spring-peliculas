package peliculas.models;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Movie {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String country;
    private LocalDate releaseDate;
    private String director;
    @ManyToMany
    @Cascade(CascadeType.PERSIST)
    private List<Actor> cast;

    public Movie(String title, String country, LocalDate releaseDate, String director, List<Actor> cast) {
        this.title = title;
        this.country = country;
        this.releaseDate = releaseDate;
        this.director = director;
        this.cast = cast;
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
