package com.jarq.graph;

public class Airport {

    private final String name;

    public Airport(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Airport: " + name;
    }
}
