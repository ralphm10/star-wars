package com.ralphm10.starwars.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WebClientService {

    private final WebClient client = WebClient.create();

    protected ResponseEntity<PersonResponse> makeGetRequest(String url) {
        return client.get()
                .uri(url)
                .retrieve()
                .toEntity(PersonResponse.class)
                .block();
    }
}