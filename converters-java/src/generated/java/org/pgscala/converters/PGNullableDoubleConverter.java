package org.pgscala.converters;

import org.joda.convert.*;

/** Do not edit - generated in Builder / PGNullableDoubleConverterBuilder.scala */

public enum PGNullableDoubleConverter implements StringConverter<Double> {
    INSTANCE;

    public static final String pgType = "double precision";

    @ToString
    public static String doubleToString(final Double d) {
        return null == d ? null : Double.toString(d);
    }

    @FromString
    public static Double stringToDouble(final String d) {
        return null == d ? null : Double.valueOf(d);
    }

// -----------------------------------------------------------------------------

    public String convertToString(final Double d) {
        return doubleToString(d);
    }

    public Double convertFromString(final Class<? extends Double> clazz, final String d) {
        return stringToDouble(d);
    }
}
