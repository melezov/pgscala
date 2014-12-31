package org.pgscala.converters;

import org.joda.convert.*;

/** Do not edit - generated in Builder / PGNullableLocationFloatConverterBuilder.scala */

public enum PGNullableLocationFloatConverter implements StringConverter<java.awt.geom.Point2D.Float> {
    INSTANCE;

    public static final String pgType = "point";

    private static Float getFloatFromString(String s) {
        if (s.equals("nan"))
            return Float.NaN;
        else if (s.equals("inf"))
            return Float.POSITIVE_INFINITY;
        else if (s.equals("-inf"))
            return Float.NEGATIVE_INFINITY;
        else
            return Float.valueOf(s);
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
    public static String locationFloatToString(final java.awt.geom.Point2D.Float l) {
        if (null == l) return null; return "("+ getStringFromDouble(l.getX())+","+getStringFromDouble(l.getY())+")";
    }

    @FromString
    public static java.awt.geom.Point2D.Float stringToLocationFloat(final String l) {
        if (null == l) return null;
    float x = getFloatFromString(l.substring(1, l.indexOf(",")));
    float y = getFloatFromString(l.substring(l.indexOf(",")+1, l.length()-1));
    return new java.awt.geom.Point2D.Float(x, y);
    }

// -----------------------------------------------------------------------------

    public String convertToString(final java.awt.geom.Point2D.Float l) {
        return locationFloatToString(l);
    }

    public java.awt.geom.Point2D.Float convertFromString(final Class<? extends java.awt.geom.Point2D.Float> clazz, final String l) {
        return stringToLocationFloat(l);
    }
}
