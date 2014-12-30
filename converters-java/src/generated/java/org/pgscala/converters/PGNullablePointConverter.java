package org.pgscala.converters;

import org.joda.convert.*;

/** Do not edit - generated in Builder / PGNullablePointConverterBuilder.scala */

public enum PGNullablePointConverter implements StringConverter<java.awt.Point> {
    INSTANCE;

    public static final String pgType = "point";

    @ToString
    public static String pointToString(final java.awt.Point p) {
        if (null == p) return null; return "("+ (int) p.getX()+","+ (int) p.getY()+")";
    }

    @FromString
    public static java.awt.Point stringToPoint(final String p) {
        if (null == p) return null;
    if (p.indexOf("nan")!=-1 ||p.indexOf("inf")!=-1) return null;
    int x = Integer.valueOf(p.substring(1, p.indexOf(",")));
    int y = Integer.valueOf(p.substring(p.indexOf(",")+1, p.length()-1));
    return new java.awt.Point(x, y);
    }

// -----------------------------------------------------------------------------

    public String convertToString(final java.awt.Point p) {
        return pointToString(p);
    }

    public java.awt.Point convertFromString(final Class<? extends java.awt.Point> clazz, final String p) {
        return stringToPoint(p);
    }
}
