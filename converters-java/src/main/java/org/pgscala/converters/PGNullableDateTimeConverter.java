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
  public static String dateTimeToString(final DateTime dT) {
    return null == dT ? null : dateTimeFormat.print(dT);
  }

  @FromString
  public static DateTime stringToDateTime(final String dT) {
    if (null == dT) return null;
    try {
      return dateTimeFormat.parseDateTime(dT);
    }
    catch(final IllegalArgumentException e) {
      return dateTimeSecondFormat.parseDateTime(dT);
    }
  }

// -----------------------------------------------------------------------------

  public String convertToString(final DateTime dT) {
    return dateTimeToString(dT);
  }

  public DateTime convertFromString(final Class<? extends DateTime> clazz, final String dT) {
    return stringToDateTime(dT);
  }
}
