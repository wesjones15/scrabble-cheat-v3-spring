package com.herokuapp.scrabblecheat.scrabblecheatv3.logic;

import com.herokuapp.scrabblecheat.scrabblecheatv3.model.Query;
import com.herokuapp.scrabblecheat.scrabblecheatv3.model.Result;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class LogicService {
    public Result getWords(Query input) {
        WordFilter wordFilter = new WordFilter();
        FindScore findScore = new FindScore();
        ArrayList<String> words = wordFilter.getScrabbleWordsFromLetters(input.getQuery());
        LinkedHashMap<String, Integer> wordsAndScores = findScore.getBestScoringWords(words, input, 3);
        return new Result(wordsAndScores);
    }
}
