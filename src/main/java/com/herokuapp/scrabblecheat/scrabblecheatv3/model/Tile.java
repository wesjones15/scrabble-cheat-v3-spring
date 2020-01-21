package com.herokuapp.scrabblecheat.scrabblecheatv3.model;

public class Tile {
    private Boolean tripleWord;
    private Boolean tripleLetter;
    private Boolean doubleWord;
    private Boolean doubleLetter;

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
