package com.jarq.graph.holidaysFlights;

import java.util.*;

/**
 * uses Dijkstra's algorithm
 * return 0 or empty connections list (depends on method) if there's no connection between airports
 */
public class MyHolidaysFlightsFinder implements HolidaysFlightsFinder {

    private Map<Airport[],List<Connection>> cheapestTravels;
    private Map<Airport[],List<Connection>> shortestTravels;
    private final Map<Airport, Set<Connection>> adjacent;

    public MyHolidaysFlightsFinder(HolidaysFlightGraph graph) {
        this.adjacent = graph.getAdjacent();
        this.cheapestTravels = new HashMap<>();
        this.shortestTravels = new HashMap<>();
    }

    @Override
    public double calculateShortestWay(Airport startPoint, Airport destination) {
        if(adjacent.size() == 0 || !adjacent.containsKey(startPoint) || !adjacent.containsKey(destination)) {
            return 0;
        }
        return getShortestWay(startPoint, destination).stream()
                .mapToDouble(Connection::getDistance)
                .sum();
    }


    @Override
    public double calculateCheapestTravel(Airport startPoint, Airport destination) {
        if(adjacent.size() == 0 || !adjacent.containsKey(startPoint) || !adjacent.containsKey(destination)) {
            return 0;
        }
        return getCheapestTravel(startPoint, destination).stream()
                .mapToDouble(Connection::getCost)
                .sum();
    }

    @Override
    public List<Connection> getShortestWay(Airport startPoint, Airport destination) {
        if(adjacent.size() == 0 || !adjacent.containsKey(startPoint) || !adjacent.containsKey(destination)) {
            return new LinkedList<>();
        }
        Airport[] startAndDestination = new Airport[]{startPoint,destination};
        if(shortestTravels.containsKey(startAndDestination) &&
                shortestTravels.get(startAndDestination).size() > 0) {
           return shortestTravels.get(startAndDestination);
        }

        List<Connection> journey = getConnections(startPoint, destination,
                new ConnectionGetDistance(), new DistanceComparator());

        shortestTravels.put(startAndDestination,journey);
        return journey;
    }

    @Override
    public List<Connection> getCheapestTravel(Airport startPoint, Airport destination) {
        if(adjacent.size() == 0 || !adjacent.containsKey(startPoint) || !adjacent.containsKey(destination)) {
            return new LinkedList<>();
        }
        Airport[] startAndDestination = new Airport[]{startPoint,destination};
        if(cheapestTravels.containsKey(startAndDestination) &&
                cheapestTravels.get(startAndDestination).size() > 0) {
            return cheapestTravels.get(startAndDestination);
        }

        List<Connection> journey = getConnections(startPoint, destination,
                new ConnectionGetCost(), new CostComparator());

        cheapestTravels.put(startAndDestination,journey);
        return journey;
    }

    private List<Connection> getConnections(Airport startPoint, Airport destination,
                                                  ConnectionMethod method,
                                                  Comparator<Connection> comparator) {

        if(!adjacent.containsKey(startPoint) || !adjacent.containsKey(destination)) {
            return new ArrayList<>();
        }

        int length = adjacent.size();
        double infinity = Double.POSITIVE_INFINITY;

        Map<Airport,Double> values = new HashMap<>(length);  // distances or costs (depends on method arguments)
        Map<Airport,Connection> connections = new LinkedHashMap<>(length);
        PriorityQueue<Connection> queue = new PriorityQueue<>(comparator);

        queue.addAll(adjacent.get(startPoint));

        for(Airport port : adjacent.keySet()) {
            values.put(port,infinity);
        }

        values.put(startPoint,0.);  // cost difference (or distance) between same location is 0
        Connection connection;
        Airport starting;
        Airport landing;
        double value;  // distance or cost (depends on method arguments)

        while(!queue.isEmpty()) {
            connection = queue.poll();

            if(connection == null) {
                break;
            } else {
                starting = connection.getStartPoint();
                landing = connection.getDestination();
                value = values.get(starting) + method.call(connection);

                if(values.get(landing) > value) {
                    values.put(landing, value);
                    connections.put(landing,connection);
                    queue.addAll(adjacent.get(landing));
                }
            }

            if(connection.getDestination() == destination) {
                break;
            }
        }

        List<Connection> output = new LinkedList<>();
        if(values.get(destination).equals(infinity)) {  // it's no connection -> return empty list
            return output;
        }

        // build path of connections to return
        Connection con = connections.get(destination);
        while(con!=null) {
            output.add(0, con);
            con = connections.get(con.getStartPoint());
        }
        return output;
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
