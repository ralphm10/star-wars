package com.ralphm10.starwars.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class FileServiceTest {
    private FilmService filmService;

    @Mock
    private WebClientService webClientService;

    @BeforeEach
    void setUp() {
        filmService = new FilmService(webClientService);
    }

    @Test
    void getCountCallsWebClient() {
        String character = "Luke Skywalker";
        filmService = new FilmService(webClientService);
        ResponseEntity<PersonResponse> response = mock(ResponseEntity.class);
        when(response.getBody()).thenReturn(mock(PersonResponse.class));
        when(webClientService.makeGetRequest(any())).thenReturn(response);

        filmService.getCount(character);

        verify(webClientService).makeGetRequest(any());

    }
}
