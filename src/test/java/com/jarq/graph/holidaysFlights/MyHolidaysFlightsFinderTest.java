package com.jarq.graph.holidaysFlights;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MyHolidaysFlightsFinderTest {

    private Airport warsaw = new Airport("Warsaw");
    private Airport mexicoCity = new Airport("Mexico City");


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

        HolidaysFlightGraph graph = new MyHolidaysFlightGraph();
        Airport oslo = new Airport("Oslo");
        Airport berlin = new Airport("Berlin");
        Airport gdansk = new Airport("Gdansk");
        graph.addConnection(gdansk, oslo,30, 30);
        graph.addConnection(oslo, gdansk, 30, 31);
        graph.addConnection(berlin, oslo, 10, 10);
        graph.addConnection(berlin, gdansk,50, 50);

        HolidaysFlightsFinder finder = new MyHolidaysFlightsFinder(graph);

        List<Connection> travel = finder.getCheapestTravel(gdansk, berlin);

        assertEquals(0, travel.size());
    }

    @Test
    public void calculateCheapestTravel_tiny_graph_if_no_connection() {

        HolidaysFlightGraph graph = new MyHolidaysFlightGraph();
        Airport oslo = new Airport("Oslo");
        Airport berlin = new Airport("Berlin");
        Airport gdansk = new Airport("Gdansk");
        graph.addConnection(gdansk, oslo,30, 30);
        graph.addConnection(oslo, gdansk, 30, 31);
        graph.addConnection(berlin, oslo, 10, 10);
        graph.addConnection(berlin, gdansk,50, 50);

        HolidaysFlightsFinder finder = new MyHolidaysFlightsFinder(graph);

        int cost = (int) finder.calculateCheapestTravel(gdansk, berlin);

        assertEquals(0, cost);
    }

    @Test
    public void calculateCheapestTravel_tiny_graph() {

        HolidaysFlightGraph graph = new MyHolidaysFlightGraph();
        Airport oslo = new Airport("Oslo");
        Airport berlin = new Airport("Berlin");
        Airport gdansk = new Airport("Gdansk");
        graph.addConnection(gdansk, oslo,30, 30);
        graph.addConnection(oslo, gdansk, 30, 31);
        graph.addConnection(oslo, berlin, 10, 11);
        graph.addConnection(berlin, oslo, 10, 10);
        graph.addConnection(gdansk, berlin, 50, 51);
        graph.addConnection(berlin, gdansk,50, 50);

        HolidaysFlightsFinder finder = new MyHolidaysFlightsFinder(graph);

        int cost = (int) finder.calculateCheapestTravel(gdansk, berlin);

        assertEquals(41, cost);
    }

    @Test
    public void getCheapestTravel_tiny_graph() {

        HolidaysFlightGraph graph = new MyHolidaysFlightGraph();
        Airport oslo = new Airport("Oslo");
        Airport berlin = new Airport("Berlin");
        Airport gdansk = new Airport("Gdansk");
        graph.addConnection(gdansk, oslo,30, 30);
        graph.addConnection(oslo, gdansk, 30, 31);
        graph.addConnection(oslo, berlin, 10, 11);
        graph.addConnection(berlin, oslo, 10, 10);
        graph.addConnection(gdansk, berlin, 50, 51);
        graph.addConnection(berlin, gdansk,50, 50);

        HolidaysFlightsFinder finder = new MyHolidaysFlightsFinder(graph);

        List<Connection> travel = finder.getCheapestTravel(gdansk, berlin);

        assertEquals(2, travel.size());
        assertEquals(oslo, travel.get(0).getDestination());
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

        HolidaysFlightsFinder bigFinder = new MyHolidaysFlightsFinder(generateBigGraph());

        int cost = (int) bigFinder.calculateCheapestTravel(warsaw, mexicoCity);

        assertEquals(805, cost);
    }

    @Test
    public void getCheapestTravel_big_graph() {

        HolidaysFlightsFinder bigFinder = new MyHolidaysFlightsFinder(generateBigGraph());

        List<Connection> travel = bigFinder.getCheapestTravel(warsaw, mexicoCity);

        System.out.println(travel);
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
    public void calculateShortestTravel_small_graph() {
    }

    @Test
    public void getShortestTravel_small_graph() {

        Airport paris = new Airport("Paris");

        HolidaysFlightGraph graph = new MyHolidaysFlightGraph();

    }

    private HolidaysFlightGraph generateSmallGraph(Airport start, Airport destintation) {

        HolidaysFlightGraph graph = new MyHolidaysFlightGraph();
        Airport oslo = new Airport("Oslo");
        Airport berlin = new Airport("Berlin");
        Airport gdansk = new Airport("Gdansk");

        graph.addConnection(berlin, oslo, 600, 50);
        graph.addConnection(oslo, berlin, 600, 50);

        graph.addConnection(destintation, oslo, 650, 35);
        graph.addConnection(oslo, destintation, 650, 35);

        graph.addConnection(berlin, destintation, 1200, 75);
        graph.addConnection(destintation, berlin, 1200, 75);

        graph.addConnection(gdansk, oslo, 300, 20);
        graph.addConnection(start, gdansk, 280, 20);
        graph.addConnection(start, berlin, 650, 80);
        graph.addConnection(destintation, start, 950, 99);

        return graph;
    }

    private HolidaysFlightGraph generateBigGraph() {

        HolidaysFlightGraph graph = new MyHolidaysFlightGraph();
        Airport newYork = new Airport("New York");
        Airport brazil = new Airport("Brazil");
        Airport london = new Airport("London");
        Airport paris = new Airport("Paris");
        Airport rome = new Airport("Rome");
        Airport madrid = new Airport("Madrid");
        Airport oslo = new Airport("Oslo");
        Airport berlin = new Airport("Berlin");
        Airport gdansk = new Airport("Gdansk");

        graph.addConnection(newYork, paris, 5000, 800);
        graph.addConnection(paris, newYork, 5000, 800);
        graph.addConnection(newYork, mexicoCity, 3800, 800);
        graph.addConnection(mexicoCity, newYork, 3800, 800);
        graph.addConnection(madrid, brazil, 4100, 1200);
        graph.addConnection(brazil, madrid, 4100, 1300);
        graph.addConnection(mexicoCity, brazil, 2100, 700);
        graph.addConnection(brazil, mexicoCity, 2100, 700);
        graph.addConnection(berlin, oslo, 800, 1000);
        graph.addConnection(oslo, berlin, 800, 1000);
        graph.addConnection(london, oslo, 800, 1000);
        graph.addConnection(london, newYork, 5000, 2);
        graph.addConnection(oslo, london, 800, 1000);
        graph.addConnection(berlin, rome, 1200, 800);
        graph.addConnection(rome, berlin, 1200, 800);
        graph.addConnection(madrid, rome, 1200, 800);
        graph.addConnection(rome, madrid, 1200, 800);
        graph.addConnection(madrid, rome, 1200, 800);
        graph.addConnection(warsaw, gdansk, 280, 1);
        graph.addConnection(gdansk, warsaw, 280, 80);
        graph.addConnection(london, gdansk, 1300, 120);
        graph.addConnection(gdansk, london, 1300, 2);
        graph.addConnection(warsaw, berlin, 650, 110);
        graph.addConnection(berlin, warsaw, 650, 110);

        return graph;
    }
}