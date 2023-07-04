package cinema.cinemaLicenta.controller;

import cinema.cinemaLicenta.dto.FilmDTO;
import cinema.cinemaLicenta.entity.Film;
import cinema.cinemaLicenta.services.FilmService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringJUnitConfig
@WebMvcTest(FilmController.class)
public class FilmControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FilmService filmService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddFilm() throws Exception {
        FilmDTO filmDTO = new FilmDTO();
        filmDTO.setTitle("Test Film");
        filmDTO.setDescription("Test Description");
        filmDTO.setReleaseYear("2023");
        filmDTO.setDuration("120 minutes");

        when(filmService.addFilm(any(FilmDTO.class))).thenReturn(filmDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/film/add")
                        .content("{ \"title\": \"Test Film\", \"description\": \"Test Description\", \"release_year\": \"2023\", \"duration\": \"120 minutes\" }")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Test Film"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Test Description"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.release_year").value("2023"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.duration").value("120 minutes"));
    }

    @Test
    public void testGetOneFilm() throws Exception {
        Film film = new Film();
        film.setId(1L);
        film.setTitle("Test Film");
        film.setDescription("Test Description");
        film.setReleaseYear("2023");
        film.setDuration("120 minutes");

        when(filmService.getFilm(anyLong())).thenReturn(Optional.of(film));

        mockMvc.perform(MockMvcRequestBuilders.get("/film/getFilm/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Test Film"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Test Description"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.release_year").value("2023"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.duration").value("120 minutes"));
    }

    @Test
    public void testGetByName() throws Exception {
        FilmDTO filmDTO1 = new FilmDTO();
        filmDTO1.setTitle("Test Film 1");
        filmDTO1.setDescription("Test Description 1");
        filmDTO1.setReleaseYear("2023");
        filmDTO1.setDuration("120 minutes");

        FilmDTO filmDTO2 = new FilmDTO();
        filmDTO2.setTitle("Test Film 2");
        filmDTO2.setDescription("Test Description 2");
        filmDTO2.setReleaseYear("2023");
        filmDTO2.setDuration("120 minutes");

        when(filmService.getFilmByTitle(anyString())).thenReturn(Arrays.asList(filmDTO1, filmDTO2));

        mockMvc.perform(MockMvcRequestBuilders.get("/film/getByName/Test"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Test Film 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].description").value("Test Description 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].release_year").value("2023"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].duration").value("120 minutes"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].title").value("Test Film 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].description").value("Test Description 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].release_year").value("2023"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].duration").value("120 minutes"));
    }

    @Test
    public void testUpdateAFilm() throws Exception {
        FilmDTO filmDTO = new FilmDTO();
        filmDTO.setTitle("Updated Film");
        filmDTO.setDescription("Updated Description");
        filmDTO.setReleaseYear("2024");
        filmDTO.setDuration("150 minutes");

        when(filmService.updateFilm(anyLong(), anyString(), anyString(), anyString(), anyString())).thenReturn(filmDTO);

        mockMvc.perform(MockMvcRequestBuilders.put("/film/update/1/Updated%20Film/Updated%20Description/2024/150%20minutes"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Updated Film"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Updated Description"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.release_year").value("2024"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.duration").value("150 minutes"));
    }

    @Test
    public void testDeleteFilm() throws Exception {
        Long filmId = 1L;
        String expectedMessage = "Film " + filmId + " was deleted";

        mockMvc.perform(MockMvcRequestBuilders.delete("/film/delete/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedMessage));
    }
}
