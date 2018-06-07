package com.jarq.graph.holidaysFlights;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class HolidaysFlightsDemo {

    public static void main(String[] args) {

        // create and fill graph
        HolidaysFlightGraph graph = new MyHolidaysFlightGraph();

        Airport krakow = new Airport("Krakow");
        Airport poznan = new Airport("Poznan");
        Airport lodz = new Airport("Lodz");
        Airport gdansk = new Airport("Gdansk");
        Airport bytom = new Airport("Bytom");
        Airport slupsk = new Airport("Slupsk");
        Airport bialystok = new Airport("Bialystok");

        graph.addConnection(bytom, lodz, 250, 80);
        graph.addConnection(lodz, bialystok, 310, 101);
        graph.addConnection(slupsk, bytom, 650, 99);
        graph.addConnection(lodz, poznan, 165, 80);
        graph.addConnection(poznan, gdansk, 290, 75);
        graph.addConnection(lodz, krakow, 300, 20);
        graph.addConnection(poznan, bialystok, 289, 120);
        graph.addConnection(krakow, slupsk, 700, 20);  // discount!
        graph.addConnection(gdansk, lodz, 301, 19.99);  // discount!
        graph.addConnection(slupsk, lodz, 299, 99.99);
        graph.addConnection(bialystok, lodz, 310, 101);
        graph.addConnection(poznan, lodz, 165, 409.99);
        graph.addConnection(krakow, poznan, 410, 19.99);  // discount!
        graph.addConnection(krakow, lodz, 710, 165);  // fantasy ;)

        // start demo
        HolidaysFlightsFinder finder = new MyHolidaysFlightsFinder(graph);

        String result = buildResult(graph, finder, krakow, lodz);
        displayResult(result);
        saveResult(result);
    }

    private static void displayResult(String result) {
        System.out.println(result);
    }

    private static String buildResult(HolidaysFlightGraph flights, HolidaysFlightsFinder finder,
                                      Airport startPoint, Airport destination) {

        double distance;
        double cost;
        List<Connection> shortestJourney, cheapestJourney;
        Set<Airport> airports = flights.getAirports();
        Set<Connection> connections = flights.getConnections();

        StringBuilder stringBuilder = new StringBuilder(String.format("\n***%s***\n", LocalDateTime.now()));

        stringBuilder.append(String.format("Operation for travel between %s (start) and %s (landing)\n\n",
                startPoint, destination));

        stringBuilder.append("- all airports:\n");
        stringBuilder.append(addCollectionToString(airports));
        stringBuilder.append("- possible connections:\n");
        stringBuilder.append(addCollectionToString(connections));

        distance = finder.calculateShortestWay(startPoint, destination);
        shortestJourney = finder.getShortestWay(startPoint, destination);
        stringBuilder.append(String.format("shortest possible distance: %.2f ", distance));
        stringBuilder.append("with connections: \n");
        stringBuilder.append(addCollectionToString(shortestJourney));

        cost = finder.calculateCheapestTravel(startPoint, destination);
        cheapestJourney = finder.getCheapestTravel(startPoint, destination);
        stringBuilder.append(String.format("lowest possible cost: %.2f ",cost));
        stringBuilder.append("with connections: \n");
        stringBuilder.append(addCollectionToString(cheapestJourney));
        stringBuilder.append("\n***Done***\n");

        return stringBuilder.toString();
    }

    private static void saveResult(String result) {

        String filePath = "src/main/resources/holidaysFlights.md";

        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
            bufferedWriter.write(result);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        System.out.println("Saved to file: " + filePath);

    }

    private static <T> String addCollectionToString(Collection<T> collection) {
        StringBuilder sb = new StringBuilder();
        for(T element : collection) {
            sb.append("\t");
            sb.append(element.toString());
            sb.append("\n");
        }
        sb.append("\n");
        return sb.toString();
    }
}
