package com.herokuapp.scrabblecheat.scrabblecheatv3.logic;

import com.herokuapp.scrabblecheat.scrabblecheatv3.logic.utils.Helper;
import com.herokuapp.scrabblecheat.scrabblecheatv3.model.Query;
import com.herokuapp.scrabblecheat.scrabblecheatv3.model.Tile;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class TestFindScore {
    private FindScore findScore;
    private Helper h;

    @Before
    public void setUp() {
        this.findScore = new FindScore();
        this.h = new Helper();
    }

    @Test
    public void testGetBestScoringWords() {
        ArrayList<String> words = h.arrayListOf("WESTERN","STEW","STEER","STRAW","RATES");
        Query input = new Query("WEST_RN");
        Integer amount = 3;
        LinkedHashMap<String, Integer> expected = new LinkedHashMap<>();
        expected.put("WESTERN", 9);
        expected.put("STEW", 7);
        expected.put("STRAW", 7);
        LinkedHashMap<String, Integer> actual = findScore.getBestScoringWords(words, input, amount);
        for (String word : /*words){//*/expected.keySet()) {
            Assert.assertTrue(actual.containsKey(word));
            Assert.assertEquals(expected.get(word), actual.get(word));
        }
    }

    @Test
    public void testMapWordsToScores() { // query = "WEST_RN
        ArrayList<String> words = h.arrayListOf("WESTERN","STEW","STEER","STRAW","RATES");
        ArrayList<String> blankedWords = h.arrayListOf("WEST_RN","STEW","STE_R","STR_W","R_TES");
        ArrayList<Tile> tiles = h.arrayListOf(
                new Tile(),
                new Tile(),
                new Tile(),
                new Tile(),
                new Tile(),
                new Tile(),
                new Tile()
        );
        LinkedHashMap<String, Integer> expected = new LinkedHashMap<>();
        expected.put("WESTERN", 9);
        expected.put("STEW", 7);
        expected.put("STEER", 4);
        expected.put("STRAW", 7);
        expected.put("RATES", 4);
        LinkedHashMap<String, Integer> actual = findScore.mapWordsToScores(words, blankedWords, tiles);
        for (String word : words) {
            Assert.assertTrue(actual.containsKey(word));
            Assert.assertEquals(expected.get(word), actual.get(word));
        }
    }

    @Test
    public void testGetActiveWordScoreMultiplier1() {
        String word = "SEVERE";
        ArrayList<Tile> tiles = h.arrayListOf(
                new Tile(),
                new Tile(false,false,true,false),
                new Tile(),
                new Tile(true, false, false, false),
                new Tile(),
                new Tile(),
                new Tile(true, false, false, false)
                );
        Integer expected = 6;
        Integer actual = findScore.getActiveWordScoreMultiplier(word, tiles);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetActiveWordScoreMultiplier2() {
        String word = "APP";
        ArrayList<Tile> tiles = h.arrayListOf(
                new Tile(),
                new Tile(false,false,true,false),
                new Tile(),
                new Tile(true, false, false, false),
                new Tile(),
                new Tile(),
                new Tile(true, false, false, false)
        );
        Integer expected = 2;
        Integer actual = findScore.getActiveWordScoreMultiplier(word, tiles);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetPointValueOfWord1() {
        String word = "APPLE";
        ArrayList<Tile> tiles = h.arrayListOf(
                new Tile(),
                new Tile(false,false,true,false),
                new Tile(false, true, false, false),
                new Tile(true, false, false, false),
                new Tile(false, false, false, true),
                new Tile(),
                new Tile(true, false, false, false)
        );
        Integer expected = 16;// (1+9+3+1+2);
        Integer actual = findScore.getPointValueOfWord(word, tiles);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetPointValueOfWord2() {
        String word = "HISTORY";
        ArrayList<Tile> tiles = h.arrayListOf(
                new Tile(),
                new Tile(false,false,true,false),
                new Tile(false, true, false, false),
                new Tile(false, true, false, false),
                new Tile(false, false, false, true),
                new Tile(),
                new Tile(true, false, false, false)
        );
        Integer expected = 18;// (4+1+3+3+2+1+4);
        Integer actual = findScore.getPointValueOfWord(word, tiles);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetPointValueOfWord3() {
        String word = "WESTERN";
        ArrayList<Tile> tiles = h.arrayListOf(
                new Tile(),
                new Tile(),
                new Tile(),
                new Tile(),
                new Tile(),
                new Tile(),
                new Tile()
        );
        Integer expected = 10;// (4+1+1+1+1+1+1);
        Integer actual = findScore.getPointValueOfWord(word, tiles);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testConvertLetterToPointValue1() {
        String letter = "C";
        Integer expected = 3;
        Integer actual = findScore.convertLetterToPointValue(letter);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void testConvertLetterToPointValue2() {
        String letter = "_";
        Integer expected = 0;
        Integer actual = findScore.convertLetterToPointValue(letter);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void testConvertLetterToPointValue3() {
        String letter = "Z";
        Integer expected = 10;
        Integer actual = findScore.convertLetterToPointValue(letter);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetActiveLetterScoreMultiplier1() {
        Tile tile = new Tile(false, true, false, false);
        Integer expected = 3;
        Integer actual = findScore.getActiveLetterScoreMultiplier(tile);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void testGetActiveLetterScoreMultiplier2() {
        Tile tile = new Tile(true, false, false, false);
        Integer expected = 1;
        Integer actual = findScore.getActiveLetterScoreMultiplier(tile);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void testGetActiveLetterScoreMultiplier3() {
        Tile tile = new Tile(false, false, true, false);
        Integer expected = 1;
        Integer actual = findScore.getActiveLetterScoreMultiplier(tile);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void testGetActiveLetterScoreMultiplier4() {
        Tile tile = new Tile(false, false, false, true);
        Integer expected = 2;
        Integer actual = findScore.getActiveLetterScoreMultiplier(tile);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void testGetActiveLetterScoreMultiplier5() {
        Tile tile = new Tile(false, false, false, false);
        Integer expected = 1;
        Integer actual = findScore.getActiveLetterScoreMultiplier(tile);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testReinsertUnderscoresInWord1() {
        String word = "SUPERB";
        String input = "SU_ERB";
        String expected = "SU_ERB";
        String actual = findScore.reinsertUnderscoresInWord(word, input);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testReinsertUnderscoresInWord2() {
        String word = "ALLIGATOR";
        String input = "AL__IGTOR";
        String expected = "AL_IG_TOR";
        String actual = findScore.reinsertUnderscoresInWord(word, input);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testShowBlanksInList() {
        ArrayList<String> words = h.arrayListOf("ACTION", "ACTIVE", "TRACT");
        String input = "ACT__I";
        ArrayList<String> expected = h.arrayListOf("ACTI__","ACTI__", "T_AC_");
        ArrayList<String> actual = findScore.showBlanksInList(words, input);
        Assert.assertTrue(h.compareArrayLists(expected, actual));
    }
}
