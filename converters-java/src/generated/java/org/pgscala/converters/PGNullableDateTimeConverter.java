package org.pgscala.converters;

import org.joda.convert.*;

import org.joda.time.DateTime;
import org.joda.time.format.*;

/** Do not edit - generated in Builder / PGNullableDateTimeConverterBuilder.scala */

public enum PGNullableDateTimeConverter implements StringConverter<DateTime> {
    INSTANCE;

    public static final String pgType = "timestamptz";

    private static final DateTimeFormatter dateTimeFormat =
            DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSSSSSZZ");

    private static final DateTimeFormatter dateTimeSecondFormat =
            DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ssZZ");

    @ToString
    public static String dateTimeToString(final DateTime dt) {
        return null == dt ? null : dateTimeFormat.print(dt);
    }

    @FromString
    public static DateTime stringToDateTime(final String dt) {
        if (null == dt) return null;

        try {
            return dateTimeFormat.parseDateTime(dt);
        } catch (final IllegalArgumentException e) {
            return dateTimeSecondFormat.parseDateTime(dt);
        }
    }

// -----------------------------------------------------------------------------

    public String convertToString(final DateTime dt) {
        return dateTimeToString(dt);
    }

    public DateTime convertFromString(final Class<? extends DateTime> clazz, final String dt) {
        return stringToDateTime(dt);
    }
}
