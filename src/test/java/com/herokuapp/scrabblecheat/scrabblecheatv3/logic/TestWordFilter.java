package com.herokuapp.scrabblecheat.scrabblecheatv3.logic;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class TestWordFilter {
    @Test
    public void testGetScrabbleWordsFromLetters() {
        WordFilter wordFilter = new WordFilter();
        String letters = "WHISKEY";
        ArrayList<String> out = wordFilter.getScrabbleWordsFromLetters(letters);
        Assert.assertEquals(82, out.size());
        for (int i = 0; i < out.size(); i++) {
            System.out.println(out.get(i));
        }
    }

    @Test
    public void testGetScrabbleWordsFromLetters2() {
        WordFilter wordFilter = new WordFilter();
        String letters = "APP_DE";
        ArrayList<String> out = wordFilter.getScrabbleWordsFromLetters(letters);
        Assert.assertEquals(434, out.size());
        for (int i = 0; i < out.size(); i++) {
            System.out.println(out.get(i));
        }
    }

    @Test
    public void testGetScrabbleWordsFromLetters3() {
        WordFilter wordFilter = new WordFilter();
        String letters = "_____";
        ArrayList<String> out = wordFilter.getScrabbleWordsFromLetters(letters);
        Assert.assertEquals(20007, out.size());
        for (int i = 0; i < out.size(); i++) {
            System.out.println(out.get(i));
        }
    }

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
        Integer expected = 4;
        Integer actual = wordFilter.filterWordsByLength(words, letters).size();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testFilterWordsByUnusedLetters() {
        WordFilter wordFilter = new WordFilter();
        ArrayList<String> words = getMockWords();
        String letters = "SAFD";
        String[] expected = {"A", "AAS", "ASDF"};
        ArrayList<String> actual = wordFilter.filterWordsByUnusedLetters(words, letters);
        for (int i = 0; i < expected.length; i++) {
            Assert.assertEquals(expected[i], actual.get(i));
        }
    }

    @Test
    public void testFilterWordsByUnusedLettersUnderscore() {
        WordFilter wordFilter = new WordFilter();
        ArrayList<String> words = arrayListOf("SAFE","SDFS","LLOK","SLAP","FLAG");
        String letters = "SAF_";
        String[] expected = {"SAFE","SDFS"};
        ArrayList<String> actual = wordFilter.filterWordsByUnusedLetters(words, letters);
        for (int i = 0; i < expected.length; i++) {
            Assert.assertTrue(actual.contains(expected[i]));
        }
    }

    @Test
    public void testFilterWordsByLetterFrequency() {
        WordFilter wordFilter = new WordFilter();
        ArrayList<String> words = arrayListOf("A","AA","AAA","AAAA","AAAAA");
        String letters = "AAA";
        String[] expected = {"A","AA","AAA"};
        ArrayList<String> actual = wordFilter.filterWordsByLetterFrequency(words, letters);
        for (int i = 0; i < expected.length; i++) {
            Assert.assertTrue(actual.contains(expected[i]));
        }
    }

    @Test
    public void testFilterWordsByLetterFrequencyWithUnderscore() {
        WordFilter wordFilter = new WordFilter();
        ArrayList<String> words = arrayListOf("A","AAF","AAA","AAAA","AAAAA");
        String letters = "AA_";
        String[] expected = {"A","AAF","AAA"};
        ArrayList<String> actual = wordFilter.filterWordsByLetterFrequency(words, letters);
        for (int i = 0; i < expected.length; i++) {
            Assert.assertTrue(actual.contains(expected[i]));
        }
        Assert.assertEquals(expected.length, actual.size());
    }

    @Test
    public void testFilterWordsByLetterFrequencyWithUnderscore2() {
        WordFilter wordFilter = new WordFilter();
        String letters = "APP_DE";
        ArrayList<String> words = arrayListOf("APPA","PAPA","APP","AAAF","ADAPT","ADDED","AAPDED","DEEP","PAD","DIP","DIPPA");

        ArrayList<String> expected = arrayListOf("APPA","PAPA","APP","DEEP","PAD","DIP","DIPPA");
        ArrayList<String> actual = wordFilter.filterWordsByLetterFrequency(words, letters);

        System.out.println("expected\tactual");
        System.out.println(expected.size() + "\t\t" + actual.size());
        for (int i = 0; i < expected.size(); i++) {
            System.out.println(expected.get(i) + "\t\t" + actual.get(i));
            Assert.assertEquals(expected.get(i), actual.get(i));
        }
    }

    private ArrayList<String> getMockWords() {
        return arrayListOf("A", "AAS","ASDF","ASDFG","ASFGHFD");
    }

    private ArrayList<String> arrayListOf(String... words) {
        return new ArrayList<>(Arrays.asList(words));
    }
}
