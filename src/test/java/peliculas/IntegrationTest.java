package peliculas;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import peliculas.repositories.MoviesRepository;

import javax.json.Json;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "target/snippets/movies")
public class IntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    MoviesRepository moviesRepository;

    @Test
    public void gettingMoviesIndex() throws Exception {
        mockMvc.perform(get("/movies"))
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(document("index"));
    }

    @Test
    public void searchByNameExactly() throws Exception {
        mockMvc.perform(get("/movies").param("name", "Inception"))
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(document("search"));
    }

    @Test
    public void searchByName() throws Exception {
        mockMvc.perform(get("/movies").param("nameLike", "a"))
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(document("search"));
    }

    @Test
    public void pageSizeIsTheRequested() throws Exception {
        mockMvc.perform(get("/movies").param("page", "0").param("size", "1"))
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(1)))
                .andDo(document("paging"));
    }

    @Test
    public void creatingAMovie() throws Exception {
        String json = Json.createObjectBuilder()
                .add("title", "Django Unchained")
                .add("country", "United States")
                .add("releaseDate", "2012-12-25")
                .add("director", "Quentin Tarantino")
                .add("cast", Json.createArrayBuilder()
                        .add(Json.createObjectBuilder().add("name", "Samuel").add("surname", "Jackson"))
                        .add(Json.createObjectBuilder().add("name", "Cristoph").add("surname", "Waltz"))
                        .add(Json.createObjectBuilder().add("name", "Leonardo").add("surname", "DiCaprio"))
                ).build().toString();

        mockMvc.perform(post("/movies")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(json)
        ).andExpect(status().is(201))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.*", hasSize(6)))
                .andExpect(jsonPath("$.id").exists())
                .andDo(document("post"));
    }

    @Test
    public void modifyingAMovie() throws Exception {
        String json = Json.createObjectBuilder().add("title", "hola").build().toString();
        mockMvc.perform(patch("/movies/2")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json))
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.title").value("hola"))
                .andDo(document("patch"));

    }

    @Test
    public void deletingAMovie() throws Exception {
        mockMvc.perform(delete("/movies/1"))
                .andExpect(status().is(200))
                .andDo(document("delete"));
    }
}
