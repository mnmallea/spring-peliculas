package peliculas.models;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Embeddable
public class Actor {

    @NotNull
    private String name;

    @NotNull
    private String surname;

    public Actor(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Actor() {
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
