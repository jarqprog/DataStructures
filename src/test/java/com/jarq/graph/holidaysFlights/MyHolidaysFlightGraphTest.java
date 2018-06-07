package com.jarq.graph.holidaysFlights;

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

    @Test
    public void hasConnection() {

        Airport krakow = new Airport("Krakow");
        Airport poznan = new Airport("Poznan");
        Airport lodz = new Airport("Lodz");
        Airport gdansk = new Airport("Gdansk");
        Airport bytom = new Airport("Bytom");
        Airport slupsk = new Airport("Slupsk");

        graph.addConnection(krakow, poznan, 120, 300);
        graph.addConnection(slupsk, bytom, 190, 690);
        graph.addConnection(lodz, poznan, 80, 100);
        graph.addConnection(poznan, gdansk, 100, 300);
        graph.addConnection(krakow, lodz, 100, 300);
        graph.addConnection(lodz, gdansk, 100, 330);

        assertTrue(graph.hasConnection(krakow, gdansk));

        assertFalse(graph.hasConnection(bytom, poznan));
    }

    @Test
    public void hasConnection_medium_graph() {

        Airport krakow = new Airport("Krakow");
        Airport poznan = new Airport("Poznan");
        Airport lodz = new Airport("Lodz");
        Airport gdansk = new Airport("Gdansk");
        Airport bytom = new Airport("Bytom");
        Airport slupsk = new Airport("Slupsk");
        Airport plock = new Airport("Plock");
        Airport pyrzowice = new Airport("Pyrzowice");
        Airport leba = new Airport("Leba");
        Airport sacz = new Airport("Sacz");
        Airport radom = new Airport("Radom");
        Airport warszawa = new Airport("Warszawa");

        graph.addConnection(krakow, poznan, 120, 300);
        graph.addConnection(slupsk, bytom, 190, 690);
        graph.addConnection(lodz, poznan, 80, 100);
        graph.addConnection(poznan, gdansk, 100, 300);
        graph.addConnection(krakow, lodz, 100, 300);
        graph.addConnection(lodz, gdansk, 100, 330);
        graph.addConnection(gdansk, plock, 100, 330);
        graph.addConnection(plock, radom, 100, 330);
        graph.addConnection(radom, warszawa, 100, 330);
        graph.addConnection(warszawa, sacz, 100, 330);
        graph.addConnection(sacz, leba, 100, 330);
        graph.addConnection(gdansk, plock, 100, 330);
        graph.addConnection(pyrzowice, gdansk, 100, 330);

        assertTrue(graph.hasConnection(krakow, plock));
        assertFalse(graph.hasConnection(krakow, pyrzowice));
    }

    @Test
    public void hasConnection_big_graph() {

        Airport startCity = new Airport("startCity");
        Airport lastCity = new Airport("lastCity");
        int graphSize = 100;
        populateGraph(startCity, lastCity, graphSize);

        Airport poznan = new Airport("Poznan");

        graph.addConnection(lastCity, poznan, 100, 100);

        assertTrue(graph.hasConnection(startCity, poznan));
    }

    private void populateGraph(Airport firstAirport, Airport lastAirport, int size) {
        int minimumSize = 3;
        if(size < minimumSize) {
            throw new IllegalArgumentException("size should be greater than 3!");
        }

        Airport[] airports = generateAirports(size);

        for(int i=0; i<size-1; i++) {

            graph.addConnection(airports[i], airports[i+1], i, i);
            if(i==2) {
                graph.addConnection(firstAirport, airports[i], 1, 1);
            }

            if(i > minimumSize && i < size-minimumSize) {
                graph.addConnection(airports[i], airports[i-1], i, i);
                graph.addConnection(airports[i], airports[i-2], i, i);
                graph.addConnection(airports[i], airports[i-3], i, i);
            }

            else if(i==size-2) {
                graph.addConnection(airports[i-2], lastAirport, i, i);
            }
        }
    }

    private Airport[] generateAirports(int quantity) {
        Airport[] airports = new Airport[quantity];
        for(int i=1; i<=quantity; i++) {
            airports[i-1] = new Airport("CityNo"+String.valueOf(i));
        }
        return airports;
    }
}