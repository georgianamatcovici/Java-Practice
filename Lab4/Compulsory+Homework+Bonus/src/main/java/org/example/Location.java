package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Location implements Comparable<Location> {
    private int locationId;
    private String locationName;
    private LocationType locationType;

    @Override
    public int compareTo(Location o) {
        return this.locationName.compareTo(o.locationName);
    }
    @Override
    public String toString()
    {
        return locationId+" ";
    }
}
