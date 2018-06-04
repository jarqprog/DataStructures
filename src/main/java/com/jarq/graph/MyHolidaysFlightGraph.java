package com.jarq.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MyHolidaysFlightGraph implements HolidaysFlightGraph {

    private Set<Airport> airports;
    private Set<Connection> connections;
    private Map<Airport, Set<Connection>> adjacents;

    public MyHolidaysFlightGraph() {
        this.airports = new HashSet<>();
        this.connections = new HashSet<>();
        this.adjacents = new HashMap<>();
    }

    @Override
    public boolean addAirport(Airport airport) {
        return airports.add(airport);
    }

    @Override
    public boolean addAirport(String name) {
        Airport airport = new Airport(name);
        return addAirport(airport);
    }

    @Override
    public boolean addConnection(Connection connection) {
        if(! connections.add(connection)) {
            return false;
        }

        adjacents.putIfAbsent(connection.getFirstCity(), new HashSet<>());
        adjacents.putIfAbsent(connection.getSecondCity(), new HashSet<>());

        adjacents.get(connection.getFirstCity()).add(connection);
        adjacents.get(connection.getSecondCity()).add(connection);

        return true;
    }

    @Override
    public boolean addConnection(Airport first, Airport second, double cost, double distance) {
        Connection connection = new Connection(first, second, cost, distance);
        return addConnection(connection);
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Connection findShortest(Airport first, Airport second) {
        return null;
    }

    @Override
    public Connection findCheapest(Airport first, Airport second) {
        return null;
    }
}
