package peliculas.repositories;

public class MovieNotFoundException extends RuntimeException{

    public MovieNotFoundException(Long id) {
        super("Movie not found. Id : " + id);
    }

}
