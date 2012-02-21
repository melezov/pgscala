package hr.element.pgscala.converters;

import org.joda.convert.*;

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
