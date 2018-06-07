package com.jarq.graph.holidaysFlights;

class Airport {

    private final String name;

    Airport(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
