package com.herokuapp.scrabblecheat.scrabblecheatv3.logic;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * I need to add additional methods for blank tiles
 * Blank Tile represented by "_"
 * Usage: in="ENDE_L" out="NEEDLE"
 */
public class WordFilter {

    public ArrayList<String> getScrabbleWordsFromLetters(String letters) {
        ArrayList<String> words = getScrabbleWordList();
        words = filterWordsByLength(words, letters);
        words = filterWordsByUnusedLetters(words, letters);
        words = filterWordsByLetterFrequency(words, letters);
        return words;
    }

    public ArrayList<String> filterWordsByLength(ArrayList<String> words, String letters) {
        return (ArrayList<String>) words
                .stream()
                .filter(word -> word.length() <= letters.length())
                .collect(Collectors.toList());
    }

    public ArrayList<String> filterWordsByUnusedLetters(ArrayList<String> words, String letters) {
        // if _ present in letters
        //      for each word:
        //          track number of unused letters present in word
        //          if UL count <= (# of _)
        //              keep word
        return null;
    }

    public ArrayList<String> filterWordsByLetterFrequency(ArrayList<String> words, String letters) {
        // if _ present in letters
        //      for each word:
        //          track delta of extra unused letters
        //          keep word if delta - (# of _) == 0;
        return null;
    }

    public ArrayList<String> getScrabbleWordList() {
        File file = new File("src/main/resources/scrabble_word_list.txt");
        ArrayList<String> scrabbleWords = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                scrabbleWords.add(scanner.nextLine());
            }
            scanner.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return scrabbleWords;
    }

    public ArrayList<String> buildListOfUnusedLetters(String letters) {
        ArrayList<String> unusedLetters = new ArrayList<>();
        String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        for (String letter : alphabet)
            if (!letters.contains(letter))
                unusedLetters.add(letter);
        return unusedLetters;
    }
}
