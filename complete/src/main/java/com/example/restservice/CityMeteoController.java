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
        try {
            JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader(System.getProperty("user.dir")+"/src/main/java/com/example/restservice/cities.json"));

            return new Cities(jsonArray.toArray(),counter.incrementAndGet());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    @GetMapping("/cities/{city}")
    public String cityMeteo(@PathVariable String city,@RequestParam(value = "days", defaultValue = "1") Integer days) {
        //String value = (String) jsonObject.get("key_name");
        return city+' '+days.toString();
    }
}
