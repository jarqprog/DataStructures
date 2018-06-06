package com.jarq.graph.holidaysFlights;

public class HolidaysFlightsDemo {

    public static void main(String[] args) {
        HolidaysFlightGraph graph = new MyHolidaysFlightGraph();

        Airport krakow = new Airport("Krakow");
        Airport poznan = new Airport("Poznan");
        Airport lodz = new Airport("Lodz");
        Airport gdansk = new Airport("Gdansk");
        Airport bytom = new Airport("Bytom");
        Airport slupsk = new Airport("Slupsk");
        Airport bialystok = new Airport("Bialystok");

        graph.addConnection(bytom, lodz, 10000, 10);
        graph.addConnection(lodz, bialystok, 1, 101);
        graph.addConnection(slupsk, bytom, 100, 112);
        graph.addConnection(lodz, poznan, 100, 103);
        graph.addConnection(poznan, gdansk, 100, 1012);
        graph.addConnection(lodz, krakow, 2000, 1101);
        graph.addConnection(poznan, bialystok, 1, 11);
        graph.addConnection(krakow, slupsk, 50, 232);
        graph.addConnection(gdansk, lodz, 100, 46);
        graph.addConnection(slupsk, lodz, 250, 657);
        graph.addConnection(bialystok, lodz, 2, 123);
        graph.addConnection(poznan, lodz, 100, 132);
        graph.addConnection(krakow, poznan, 100, 1);


        HolidaysFlightsFinder finder = new MyHolidaysFlightsFinder(graph);

        System.out.println(finder.calculateShortestWay(krakow, lodz));
        System.out.println(finder.getShortestWay(krakow, lodz));

        System.out.println(finder.calculateCheapestTravel(krakow, lodz));
        System.out.println(finder.getCheapestTravel(krakow, lodz));
    }

}
