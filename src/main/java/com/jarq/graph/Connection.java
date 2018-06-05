package com.jarq.graph;

import java.util.Objects;

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
        return String.format("Flight between %s - %s, distance: %.2f, price: %.2f", firstCity, secondCity, distance, cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.firstCity, this.secondCity, (int) this.cost, (int) this.distance);
    }

    @Override
    public boolean equals(Object object){
        if(this == object) {
            return true;
        }

        if( !(object instanceof Connection) ) {
            return false;
        }

        Connection con = (Connection) object;
        return
                this.firstCity.equals(con.firstCity) &&
                this.secondCity.equals(con.secondCity) &&
                (int) this.cost == (int) con.cost &&
                (int) this.distance == (int) con.distance;
    }
}
