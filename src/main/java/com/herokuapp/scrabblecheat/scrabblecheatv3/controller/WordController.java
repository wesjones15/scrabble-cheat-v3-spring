package com.herokuapp.scrabblecheat.scrabblecheatv3.controller;

import com.herokuapp.scrabblecheat.scrabblecheatv3.logic.WordFilter;
import com.herokuapp.scrabblecheat.scrabblecheatv3.model.Query;
import com.herokuapp.scrabblecheat.scrabblecheatv3.model.Result;
import com.herokuapp.scrabblecheat.scrabblecheatv3.service.ScrabbleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Map;

@CrossOrigin(origins = "*")
@Controller
public class WordController {
    private ScrabbleService service;

    @Autowired
    public WordController(ScrabbleService service) {
        this.service = service;
    }

    @GetMapping(value = "/words")
    public ResponseEntity<Result> getBestWords(@RequestBody Query query) {
        return service.getThreeBestScoringWords(query);
    }

    @GetMapping(value = "/test/1")
    public ResponseEntity<String> getTestWords() {
        return service.testThatItsWorking();
    }

    @GetMapping(value = "/test/2")
    public ResponseEntity<Map<String, Integer>> getTestMap() {
        return service.testIsMapWorking();
    }

    @GetMapping(value = "/test/words")
    public ResponseEntity<ArrayList<String>> getWordsFromQuery(@RequestBody Query query) {
        WordFilter wordFilter = new WordFilter();
        ArrayList<String> words = wordFilter.getScrabbleWordsFromLetters(query.getQuery());
        return new ResponseEntity<>(words, HttpStatus.OK);
    }

    @GetMapping(value = "/test/queryobject")
    public ResponseEntity<Query> getQueryObject() {
        Query query = new Query();
        return new ResponseEntity<>(query, HttpStatus.OK);
    }
}
