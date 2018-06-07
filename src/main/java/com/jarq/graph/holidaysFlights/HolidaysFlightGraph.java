package com.jarq.graph.holidaysFlights;

import java.util.Map;
import java.util.Set;

public interface HolidaysFlightGraph {

    boolean addConnection(Airport startPoint, Airport destination, double distance, double cost);
    int countAirports();
    int countConnections();
    boolean hasConnection(Airport startPoint, Airport destination);
    Set<Airport> getAirports();
    Set<Connection> getConnections();
    Map<Airport, Set<Connection>> getAdjacent();
}
