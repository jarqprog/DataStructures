package com.jarq.graph;

public interface HolidaysFlightGraph {

    boolean addConnection(Airport startPoint, Airport destination, double cost, double distance);
    int countAirports();
    int countConnections();
    Connection findShortestConnection(Airport startPoint, Airport destination);
    Connection findCheapestConnection(Airport startPoint, Airport destination);
    boolean hasConnection(Airport startPoint, Airport destination);
}
