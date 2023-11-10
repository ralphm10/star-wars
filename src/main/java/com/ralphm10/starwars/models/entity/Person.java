package com.ralphm10.starwars.models.entity;

import lombok.Getter;

import java.util.List;

@Getter
public class Person {
    private String name;
    private String url;
    private List<String> films;
}
