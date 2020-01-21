package com.herokuapp.scrabblecheat.scrabblecheatv3.controller;

import com.herokuapp.scrabblecheat.scrabblecheatv3.service.ScrabbleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@CrossOrigin(origins = "*")
@Controller
public class WordController {
    private ScrabbleService service;

    @Autowired
    public WordController(ScrabbleService service) {
        this.service = service;
    }

    @GetMapping(value = "/test/1")
    public ResponseEntity<String> getTestWords() {
        return service.testThatItsWorking();
    }

    @GetMapping(value = "/test/2")
    public ResponseEntity<Map<String, Integer>> getTestMap() {
        return service.testIsMapWorking();
    }
}
