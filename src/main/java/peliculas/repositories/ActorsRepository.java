package peliculas.repositories;

import org.springframework.data.repository.CrudRepository;
import peliculas.models.Actor;

public interface ActorsRepository extends CrudRepository<Actor, Long> {

    <S extends Actor> S save(S s);
}
