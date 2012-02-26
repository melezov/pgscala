package hr.element.pgscala.converters;

import org.joda.convert.*;

import org.joda.time.DateTime;
import org.joda.time.format.*;

/** Do not edit - generated in PGBuilder / PGNullableDateTimeConverterBuilder.scala */

public enum PGNullableDateTimeConverter implements StringConverter<DateTime> {
  INSTANCE;

  public static final String pgType = "timestamptz";

  private static final DateTimeFormatter dateTimeFormat =
    DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSSZZ");

  @ToString
  public static String dateTimeToString(final DateTime dT) {
    return null == dT ? null : dateTimeFormat.print(dT);
  }

  @FromString
  public static DateTime stringToDateTime(final String dT) {
    return null == dT ? null : dateTimeFormat.parseDateTime(dT);
  }

// -----------------------------------------------------------------------------

  public String convertToString(final DateTime dT) {
    return dateTimeToString(dT);
  }

  public DateTime convertFromString(final Class<? extends DateTime> clazz, final String dT) {
    return stringToDateTime(dT);
  }
}
