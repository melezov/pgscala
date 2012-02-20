package hr.element.pgscala.converters;

import org.joda.convert.*;

{ imports }

public enum PGNullable{ upperType }Converter implements StringConverter<DateTime> {
  INSTANCE;

  public static final String pgType = "timestamptz";

{ body }

  @ToString
  public static String { lowerType }ToString(final DateTime dT) {
    return null == dT ? null : dateTimeFormat.print(dT);
  }

  @FromString
  public static DateTime stringTo{ upperType }(final String dT) {
    return null == dT ? null : dateTimeFormat.parseDateTime(dT);
  }

// ----------------------------------------------------------------------------

  public String convertToString(final DateTime dT) {
    return { lowerType }ToString(dT);
  }

  public DateTime convertFromString(final Class<? extends DateTime> clazz, final String dT) {
    return stringTo{ upperName }(dT);
  }
}
