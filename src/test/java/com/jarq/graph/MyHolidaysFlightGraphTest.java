package com.jarq.graph;

import org.junit.Test;

import static org.junit.Assert.*;

public class MyHolidaysFlightGraphTest {

    private HolidaysFlightGraph graph = new MyHolidaysFlightGraph();

    @Test
    public void addConnection() {

        Airport start = new Airport("Krakow");
        Airport dest = new Airport("Poznan");

        assertTrue(graph.addConnection(start, dest, 120, 300));
    }


    @Test
    public void addConnection_same_connection_two_times() {

        Airport start = new Airport("Krakow");
        Airport dest = new Airport("Poznan");

        assertTrue(graph.addConnection(start, dest, 120, 300));
        assertFalse(graph.addConnection(start, dest, 120, 300));
    }

    @Test
    public void addConnection_same_cities_different_order() {

        Airport start = new Airport("Krakow");
        Airport dest = new Airport("Poznan");

        assertTrue(graph.addConnection(start, dest, 120, 300));
        assertTrue(graph.addConnection(dest, start, 120, 300));

        assertFalse(graph.addConnection(start, dest, 120, 300));
        assertFalse(graph.addConnection(dest, start, 120, 300));
    }

    @Test
    public void countAirports_if_empty() {

        assertEquals(0, graph.countAirports());
    }

    @Test
    public void countAirports_with_two() {

        Airport start = new Airport("Krakow");
        Airport dest = new Airport("Poznan");

        graph.addConnection(start, dest, 120, 300);

        assertEquals(2, graph.countAirports());
    }

    @Test
    public void countAirports_with_four() {

        Airport krakow = new Airport("Krakow");
        Airport poznan = new Airport("Poznan");
        Airport lodz = new Airport("Lodz");
        Airport gdansk = new Airport("Gdansk");

        graph.addConnection(krakow, poznan, 120, 300);
        graph.addConnection(lodz, poznan, 80, 100);
        graph.addConnection(poznan, gdansk, 100, 300);
        graph.addConnection(gdansk, krakow, 180, 600);
        graph.addConnection(krakow, lodz, 100, 300);
        graph.addConnection(lodz, gdansk, 100, 330);

        assertEquals(4, graph.countAirports());
    }

    @Test
    public void countConnections_if_empty() {
        assertEquals(0, graph.countConnections());
    }

    @Test
    public void countConnections_with_two() {

        Airport start = new Airport("Krakow");
        Airport dest = new Airport("Poznan");

        graph.addConnection(start, dest, 120, 300);
        graph.addConnection(dest, start, 120, 300);

        assertEquals(2, graph.countConnections());
    }

    @Test
    public void countConnections_with_six() {

        Airport krakow = new Airport("Krakow");
        Airport poznan = new Airport("Poznan");
        Airport lodz = new Airport("Lodz");
        Airport gdansk = new Airport("Gdansk");

        graph.addConnection(krakow, poznan, 120, 300);
        graph.addConnection(lodz, poznan, 80, 100);
        graph.addConnection(poznan, gdansk, 100, 300);
        graph.addConnection(gdansk, krakow, 180, 600);
        graph.addConnection(krakow, lodz, 100, 300);
        graph.addConnection(lodz, gdansk, 100, 330);

        assertEquals(6, graph.countConnections());
    }

    @Test
    public void countConnections_with_six_if_repeats() {

        Airport krakow = new Airport("Krakow");
        Airport poznan = new Airport("Poznan");
        Airport lodz = new Airport("Lodz");
        Airport gdansk = new Airport("Gdansk");

        graph.addConnection(krakow, poznan, 120, 300);
        graph.addConnection(lodz, poznan, 80, 100);
        graph.addConnection(poznan, gdansk, 100, 300);
        graph.addConnection(gdansk, krakow, 180, 600);
        graph.addConnection(krakow, lodz, 100, 300);
        graph.addConnection(lodz, gdansk, 100, 330);

        // repeats
        graph.addConnection(krakow, poznan, 120, 300);
        graph.addConnection(lodz, poznan, 80, 100);
        graph.addConnection(poznan, gdansk, 100, 300);
        graph.addConnection(gdansk, krakow, 180, 600);
        graph.addConnection(krakow, lodz, 100, 300);
        graph.addConnection(lodz, gdansk, 100, 330);

        assertEquals(6, graph.countConnections());
    }

    @Test
    public void findShortestConnection() {
    }

    @Test
    public void findCheapestConnection() {
    }
}