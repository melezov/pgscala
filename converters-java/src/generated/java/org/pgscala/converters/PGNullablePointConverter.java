package org.pgscala.converters;

import org.joda.convert.*;

/** Do not edit - generated in Builder / PGNullablePointConverterBuilder.scala */

public enum PGNullablePointConverter implements StringConverter<java.awt.geom.Point2D> {
    INSTANCE;

    public static final String pgType = "point";

    @ToString
    public static String pointToString(final java.awt.geom.Point2D p) {
        if (null == p) return null;
     return "("+ p.getX()+","+p.getY()+")";
    }

    @FromString
    public static java.awt.geom.Point2D stringToPoint(final String p) {
        if (null == p) return null;
    
    double x = Double.valueOf(p.substring(1, p.indexOf(",")));
    double y = Double.valueOf(p.substring(p.indexOf(",")+1, p.length()-1));
    return new java.awt.geom.Point2D.Double(x, y);
    }

// -----------------------------------------------------------------------------

    public String convertToString(final java.awt.geom.Point2D p) {
        return pointToString(p);
    }

    public java.awt.geom.Point2D convertFromString(final Class<? extends java.awt.geom.Point2D> clazz, final String p) {
        return stringToPoint(p);
    }
}
