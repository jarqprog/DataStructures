package com.jarq.graph;

import com.jarq.queue.CustomQueue;
import com.jarq.queue.ICustomQueue;

import java.util.*;

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
    public Connection findShortestConnection(Airport startPoint, Airport destination) {
        return null;
    }

    @Override
    public Connection findCheapestConnection(Airport startPoint, Airport destination) {
        return null;
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
}
