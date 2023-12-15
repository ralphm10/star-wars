package com.ralphm10.starwars.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class HttpClientService {
    protected HttpResponse<String> makeGetRequest(String url) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build();

        var client = HttpClient.newHttpClient();

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
