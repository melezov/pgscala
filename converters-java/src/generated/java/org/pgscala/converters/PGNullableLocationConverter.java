package org.pgscala.converters;

import org.joda.convert.*;

/** Do not edit - generated in Builder / PGNullableLocationConverterBuilder.scala */

public enum PGNullableLocationConverter implements StringConverter<java.awt.geom.Point2D> {
    INSTANCE;

    public static final String pgType = "point";

    private static Double getDoubleFromString(String s) {
        if (s.equals("nan"))
            return Double.NaN;
        else if (s.equals("inf"))
            return Double.POSITIVE_INFINITY;
        else if (s.equals("-inf"))
            return Double.NEGATIVE_INFINITY;
        else
            return Double.valueOf(s);
    }

    @ToString
    public static String locationToString(final java.awt.geom.Point2D l) {
        if (null == l) return null; return "("+ l.getX()+","+l.getY()+")";
    }

    @FromString
    public static java.awt.geom.Point2D stringToLocation(final String l) {
        if (null == l) return null;
    double x = getDoubleFromString(l.substring(1, l.indexOf(",")));
    double y = getDoubleFromString(l.substring(l.indexOf(",")+1, l.length()-1));
    return new java.awt.geom.Point2D.Double(x, y);
    }

// -----------------------------------------------------------------------------

    public String convertToString(final java.awt.geom.Point2D l) {
        return locationToString(l);
    }

    public java.awt.geom.Point2D convertFromString(final Class<? extends java.awt.geom.Point2D> clazz, final String l) {
        return stringToLocation(l);
    }
}
