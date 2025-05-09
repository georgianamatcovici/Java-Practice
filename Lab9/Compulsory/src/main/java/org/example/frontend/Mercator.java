package org.example.frontend;

abstract class Mercator {
    final static double RADIUS_MAJOR = 6378137.0;
    abstract double xAxisProjection(double lon);
    abstract double yAxisProjection(double lat);
}

