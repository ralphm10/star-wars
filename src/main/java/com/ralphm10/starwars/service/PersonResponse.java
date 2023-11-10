package com.ralphm10.starwars.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ralphm10.starwars.models.entity.Person;
import lombok.Getter;

import java.util.List;

@Getter
public class PersonResponse {
    @JsonProperty("results")
    private List<Person> people;
    @JsonProperty("next")
    private String nextPage;
}
