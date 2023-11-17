package com.ralphm10.starwars.service;

import com.ralphm10.starwars.models.entity.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class FilmService {

    private final WebClientService webClientService;

    public FilmService(WebClientService webClientService) {
        this.webClientService = webClientService;
    }

    public int getCount(String character) {
        return getPeople().stream()
                .filter(person -> person.getName().equalsIgnoreCase(character))
                .findFirst()
                .map(person -> person.getFilms().size())
                .orElse(0);
    }

    public List<Person> getPeople() {
        String url = "https://challenges.hackajob.co/swapi/api/people/";
        List<Person> people = new ArrayList<>();

        while (url != null) {
            ResponseEntity<PersonResponse> response = webClientService.makeGetRequest(url);
            PersonResponse responseBody = response.getBody();
            if (Objects.nonNull(responseBody)) {
                people.addAll(responseBody.getPeople());
                url = responseBody.getNextPage();
            }
        }

        return people;
    }
}
