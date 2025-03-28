package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Location implements Comparable<Location> {
    private int locationId;
    private String locationName;
    private LocationType locationType;

    Location(int locationId, String locationName) {
        this.locationId = locationId;
        this.locationName = locationName;
    }

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
