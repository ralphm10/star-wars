package com.ralphm10.starwars.service;

import com.ralphm10.starwars.models.entity.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class FilmService {

    private final WebClientService webClientService;

    public FilmService(WebClientService webClientService) {
        this.webClientService = webClientService;
    }

    public int getCountWithWebClient(String character) {
        return getPeopleWithWebClient().stream()
                .filter(person -> person.getName().equalsIgnoreCase(character))
                .findFirst()
                .map(person -> person.getFilms().size())
                .orElse(0);
    }

    // try with http client https://www.baeldung.com/spring-6-http-interface
    // try with retrofit https://square.github.io/retrofit/

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
