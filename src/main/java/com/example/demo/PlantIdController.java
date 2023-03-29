package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlantIdController {

    @Autowired
    private PlantIdService plantIdService;

    @PostMapping("/identify")
    public ResponseEntity<String> identifyPlant(@RequestBody PlantIdentificationRequest request) {
        return plantIdService.identifyPlant(request.getImageBase64());
    }
}
