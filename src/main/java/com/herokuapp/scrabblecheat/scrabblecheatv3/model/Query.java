package com.herokuapp.scrabblecheat.scrabblecheatv3.model;

import java.util.ArrayList;

public class Query {
    private String query;
    private ArrayList<Tile> tiles;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public void setTiles(ArrayList<Tile> tiles) {
        this.tiles = tiles;
    }
}
