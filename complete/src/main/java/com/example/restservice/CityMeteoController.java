package com.example.restservice;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class CityMeteoController {
    private final AtomicLong counter = new AtomicLong();

    @CrossOrigin(origins = "http://localhost:8888") //Change for the web client address
    @GetMapping("/cities")
    public Cities cities() {
        String resourceName = System.getProperty("user.dir")+"/src/main/java/com/example/restservice/cities.json";

        InputStream is = null;
        try {
            is = new FileInputStream(resourceName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (is == null) {
            throw new NullPointerException("Cannot find resource file " + resourceName);
        }

        JSONTokener tokener = new JSONTokener(is);


        JSONArray object = new JSONArray(tokener);


        return new Cities(object,counter.incrementAndGet());
    }

    @CrossOrigin(origins = "http://localhost:8888") //Change for the web client address
    @GetMapping("/cities/{city}")
    public String cityMeteo(@PathVariable String city,@RequestParam(value = "days", defaultValue = "1") String days) {


        String resourceName = System.getProperty("user.dir")+"/src/main/java/com/example/restservice/meteo.json";

        InputStream is = null;
        try {
            is = new FileInputStream(resourceName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (is == null) {
            throw new NullPointerException("Cannot find resource file " + resourceName);
        }

        JSONTokener tokener = new JSONTokener(is);
        JSONObject object = new JSONObject(tokener);

        JSONObject value= null;
        try{
            value=object.getJSONObject(city);
        } catch (JSONException e){

            JSONObject json = new JSONObject();
            json.put("error", "That city doesn't exist");
            json.put("id",counter.incrementAndGet());

            return  json.toString();
        }


        String intregex = "[1-5]";

        if(!days.matches(intregex)) {
            JSONObject json = new JSONObject();
            json.put("error", "Days number not allowed ");
            json.put("id",counter.incrementAndGet());

            return  json.toString();
        }



        if (value!=null){
            JSONObject item = new JSONObject();
            JSONObject json = new JSONObject();

            for(int i=1;i<=Integer.parseInt(days);i++){
                item.put(String.valueOf(i),value.get(String.valueOf(i)));
            }
            json.put(city,item);
            json.put("id",counter.incrementAndGet());
            return json.toString();

        }
        else {
            JSONObject json = new JSONObject();
            json.put("error", "City not available");
            json.put("id", counter.incrementAndGet());

            return json.toString();
        }
    }
}
