package com.example.restservice;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class CityMeteoController {
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/cities")
    public Cities cities() {
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray =null;
        try {
            jsonArray = (JSONArray) jsonParser.parse(new FileReader(System.getProperty("user.dir")+"/src/main/java/com/example/restservice/cities.json"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Cities(jsonArray.toArray(),counter.incrementAndGet());
    }

    @GetMapping("/cities/{city}")
    public String cityMeteo(@PathVariable String city,@RequestParam(value = "days", defaultValue = "1") Integer days) {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = null;
        Object value=null;
        try {
            jsonObject = (JSONObject) jsonParser.parse(new FileReader(System.getProperty("user.dir")+"/src/main/java/com/example/restservice/meteo.json"));
            value=  jsonObject.get(city);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (value!=null)
            return value.toString()+' '+days.toString();
        else
            return "City not found "+days.toString();
    }
}
