package com.ralphm10.starwars.controller;

import com.ralphm10.starwars.service.FilmService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilmController {
    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/films")
    public String getCount(@RequestParam String character) {
        int count = filmService.getFilmCount(character);
        return character + " appeared in " + count + " films";
    }
}
