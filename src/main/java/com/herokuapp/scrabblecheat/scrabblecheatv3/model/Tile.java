package com.herokuapp.scrabblecheat.scrabblecheatv3.model;

public class Tile {
    private Boolean tripleWord;
    private Boolean tripleLetter;
    private Boolean doubleWord;
    private Boolean doubleLetter;

    public Tile() {
        this.tripleWord = false;
        this.tripleLetter = false;
        this.doubleWord = false;
        this.doubleLetter = false;
    }

    public Tile(Boolean tripleWord, Boolean tripleLetter, Boolean doubleWord, Boolean doubleLetter) {
        this.tripleWord = tripleWord;
        this.tripleLetter = tripleLetter;
        this.doubleWord = doubleWord;
        this.doubleLetter = doubleLetter;
    }

    public Boolean getTripleWord() {
        return tripleWord;
    }

    public void setTripleWord(Boolean tripleWord) {
        this.tripleWord = tripleWord;
    }

    public Boolean getTripleLetter() {
        return tripleLetter;
    }

    public void setTripleLetter(Boolean tripleLetter) {
        this.tripleLetter = tripleLetter;
    }

    public Boolean getDoubleWord() {
        return doubleWord;
    }

    public void setDoubleWord(Boolean doubleWord) {
        this.doubleWord = doubleWord;
    }

    public Boolean getDoubleLetter() {
        return doubleLetter;
    }

    public void setDoubleLetter(Boolean doubleLetter) {
        this.doubleLetter = doubleLetter;
    }
}
