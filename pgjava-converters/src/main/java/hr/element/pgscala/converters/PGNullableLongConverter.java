package hr.element.pgscala.converters;

import org.joda.convert.*;

public enum PGNullableLongConverter implements StringConverter<Long> {
  INSTANCE;

  public static final String pgType = "bigint";

  @ToString
  public static String longToString(final Long l) {
    return null == l ? null : Long.toString(l);
  }

  @FromString
  public static Long stringToLong(final String l) {
    return null == l ? null : Long.valueOf(l);
  }

// -----------------------------------------------------------------------------

  public String convertToString(final Long l) {
    return longToString(l);
  }

  public Long convertFromString(final Class<? extends Long> clazz, final String l) {
    return stringToLong(l);
  }
}
