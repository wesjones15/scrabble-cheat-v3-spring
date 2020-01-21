package com.herokuapp.scrabblecheat.scrabblecheatv3.service;

import com.herokuapp.scrabblecheat.scrabblecheatv3.model.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class ScrabbleService {
    public Map<String, Integer> getThreeBestScoringWords(Query input) {
        return null;
    }

    public ResponseEntity<String> testThatItsWorking() {
        return new ResponseEntity<String>("Hello there", HttpStatus.OK);
    }

    public ResponseEntity<Map<String, Integer>> testIsMapWorking() {
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("WESTERN", 10);
        map.put("GASTROINTESTINAL", 13);
        map.put("JESUS", 7);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
