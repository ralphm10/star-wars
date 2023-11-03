package com.ralphm10.starwars.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class FileServiceTest {
    private FilmService filmService;

    @BeforeEach
    void setUp() {
        filmService = new FilmService();
    }

    @Test
    void returnsCorrectFilmCountForCharacter() {
        String character = "Luke Skywalker";
        int expectedCount = 5;
        filmService = new FilmService();
        int result = filmService.getFilmCount(character);

        assertEquals(expectedCount, result);
    }

    @Test
    void returnsZeroForCharacterWithNoFilms() {
        String character = "Some Character";
        int expectedCount = 0;
        int result = filmService.getFilmCount(character);

        assertEquals(expectedCount, result);
    }

    @Test
    void trimsCharacterString() {
        String character = " Luke Skywalker ";
        int expectedCount = 5;
        filmService.getFilmCount(character);

        int result = filmService.getFilmCount(character);

        assertEquals(expectedCount, result);
    }

    @Test
    void isCaseInsensitive() {
        String character = "luke skywalker";
        int expectedCount = 5;

        int result = filmService.getFilmCount(character);

        assertEquals(expectedCount, result);
    }
}
