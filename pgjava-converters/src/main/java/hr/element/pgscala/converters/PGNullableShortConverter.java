package hr.element.pgscala.converters;

import org.joda.convert.*;

{ imports }

public enum PGNullable{ upperType }Converter implements StringConverter<Short> {
  INSTANCE;

  public static final String pgType = "smallint";

{ body }

  @ToString
  public static String { lowerType }ToString(final Short s) {
    return null == s ? null : Short.toString(s);
  }

  @FromString
  public static Short stringTo{ upperType }(final String s) {
    return null == s ? null : Short.valueOf(s);
  }

// ----------------------------------------------------------------------------

  public String convertToString(final Short s) {
    return { lowerType }ToString(s);
  }

  public Short convertFromString(final Class<? extends Short> clazz, final String s) {
    return stringTo{ upperName }(s);
  }
}
