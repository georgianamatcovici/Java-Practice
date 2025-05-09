package org.example.frontend;

public class SphericalMercator extends Mercator {
    @Override
    public double xAxisProjection(double lon) {
        return Math.toRadians(lon) * RADIUS_MAJOR;
    }

    @Override
    public double yAxisProjection(double lat) {
        return Math.log(Math.tan(Math.PI / 4 + Math.toRadians(lat) / 2)) * RADIUS_MAJOR;
    }
}
