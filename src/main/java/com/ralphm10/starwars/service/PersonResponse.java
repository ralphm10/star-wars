package com.ralphm10.starwars.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import com.ralphm10.starwars.models.entity.Person;
import lombok.Getter;

import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonResponse {
    @JsonProperty("results")
    @SerializedName("results")
    private List<Person> people;
    @JsonProperty("next")
    @SerializedName("next")
    private String nextPage;
}
