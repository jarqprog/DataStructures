package com.jarq.graph.holidaysFlights;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MyHolidaysFlightsFinderTest {

    private Airport warsaw = new Airport("Warsaw");
    private Airport mexicoCity = new Airport("Mexico City");
    private Airport berlin = new Airport("Berlin");
    private Airport gdansk = new Airport("Gdansk");


    // tests for cheapest:
    @Test
    public void calculateCheapestTravel_with_empty_graph() {

        HolidaysFlightsFinder myFinder = new MyHolidaysFlightsFinder(new MyHolidaysFlightGraph());

        int result = (int) myFinder.calculateCheapestTravel(warsaw, mexicoCity);

        assertEquals(0, result);
    }

    @Test
    public void getCheapestTravel_with_empty_graph() {

        HolidaysFlightsFinder myFinder = new MyHolidaysFlightsFinder(new MyHolidaysFlightGraph());
        List<Connection> connections = myFinder.getCheapestTravel(warsaw, mexicoCity);

        assertEquals(0, connections.size());
    }

    @Test
    public void getCheapestTravel_tiny_graph_if_no_connection() {

        HolidaysFlightGraph graph = generateTinyGraph(gdansk, berlin, false);
        HolidaysFlightsFinder finder = new MyHolidaysFlightsFinder(graph);

        List<Connection> travel = finder.getCheapestTravel(gdansk, berlin);

        assertEquals(0, travel.size());
    }

    @Test
    public void calculateCheapestTravel_tiny_graph_if_no_connection() {
        HolidaysFlightGraph graph = generateTinyGraph(gdansk, berlin, false);
        HolidaysFlightsFinder finder = new MyHolidaysFlightsFinder(graph);

        int cost = (int) finder.calculateCheapestTravel(gdansk, berlin);

        assertEquals(0, cost);
    }

    @Test
    public void getCheapestTravel_tiny_graph() {
        HolidaysFlightGraph graph = generateTinyGraph(gdansk, berlin, true);
        HolidaysFlightsFinder finder = new MyHolidaysFlightsFinder(graph);

        List<Connection> travel = finder.getCheapestTravel(gdansk, berlin);

        assertEquals(2, travel.size());
        assertEquals("Oslo", travel.get(0).getDestination().getName());
    }

    @Test
    public void calculateCheapestTravel_tiny_graph() {
        HolidaysFlightGraph graph = generateTinyGraph(gdansk, berlin, true);
        HolidaysFlightsFinder finder = new MyHolidaysFlightsFinder(graph);

        int cost = (int) finder.calculateCheapestTravel(gdansk, berlin);

        assertEquals(41, cost);
    }

    @Test
    public void calculateCheapestTravel_small_graph() {

        Airport paris = new Airport("Paris");
        HolidaysFlightsFinder smallFinder = new MyHolidaysFlightsFinder(generateSmallGraph(warsaw, paris));

        int cost = (int) smallFinder.calculateCheapestTravel(warsaw, paris);

        assertEquals(75, cost);
    }

    @Test
    public void getCheapestTravel_small_graph() {

        Airport paris = new Airport("Paris");
        HolidaysFlightsFinder smallFinder = new MyHolidaysFlightsFinder(generateSmallGraph(warsaw, paris));

        List<Connection> travel = smallFinder.getCheapestTravel(warsaw, paris);

        assertEquals(3, travel.size());
        assertEquals("Gdansk", travel.get(0).getDestination().getName());
        assertEquals("Gdansk", travel.get(1).getStartPoint().getName());
        assertEquals("Oslo", travel.get(2).getStartPoint().getName());
    }

    @Test
    public void calculateCheapestTravel_big_graph() {

        HolidaysFlightsFinder bigFinder = new MyHolidaysFlightsFinder(generateBigGraph(warsaw, mexicoCity));

        int cost = (int) bigFinder.calculateCheapestTravel(warsaw, mexicoCity);

        assertEquals(805, cost);
    }

    @Test
    public void getCheapestTravel_big_graph() {

        HolidaysFlightsFinder bigFinder = new MyHolidaysFlightsFinder(generateBigGraph(warsaw, mexicoCity));

        List<Connection> travel = bigFinder.getCheapestTravel(warsaw, mexicoCity);

        assertEquals(4, travel.size());
        assertEquals(gdansk, travel.get(1).getStartPoint());
        assertEquals("London", travel.get(2).getStartPoint().getName());
        assertEquals("New York", travel.get(3).getStartPoint().getName());
    }


    // tests for shortest:
    @Test
    public void calculateShortestTravel_with_empty_graph() {

        HolidaysFlightsFinder myFinder = new MyHolidaysFlightsFinder(new MyHolidaysFlightGraph());

        int result = (int) myFinder.calculateShortestWay(warsaw, mexicoCity);

        assertEquals(0, result);
    }

    @Test
    public void getShortestTravel_with_empty_graph() {

        HolidaysFlightsFinder myFinder = new MyHolidaysFlightsFinder(new MyHolidaysFlightGraph());

        List<Connection> connections = myFinder.getShortestWay(warsaw, mexicoCity);

        assertEquals(0, connections.size());
    }

    @Test
    public void getShortestTravel_tiny_graph_if_no_connection() {

        HolidaysFlightGraph graph = generateTinyGraph(gdansk, berlin, false);
        HolidaysFlightsFinder finder = new MyHolidaysFlightsFinder(graph);

        List<Connection> travel = finder.getShortestWay(gdansk, berlin);

        assertEquals(0, travel.size());
    }

    @Test
    public void calculateShortestTravel_tiny_graph_if_no_connection() {
        HolidaysFlightGraph graph = generateTinyGraph(gdansk, berlin, false);
        HolidaysFlightsFinder finder = new MyHolidaysFlightsFinder(graph);

        int distance = (int) finder.calculateShortestWay(gdansk, berlin);

        assertEquals(0, distance);
    }

    @Test
    public void calculateShortestTravel_tiny_graph() {
        HolidaysFlightGraph graph = generateTinyGraph(gdansk, berlin, true);
        HolidaysFlightsFinder finder = new MyHolidaysFlightsFinder(graph);

        int distance = (int) finder.calculateShortestWay(gdansk, berlin);

        assertEquals(400, distance);
    }

    @Test
    public void getShortestTravel_tiny_graph() {
        HolidaysFlightGraph graph = generateTinyGraph(gdansk, berlin, true);
        HolidaysFlightsFinder finder = new MyHolidaysFlightsFinder(graph);

        List<Connection> travel = finder.getShortestWay(gdansk, berlin);

        assertEquals(2, travel.size());
        assertEquals("Oslo", travel.get(0).getDestination().getName());
    }

    @Test
    public void calculateShortestTravel_small_graph() {

        Airport paris = new Airport("Paris");
        HolidaysFlightsFinder smallFinder = new MyHolidaysFlightsFinder(generateSmallGraph(warsaw, paris));

        int distance = (int) smallFinder.calculateShortestWay(warsaw, paris);

        assertEquals(1230, distance);
    }

    @Test
    public void getShortestTravel_small_graph() {

        Airport paris = new Airport("Paris");
        HolidaysFlightsFinder smallFinder = new MyHolidaysFlightsFinder(generateSmallGraph(warsaw, paris));

        List<Connection> travel = smallFinder.getShortestWay(warsaw, paris);

        assertEquals(3, travel.size());
        assertEquals("Gdansk", travel.get(0).getDestination().getName());
        assertEquals("Gdansk", travel.get(1).getStartPoint().getName());
        assertEquals("Oslo", travel.get(2).getStartPoint().getName());
    }

    @Test
    public void calculateShortestTravel_big_graph() {

        HolidaysFlightsFinder bigFinder = new MyHolidaysFlightsFinder(generateBigGraph(warsaw, mexicoCity));

        int distance = (int) bigFinder.calculateShortestWay(warsaw, mexicoCity);

        assertEquals(50, distance);
    }

    @Test
    public void getShortestTravel_big_graph() {

        HolidaysFlightsFinder bigFinder = new MyHolidaysFlightsFinder(generateBigGraph(warsaw, mexicoCity));

        List<Connection> travel = bigFinder.getShortestWay(warsaw, mexicoCity);

        assertEquals(5, travel.size());
        assertEquals(berlin, travel.get(1).getStartPoint());
        assertEquals("Rome", travel.get(2).getStartPoint().getName());
        assertEquals("Madrid", travel.get(3).getStartPoint().getName());
        assertEquals("Brazil", travel.get(4).getStartPoint().getName());
    }


    private HolidaysFlightGraph generateTinyGraph(Airport start, Airport destination, boolean connect) {

        HolidaysFlightGraph tinyGraph = new MyHolidaysFlightGraph();
        Airport oslo = new Airport("Oslo");

        tinyGraph.addConnection(start, oslo,300, 30);
        tinyGraph.addConnection(oslo, start, 300, 31);
        tinyGraph.addConnection(destination, oslo, 100, 10);
        tinyGraph.addConnection(destination, start,500, 50);

        if(connect) {
            tinyGraph.addConnection(start, destination, 500, 51);
            tinyGraph.addConnection(oslo, destination, 100, 11);
        }
        return tinyGraph;
    }

    private HolidaysFlightGraph generateSmallGraph(Airport start, Airport destination) {

        HolidaysFlightGraph graph = new MyHolidaysFlightGraph();
        Airport oslo = new Airport("Oslo");

        graph.addConnection(berlin, oslo, 600, 50);
        graph.addConnection(oslo, berlin, 600, 50);

        graph.addConnection(destination, oslo, 650, 35);
        graph.addConnection(oslo, destination, 650, 35);

        graph.addConnection(berlin, destination, 1200, 75);
        graph.addConnection(destination, berlin, 1200, 75);

        graph.addConnection(gdansk, oslo, 300, 20);
        graph.addConnection(start, gdansk, 280, 20);
        graph.addConnection(start, berlin, 650, 80);
        graph.addConnection(destination, start, 950, 99);

        return graph;
    }

    private HolidaysFlightGraph generateBigGraph(Airport start, Airport destination) {

        HolidaysFlightGraph graph = new MyHolidaysFlightGraph();
        Airport newYork = new Airport("New York");
        Airport brazil = new Airport("Brazil");
        Airport london = new Airport("London");
        Airport paris = new Airport("Paris");
        Airport rome = new Airport("Rome");
        Airport madrid = new Airport("Madrid");
        Airport oslo = new Airport("Oslo");

        graph.addConnection(newYork, paris, 5000, 800);
        graph.addConnection(paris, newYork, 5000, 800);
        graph.addConnection(newYork, destination, 3800, 800);
        graph.addConnection(destination, newYork, 3800, 800);
        graph.addConnection(madrid, brazil, 10, 1200);
        graph.addConnection(brazil, madrid, 4100, 1300);
        graph.addConnection(destination, brazil, 2100, 700);
        graph.addConnection(brazil, destination, 10, 700);
        graph.addConnection(berlin, oslo, 800, 1000);
        graph.addConnection(oslo, berlin, 800, 1000);
        graph.addConnection(london, oslo, 800, 1000);
        graph.addConnection(london, newYork, 5000, 2);
        graph.addConnection(oslo, london, 800, 1000);
        graph.addConnection(berlin, rome, 10, 800);
        graph.addConnection(rome, berlin, 1200, 800);
        graph.addConnection(madrid, rome, 1200, 800);
        graph.addConnection(rome, madrid, 10, 800);
        graph.addConnection(madrid, rome, 1200, 800);
        graph.addConnection(start, gdansk, 280, 1);
        graph.addConnection(gdansk, start, 280, 80);
        graph.addConnection(london, gdansk, 1300, 120);
        graph.addConnection(gdansk, london, 1300, 2);
        graph.addConnection(start, berlin, 10, 110);
        graph.addConnection(berlin, start, 650, 110);

        return graph;
    }
}