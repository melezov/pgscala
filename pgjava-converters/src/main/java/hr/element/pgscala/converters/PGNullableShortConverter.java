package hr.element.pgscala.converters;

import org.joda.convert.*;

public enum PGNullableShortConverter implements StringConverter<Short> {
  INSTANCE;

  public static final String pgType = "smallint";

  @ToString
  public static String shortToString(final Short s) {
    return null == s ? null : Short.toString(s);
  }

  @FromString
  public static Short stringToShort(final String s) {
    return null == s ? null : Short.valueOf(s);
  }

// -----------------------------------------------------------------------------

  public String convertToString(final Short s) {
    return shortToString(s);
  }

  public Short convertFromString(final Class<? extends Short> clazz, final String s) {
    return stringToShort(s);
  }
}
