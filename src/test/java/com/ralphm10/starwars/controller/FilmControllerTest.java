package com.ralphm10.starwars.controller;

import com.ralphm10.starwars.service.FilmService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.verify;

@SpringBootTest
class FilmControllerTest {

    @Mock
    private FilmService filmService;

    private FilmController filmController;

    @BeforeEach
    public void setup() {
        filmController = new FilmController(filmService);
    }

    @Test
    void returnsFilmCount() {
        String character = "Luke Skywalker";
        filmController.getCount(character);

        verify(filmService).getFilmCount(character);
    }
}
