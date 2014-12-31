package org.pgscala.converters;

import org.joda.convert.*;

/** Do not edit - generated in Builder / PGNullableLocationDoubleConverterBuilder.scala */

public enum PGNullableLocationDoubleConverter implements StringConverter<java.awt.geom.Point2D.Double> {
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

    private static String getStringFromDouble(Double s) {
        if (s.isNaN())
            return "nan";
        else if (s == Double.POSITIVE_INFINITY)
            return  "inf";
        else if (s == Double.NEGATIVE_INFINITY)
            return  "-inf";
        else
            return String.valueOf(s);
    }

    @ToString
    public static String locationDoubleToString(final java.awt.geom.Point2D.Double l) {
        if (null == l) return null; return "("+ getStringFromDouble(l.getX())+","+getStringFromDouble(l.getY())+")";
    }

    @FromString
    public static java.awt.geom.Point2D.Double stringToLocationDouble(final String l) {
        if (null == l) return null;
    double x = getDoubleFromString(l.substring(1, l.indexOf(",")));
    double y = getDoubleFromString(l.substring(l.indexOf(",")+1, l.length()-1));
    return new java.awt.geom.Point2D.Double(x, y);
    }

// -----------------------------------------------------------------------------

    public String convertToString(final java.awt.geom.Point2D.Double l) {
        return locationDoubleToString(l);
    }

    public java.awt.geom.Point2D.Double convertFromString(final Class<? extends java.awt.geom.Point2D.Double> clazz, final String l) {
        return stringToLocationDouble(l);
    }
}
