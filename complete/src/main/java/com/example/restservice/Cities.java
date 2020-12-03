package com.example.restservice;

import org.json.JSONArray;

public class Cities {
    private String cities;
    private long id;

    public Cities(JSONArray cities, long id) {
        this.cities = String.valueOf(cities);
        this.id = id;
    }


    public String getCities() {
        return cities;
    }

    public long getId() {
        return id;
    }
}
