package com.herokuapp.scrabblecheat.scrabblecheatv3.logic.utils;

import java.util.ArrayList;
import java.util.Arrays;

public class Helper<E extends Object> {
    public ArrayList<E> arrayListOf(E... vals) {
        return new ArrayList<>(Arrays.asList(vals));
    }

    public Boolean compareArrayLists(ArrayList<E> expected, ArrayList<E> actual) {
        boolean same = (expected.size() == actual.size());

        for (int i = 0; i < expected.size(); i++) {
            if (!same) break;
            same = expected.get(i).equals(actual.get(i));
        }

        return same;
    }
}
