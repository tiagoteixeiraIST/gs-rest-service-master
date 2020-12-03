package com.example.restservice;

public class Cities {
    private final Object[] cities;
    private final long id;

    public Cities(Object[] cities, long id) {
        this.cities = cities;
        this.id = id;
    }

    public Object[] getCities() {
        return cities;
    }

    public long getId() {
        return id;
    }
}
