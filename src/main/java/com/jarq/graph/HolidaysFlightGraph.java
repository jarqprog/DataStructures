package com.jarq.graph;

public interface HolidaysFlightGraph {

    boolean addAirport(Airport airport);
    boolean addAirport(String name);
    boolean addConnection(Connection connection);
    boolean addConnection(Airport first, Airport second, double cost, double distance);
    int size();
    Connection findShortest(Airport first, Airport second);
    Connection findCheapest(Airport first, Airport second);
}
