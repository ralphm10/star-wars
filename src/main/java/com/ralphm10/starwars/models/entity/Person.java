package com.ralphm10.starwars.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {
    private String name;
    private String url;
    private List<String> films;
}
