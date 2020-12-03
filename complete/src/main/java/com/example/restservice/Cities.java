package com.example.restservice;

public class Cities {
    private Object[] cities;
    private long id;

    public Cities(Object[] cities, long id) {
        this.cities = cities;
        this.id = id;
    }

    public Cities(String error, long id) {
        this.id = id;
    }

    public Object[] getCities() {
        return cities;
    }

    public long getId() {
        return id;
    }
}
