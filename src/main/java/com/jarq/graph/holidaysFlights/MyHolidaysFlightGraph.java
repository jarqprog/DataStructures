package com.jarq.graph.holidaysFlights;

import com.jarq.queue.CustomQueue;
import com.jarq.queue.ICustomQueue;

import java.util.*;

public class MyHolidaysFlightGraph implements HolidaysFlightGraph {

    private List<Airport> airports;  // use List instead of Set because of Dijkstra algorithm
    private Set<Connection> connections;
    private Map<Airport, Set<Connection>> adjacent;

    public MyHolidaysFlightGraph() {
        this.airports = new LinkedList<>();
        this.connections = new HashSet<>();
        this.adjacent = new HashMap<>();
    }

    @Override
    public boolean addConnection(Airport startPoint, Airport destination, double distance, double cost) {
        Connection connection = new Connection(startPoint, destination, distance, cost);
        if(connections.contains(connection)) {
            return false;
        }

        // register airports
        if(! airports.contains(startPoint)) {
            airports.add(0, startPoint);
        }

        if(! airports.contains(destination)) {
            airports.add(0, destination);
        }

        adjacent.putIfAbsent(connection.getStartPoint(), new HashSet<>());
        adjacent.putIfAbsent(connection.getDestination(), new HashSet<>());

        // register connections bounded with airports
        connections.add(connection);
        adjacent.get(connection.getStartPoint()).add(connection);

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
    public boolean hasConnection(Airport startPoint, Airport destination) {
        if(adjacent.get(startPoint) == null || adjacent.get(startPoint).isEmpty()) {
            return false;
        }

        ICustomQueue<Connection> queue = new CustomQueue<>();
        Set<Connection> checked = new HashSet<>();

        Connection examined;

        for(Connection con : adjacent.get(startPoint)) {
            queue.enqueue(con);
            checked.add(con);
        }

        while(! queue.isEmpty() ) {
            examined = queue.dequeue();
            if(examined.getDestination() == destination) {
                return true;
            }

            for(Airport port : examined.getAirports()) {
                if(port != startPoint) {
                    for(Connection con : adjacent.get(port)) {
                        if(con.getDestination() == destination) {
                            return true;
                        }

                        if(! checked.contains(con)) {
                            queue.enqueue(con);
                            checked.add(con);
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public List<Airport> getAirports() {
        return airports;
    }

    @Override
    public Set<Connection> getConnections() {
        return connections;
    }

    @Override
    public Map<Airport, Set<Connection>> getAdjacent() {
        return adjacent;
    }
}
