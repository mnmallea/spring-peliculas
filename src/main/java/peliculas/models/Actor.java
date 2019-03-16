package peliculas.models;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Embeddable
public class Actor {
    private String name;
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
