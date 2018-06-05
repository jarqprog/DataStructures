package com.jarq.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MyHolidaysFlightGraph implements HolidaysFlightGraph {

    private Set<Airport> airports;
    private Set<Connection> connections;
    private Map<Airport, Set<Connection>> adjacent;

    public MyHolidaysFlightGraph() {
        this.airports = new HashSet<>();
        this.connections = new HashSet<>();
        this.adjacent = new HashMap<>();
    }

    @Override
    public boolean addConnection(Airport startPoint, Airport destination, double cost, double distance) {
        Connection connection = new Connection(startPoint, destination, cost, distance);
        if(connections.contains(connection)) {
            return false;
        }

        // register airports
        airports.add(startPoint);
        airports.add(destination);
        adjacent.putIfAbsent(connection.getFirstCity(), new HashSet<>());
        adjacent.putIfAbsent(connection.getSecondCity(), new HashSet<>());

        // register connections bounded with airports
        connections.add(connection);
        adjacent.get(connection.getFirstCity()).add(connection);
        adjacent.get(connection.getSecondCity()).add(connection);

        return true;
    }

    @Override
    public int countAirports() {
        return airports.size();
    }

    @Override
    public int countConnections() {
        return connections.size();
    }

    @Override
    public Connection findShortestConnection(Airport startPoint, Airport destination) {
        return null;
    }

    @Override
    public Connection findCheapestConnection(Airport startPoint, Airport destination) {
        return null;
    }
}
