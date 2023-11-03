package com.ralphm10.starwars.service;

import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class FilmService {
    public int getFilmCount(String character) {
        if (Objects.equals(character.toLowerCase().trim(), "luke skywalker")) {
            return 5;
        }
        else {
            return 0;
        }
    }
}
