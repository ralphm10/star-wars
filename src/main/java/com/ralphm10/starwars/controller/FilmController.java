package com.ralphm10.starwars.controller;

import com.ralphm10.starwars.service.FilmService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;

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

    @GetMapping("/films-http")
    public String getCountHttpClient(@RequestParam String character) throws URISyntaxException, IOException, InterruptedException {
        int count = filmService.getCountWithHttpClient(character);
        return character + " appeared in " + count + " films";
    }
}
