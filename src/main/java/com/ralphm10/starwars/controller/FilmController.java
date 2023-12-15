package com.ralphm10.starwars.controller;

import com.ralphm10.starwars.models.entity.Person;
import com.ralphm10.starwars.service.FilmService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class FilmController {
    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/films-webclient")
    public String getCountWebClient(@RequestParam String character) {
        int count = filmService.getCountWithWebClient(character);
        return character + " appeared in " + count + " films";
    }

    @GetMapping("/films-retrofit")
    public List<Person> getCountRetrofit() throws IOException {

        return filmService.getPeopleWithRetrofit();
    }
}
