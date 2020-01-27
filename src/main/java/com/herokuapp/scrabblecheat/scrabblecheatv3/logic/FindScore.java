package com.herokuapp.scrabblecheat.scrabblecheatv3.logic;

import com.herokuapp.scrabblecheat.scrabblecheatv3.model.Query;
import com.herokuapp.scrabblecheat.scrabblecheatv3.model.Tile;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Implementing Multipliers on Scrabble Tiles
 * first try will return score assuming word is placed on board starting on leftmost tile
 * future:
 *      try different placements on seven tiles to get best score
 *      display that word position on frontend
 *      maybe require word connect to existing tile on board?
 *      obviously i need to refactor the business logic a bit as well
 */
public class FindScore {
    public LinkedHashMap<String, Integer> getBestScoringWords(ArrayList<String> words, Query input, Integer amount) {
        // Add blanks into words when getting score
        ArrayList<String> blankedWords = input.getQuery().contains("_") ?
                showBlanksInList(words, input.getQuery()) : words;

        LinkedHashMap<String, Integer> map = mapWordsToScores(words, blankedWords, input.getTiles());
        LinkedHashMap<String, Integer> sorted = new LinkedHashMap<>();
        map.entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue((a, b) -> b.compareTo(a)))
            .limit(amount)
            .forEach(entry -> {
                sorted.put(entry.getKey(), entry.getValue());
            }
        );
        return sorted;
    }

    public LinkedHashMap<String, Integer> mapWordsToScores(ArrayList<String> words, ArrayList<String> blankedWords, ArrayList<Tile> tiles) {
        LinkedHashMap<String, Integer> wordMap = new LinkedHashMap<>();
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            String blankedWord = blankedWords.get(i);

            Integer wordScoreMultiplier = getActiveWordScoreMultiplier(word, tiles);
            Integer wordValue = getPointValueOfWord(blankedWord, tiles);

            Integer score = wordValue * wordScoreMultiplier;
            wordMap.put(word, score);
        }
        return wordMap;
    }

    public Integer getActiveWordScoreMultiplier(String word, ArrayList<Tile> tiles) {
        int multiplier = 1;
        for (int i = 0; i < word.length(); i++) {
            if (tiles.get(i).getTripleWord()) multiplier *= 3;
            else if (tiles.get(i).getDoubleWord()) multiplier *= 2;
        }
        return multiplier;
    }

    /**
     * This method applies LetterScore multipliers
     */
    public Integer getPointValueOfWord(String word, ArrayList<Tile> tiles) {
        Integer value = 0;
        for (int i = 0; i < word.length(); i++) {
            Character letter = word.charAt(i);
            Integer letterValue = convertLetterToPointValue(String.valueOf(letter));
            Integer multiplier = getActiveLetterScoreMultiplier(tiles.get(i));
            value += (letterValue * multiplier);
        }
        return value;
    }

    public Integer convertLetterToPointValue(String letter) {
        LinkedHashMap<ArrayList<String>, Integer> scores = getScoreMap();
        Integer value = 0;
        for (ArrayList<String> letters : scores.keySet()) {
            if (letters.contains(letter)) {
                value += scores.get(letters);
                break;
            }
        }
        return value;
    }

    public Integer getActiveLetterScoreMultiplier(Tile tile) {
        Integer multiplier = 1;
        if (tile.getTripleLetter()) multiplier *= 3;
        else if (tile.getDoubleLetter()) multiplier *= 2;
        return multiplier;
    }

    public String reinsertUnderscoresInWord(String word, String input) {
        StringBuilder sb = new StringBuilder();
        WordFilter wf = new WordFilter();
        for (int i = 0; i < word.length(); i++) {
            Character letter = word.charAt(i);
            Integer countInQuery = wf.getCharCount(input, letter);
            Integer countInWordToI = wf.getCharCount(word.substring(0, i), letter);
            sb.append((countInQuery >/*=*/ countInWordToI) ? letter : "_");
        }
        return sb.toString();
    }

    public ArrayList<String> showBlanksInList(ArrayList<String> words, String input) {
        // In tests, this method alters the words list even though the original list is outside the scope of this method
        // Creating a new list bypasses this issue
        // but why does this method mess stuff up outside of its scope???
        ArrayList<String> blankedWords = new ArrayList<>();
        for (int i = 0; i < words.size(); i++) {
            String word = reinsertUnderscoresInWord(words.get(i), input);
//            words.set(i, word);
            blankedWords.add(word);
        }
        return blankedWords;
    }

    private LinkedHashMap<ArrayList<String>, Integer> getScoreMap() {
        LinkedHashMap<ArrayList<String>, Integer> map = new LinkedHashMap<>();
        map.put(arrayListOf("A","E","I","O","U","L","N","S","T","R"), 1);
        map.put(arrayListOf("D","G"), 2);
        map.put(arrayListOf("B","C","M","P"), 3);
        map.put(arrayListOf("F","H","V","W","Y"), 4);
        map.put(arrayListOf("K"), 5);
        map.put(arrayListOf("J","X"), 8);
        map.put(arrayListOf("Q","Z"), 10);
        map.put(arrayListOf("_"), 0);
        return map;
    }

    private ArrayList<String> arrayListOf(String... vals) {
        return new ArrayList<>(Arrays.asList(vals));
    }

    private void printList(ArrayList<?> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

}
