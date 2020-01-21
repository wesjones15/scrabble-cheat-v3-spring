package com.herokuapp.scrabblecheat.scrabblecheatv3.logic;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestWordFilter {
    @Test
    public void testGetScrabbleWordList1() {
        WordFilter wordFilter = new WordFilter();
        ArrayList<String> words = wordFilter.getScrabbleWordList();
        Assert.assertEquals(276643, words.size());
    }

    @Test
    public void testGetScrabbleWordList2() {
        WordFilter wordFilter = new WordFilter();
        ArrayList<String> words = wordFilter.getScrabbleWordList();
        Assert.assertEquals("AA", words.get(0));
    }

    @Test
    public void testBuildListOfUnusedLetters() {
        WordFilter wordFilter = new WordFilter();
        String letters = "ABCDEFG";
        String[] expected = {"H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        ArrayList<String> actual = wordFilter.buildListOfUnusedLetters(letters);
        for (int i = 0; i < expected.length; i++) {
            Assert.assertEquals(expected[i], actual.get(i));
        }
    }

    @Test
    public void testFilterWordsByLength1() {
        WordFilter wordFilter = new WordFilter();
        String letters = "TT";
        ArrayList<String> words = wordFilter.getScrabbleWordList();
        Integer expected = 124;
        Integer actual = wordFilter.filterWordsByLength(words, letters).size();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testFilterWordsByLength2() {
        WordFilter wordFilter = new WordFilter();
        String letters = "ABCDEFG";
        ArrayList<String> words = wordFilter.getScrabbleWordList();
        Integer expected = 77112;
        Integer actual = wordFilter.filterWordsByLength(words, letters).size();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testFilterWordsByLength3() {
        WordFilter wordFilter = new WordFilter();
        String letters = "JBIRRD";
        ArrayList<String> words = getMockWords();
        Integer expected = 5;
        Integer actual = wordFilter.filterWordsByLength(words, letters).size();
        Assert.assertEquals(expected, actual);
    }

    private ArrayList<String> getMockWords() {
        String[] mockWords = {"GASTROINTESTINAL", "JAGUAR", "TRICYCLE", "LONG", "ASK", "REPTILE", "FLAG", "POOL"};
        return new ArrayList<>(Arrays.asList(mockWords));
    }

}
