package com.example.demo;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.util.Base64;

@Service
public class PlantIdService {
    private static final String PLANT_ID_API_URL = "https://api.plant.id/v2/identify";
    private static final String API_KEY = "9C9AVV4wyvXWLGC72Uf52PDO5oWOjYGroouvqLQNQcmcwOaT4o";

    public ResponseEntity<String> identifyPlant(String imageBase64) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Api-Key", API_KEY);

        JSONObject data = new JSONObject();
        data.put("organs", new JSONArray().put("leaf").put("flower").put("fruit").put("bark").put("habit"));
        data.put("images", new JSONArray().put(imageBase64));
        data.put("modifiers", new JSONArray().put("crops_fast").put("similar_images"));
        data.put("plant_language", "en");
        data.put("plant_details", new JSONArray().put("common_names").put("url").put("name_authority").put("wiki_description").put("taxonomy").put("synonyms"));

        HttpEntity<String> entity = new HttpEntity<>(data.toString(), headers);
        return restTemplate.exchange(PLANT_ID_API_URL, HttpMethod.POST, entity, String.class);
    }

    public static String base64EncodeFromFile(String fileString) throws Exception {
        File file = new File(fileString);
        FileInputStream fis = new FileInputStream(file);
        String res = Base64.getEncoder().encodeToString(fis.readAllBytes());
        fis.close();
        return res;
    }
}
