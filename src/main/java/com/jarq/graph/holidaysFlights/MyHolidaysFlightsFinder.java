package com.jarq.graph.holidaysFlights;

import java.util.*;

/**
 * uses Dijkstra's algorithm
 */
public class MyHolidaysFlightsFinder implements HolidaysFlightsFinder {

    private Map<Airport[],List<Connection>> cheapestTravels;
    private Map<Airport[],List<Connection>> shortestTravels;

    private final List<Airport> airports;
    private final Map<Airport, Set<Connection>> adjacent;

    public MyHolidaysFlightsFinder(HolidaysFlightGraph graph) {
        this.airports = graph.getAirports();
        this.adjacent = graph.getAdjacent();
        this.cheapestTravels = new HashMap<>();
        this.shortestTravels = new HashMap<>();
    }

    @Override
    public double calculateShortestWay(Airport startPoint, Airport destination) {
        return getShortestWay(startPoint, destination).stream()
                .mapToDouble(Connection::getDistance)
                .sum();
    }

    @Override
    public List<Connection> getShortestWay(Airport startPoint, Airport destination) {
        Airport[] startAndDestination = new Airport[]{startPoint,destination};
        if(shortestTravels.containsKey(startAndDestination)) {
           return shortestTravels.get(startAndDestination);
        }

        List<Connection> journey = new LinkedList<>();
        Connection[] cons = getConnections(startPoint, destination,
                new ConnectionGetDistance(), new DistanceComparator());
        for(Connection con : cons) {
            if(con != null) {
                journey.add(con);
            }
        }

        shortestTravels.put(startAndDestination,journey);
        return journey;
    }

    @Override
    public double calculateCheapestTravel(Airport startPoint, Airport destination) {
        return getCheapestTravel(startPoint, destination).stream()
                .mapToDouble(Connection::getCost)
                .sum();
    }

    @Override
    public List<Connection> getCheapestTravel(Airport startPoint, Airport destination) {
        Airport[] startAndDestination = new Airport[]{startPoint,destination};
        if(cheapestTravels.containsKey(startAndDestination)) {
            return cheapestTravels.get(startAndDestination);
        }

        List<Connection> journey = new LinkedList<>();
        Connection[] cons = getConnections(startPoint, destination,
                new ConnectionGetCost(), new CostComparator());
        for(Connection con : cons) {
            if(con != null) {
                journey.add(con);
            }
        }
        cheapestTravels.put(startAndDestination,journey);
        return journey;
    }

//    private Connection[] getShortestConnections(Airport startPoint, Airport destination) {
//        int length = airports.size();
//        double infinity = Double.POSITIVE_INFINITY;
//        double[] distances = new double[length];
//
//        PriorityQueue<Connection> queue = new PriorityQueue<>(Comparator.comparingDouble(Connection::getDistance));
//        queue.addAll(adjacent.get(startPoint));
//
//        for(int i=0; i<length; i++) {
//            distances[i] = infinity;
//        }
//
//        int startPointIndex = airports.indexOf(startPoint);
//        distances[startPointIndex] = 0;  // distance from start to start equals 0
//
//        Connection connection;
//        Airport starting;
//        Airport landing;
//        double distance;
//
//        Connection[] travelPoints = new Connection[length];
//
//        while(!queue.isEmpty()) {
//            connection = queue.poll();
//
//            if(connection == null) {
//                break;
//            } else {
//                starting = connection.getStartPoint();
//                landing = connection.getDestination();
//
//                distance = distances[airports.indexOf(starting)] + connection.getDistance();
//                queue.addAll(adjacent.get(landing));
//
//                int startingIndex = airports.indexOf(starting);
//                int landingIndex = airports.indexOf(landing);
//
//                if( distances[landingIndex] > distance) {
//                    distances[landingIndex] = distance;
//                    travelPoints[startingIndex] = connection;
//                }
//            }
//
//            if(connection.getDestination() == destination) {
//                break;
//            }
//        }
//        return travelPoints;
//    }

    private Connection[] getConnections(Airport startPoint, Airport destination,
                                        ConnectionMethod method,
                                        Comparator<Connection> comparator) {
        int length = airports.size();
        double infinity = Double.POSITIVE_INFINITY;
        double[] values = new double[length];  // distances or costs (depends on method arguments)

        PriorityQueue<Connection> queue = new PriorityQueue<>(comparator);
        queue.addAll(adjacent.get(startPoint));

        for(int i=0; i<length; i++) {
            values[i] = infinity;
        }

        int startPointIndex = airports.indexOf(startPoint);
        values[startPointIndex] = 0;  // cost difference/distance between same location is 0
        Connection connection;
        Airport starting;
        Airport landing;
        double value;  // distance or cost (depends on method arguments)

        Connection[] connections = new Connection[length];

        while(!queue.isEmpty()) {
            connection = queue.poll();

            if(connection == null) {
                break;
            } else {
                starting = connection.getStartPoint();
                landing = connection.getDestination();

                value = values[airports.indexOf(starting)] + method.call(connection);
                queue.addAll(adjacent.get(landing));

                int startingIndex = airports.indexOf(starting);
                int landingIndex = airports.indexOf(landing);

                if( values[landingIndex] > value) {
                    values[landingIndex] = value;
                    connections[startingIndex] = connection;
                }
            }

            if(connection.getDestination() == destination) {
                break;
            }
        }
        return connections;
    }

    // helper classes:
    private class CostComparator implements Comparator<Connection> {

        @Override
        public int compare(Connection o1, Connection o2) {
            return Double.compare(o1.getCost(), o2.getCost());
        }
    }

    private class DistanceComparator implements Comparator<Connection> {

        @Override
        public int compare(Connection o1, Connection o2) {
            return Double.compare(o1.getDistance(), o2.getDistance());
        }
    }

    private interface ConnectionMethod {
        double call(Connection con);
    }

    private class ConnectionGetCost implements ConnectionMethod {
        @Override
        public double call(Connection con) {
            return con.getCost();
        }
    }

    private class ConnectionGetDistance implements ConnectionMethod {
        @Override
        public double call(Connection con) {
            return con.getDistance();
        }
    }
}
