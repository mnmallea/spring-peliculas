package models;

import java.time.LocalDate;
import java.util.List;

public class Movie {
    private String title;
    private String country;
    private LocalDate releaseDate;
    private String director;
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
