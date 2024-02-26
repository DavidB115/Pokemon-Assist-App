package com.techelevator.dndapi.model;

import java.util.ArrayList;

public class Root {
    private int count;

    private ArrayList<Result> results;

    public Root(){};
    public Root(int count, ArrayList<Result>  results) {
        this.count = count;
        this.results = results;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<Result> getResults() {
        return results;
    }

    public void setResults(ArrayList<Result> results) {
        this.results = results;
    }
}
