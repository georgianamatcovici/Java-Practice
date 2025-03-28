package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SafestRoute {
    private List<Location> locationList;
    private Map<LocationType, Integer> locationTypes=new HashMap<LocationType, Integer>();
    SafestRoute(List<Location> locationList)
    {
        this.locationList = locationList;

    }
}
