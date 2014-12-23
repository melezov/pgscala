package org.pgscala.converters;

import org.joda.convert.*;

/** Do not edit - generated in Builder / PGNullableStringConverterBuilder.scala */

public enum PGNullableStringConverter implements StringConverter<String> {
    INSTANCE;

    public static final String pgType = "text";

    @ToString
    @FromString
    public static String stringToString(final String s) {
        return s;
    }

// -----------------------------------------------------------------------------

    public String convertToString(final String s) {
        return s;
    }

    public String convertFromString(final Class<? extends String> clazz, final String s) {
        return s;
    }
}
