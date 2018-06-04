package com.jarq.graph;

public class Connection {

    private final Airport firstCity;
    private final Airport secondCity;
    private double distance;
    private double cost;

    public Connection(Airport firstCity, Airport secondCity, double distance, double cost) {
        this.firstCity = firstCity;
        this.secondCity = secondCity;
        this.distance = distance;
        this.cost = cost;
    }

    public Airport getFirstCity() {
        return firstCity;
    }

    public Airport getSecondCity() {
        return secondCity;
    }

    public double getDistance() {
        return distance;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return String.format("Flight between %s - %s, distance: %f, price: %f", firstCity, secondCity, distance, cost);
    }
}
