package peliculas.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import peliculas.models.Movie;
import peliculas.repositories.MoviesRepository;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

@RunWith(SpringRunner.class)
@WebMvcTest
public class MoviesControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    MoviesRepository moviesRepository;

    private Movie movie;

    private String getJsonMovie() {
        JsonArrayBuilder cast = Json.createArrayBuilder()
                .add(Json.createObjectBuilder().add("name", "Hola").add("surname", "chau"))
                .add(Json.createObjectBuilder().add("name", "sdfdfs").add("surname", "dfas"));


        JsonObject jsonObject = Json.createObjectBuilder()
                .add("title", "Los increibles")
                .add("country", "USA")
                .add("releaseDate", "2018-01-01")
                .add("director", "Nose")
                .add("cast", cast)
                .build();


        return jsonObject.toString();
    }

    @Test
    public void postMovie() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/movies").accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.getJsonMovie()))
                .andExpect(MockMvcResultMatchers.status().is(201));
    }

    @Test
    public void shouldNotBeAbleToPostAnInvalidMovie() throws Exception {
        String json = Json.createObjectBuilder().add("title", "hola").build().toString();

        mockMvc.perform(MockMvcRequestBuilders.post("/movies").accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().is(400));

    }
}