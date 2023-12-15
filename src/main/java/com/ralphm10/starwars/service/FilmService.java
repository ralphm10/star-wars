package com.ralphm10.starwars.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ralphm10.starwars.models.entity.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class FilmService {

    private final WebClientService webClientService;
    private final HttpClientService httpClientService;
    private final ObjectMapper objectMapper;


    public FilmService(WebClientService webClientService, HttpClientService httpClientService) {
        this.webClientService = webClientService;
        this.httpClientService = httpClientService;
        this.objectMapper = new ObjectMapper();
    }

    public int getCountWithWebClient(String character) {
        return getPeopleWithWebClient().stream()
                .filter(person -> person.getName().equalsIgnoreCase(character))
                .findFirst()
                .map(person -> person.getFilms().size())
                .orElse(0);
    }

    public int getCountWithHttpClient(String character) throws URISyntaxException, IOException, InterruptedException {
        return getPeopleWithHttpClient().stream()
                .filter(person -> person.getName().equalsIgnoreCase(character))
                .findFirst()
                .map(person -> person.getFilms().size())
                .orElse(0);
    }

    public List<Person> getPeopleWithWebClient() {
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

    public List<Person> getPeopleWithHttpClient() throws URISyntaxException, IOException, InterruptedException {
        String url = "https://challenges.hackajob.co/swapi/api/people/";
        List<Person> people = new ArrayList<>();

        while (url != null) {
            HttpResponse<String> response = httpClientService.makeGetRequest(url);
            PersonResponse responseBody = objectMapper.readValue(response.body(), PersonResponse.class);
            if (Objects.nonNull(responseBody)) {
                people.addAll(responseBody.getPeople());
                url = responseBody.getNextPage();
            }
        }

        return people;
    }

    public List<Person> getPeopleWithRetrofit() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://challenges.hackajob.co/swapi/api/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        RetroFitService service = retrofit.create(RetroFitService.class);


        Call<PersonResponse> call = service.getPeople();
        Response<PersonResponse> response = call.execute();

        if (response.isSuccessful()) {
            return response.body().getPeople();
        } else {
            throw new IOException(response.errorBody() != null ? response.errorBody().string() : "Unknown error");
        }
        // this returns first page only
    }
}

