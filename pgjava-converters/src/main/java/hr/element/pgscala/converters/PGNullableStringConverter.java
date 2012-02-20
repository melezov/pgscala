package hr.element.pgscala.converters;

import org.joda.convert.*;

{ imports }

public enum PGNullable{ upperType }Converter implements StringConverter<String> {
  INSTANCE;

  public static final String pgType = "text";

{ body }

  @ToString
  @FromString
  public static String stringTo{ upperType }(final String s) {
    return s;
  }

// ----------------------------------------------------------------------------

  public String convertToString(final String s) {
    return { lowerType }ToString(s);
  }

  public String convertFromString(final Class<? extends String> clazz, final String s) {
    return s;
  }
}
