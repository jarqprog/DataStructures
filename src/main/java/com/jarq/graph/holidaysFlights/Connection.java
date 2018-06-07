package com.jarq.graph.holidaysFlights;

import java.util.Objects;

class Connection {

    private final Airport startPoint;
    private final Airport destination;
    private double distance;
    private double cost;

    Connection(Airport startPoint, Airport destination, double distance, double cost) {
        this.startPoint = startPoint;
        this.destination = destination;
        this.distance = distance;
        this.cost = cost;
    }

    Airport[] getAirports() {
        return new Airport[]{startPoint, destination};
    }

    Airport getStartPoint() {
        return startPoint;
    }

    Airport getDestination() {
        return destination;
    }

    double getDistance() {
        return distance;
    }

    double getCost() {
        return cost;
    }

    void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return String.format("%s - %s, distance: %.2f, price: %.2f", startPoint, destination, distance, cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.startPoint, this.destination, (int) this.cost, (int) this.distance);
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
                this.startPoint.equals(con.startPoint) &&
                this.destination.equals(con.destination) &&
                (int) this.cost == (int) con.cost &&
                (int) this.distance == (int) con.distance;
    }
}
