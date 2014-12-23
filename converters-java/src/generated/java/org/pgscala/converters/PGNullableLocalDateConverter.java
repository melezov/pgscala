package org.pgscala.converters;

import org.joda.convert.*;

import org.joda.time.LocalDate;

/** Do not edit - generated in Builder / PGNullableLocalDateConverterBuilder.scala */

public enum PGNullableLocalDateConverter implements StringConverter<LocalDate> {
    INSTANCE;

    public static final String pgType = "date";

    @ToString
    public static String localDateToString(final LocalDate ld) {
        return null == ld ? null : ld.toString();
    }

    @FromString
    public static LocalDate stringToLocalDate(final String ld) {
        return null == ld ? null : new LocalDate(ld);
    }

// -----------------------------------------------------------------------------

    public String convertToString(final LocalDate ld) {
        return localDateToString(ld);
    }

    public LocalDate convertFromString(final Class<? extends LocalDate> clazz, final String ld) {
        return stringToLocalDate(ld);
    }
}
