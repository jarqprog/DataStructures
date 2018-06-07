package com.jarq.graph.holidaysFlights;

import java.util.List;

public interface HolidaysFlightsFinder {

    double calculateCheapestTravel(Airport startPoint, Airport destination);
    List<Connection> getCheapestTravel(Airport startPoint, Airport destination);
    double calculateShortestWay(Airport startPoint, Airport destination);
    List<Connection> getShortestWay(Airport startPoint, Airport destination);
}
