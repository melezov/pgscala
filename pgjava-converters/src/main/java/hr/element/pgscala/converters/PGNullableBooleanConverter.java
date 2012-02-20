package hr.element.pgscala.converters;

import org.joda.convert.*;

{ imports }

public enum PGNullable{ upperType }Converter implements StringConverter<Boolean> {
  INSTANCE;

  public static final String pgType = "boolean";

{ body }

  @ToString
  public static String { lowerType }ToString(final Boolean b) {
    return null == b ? null : b ? "t" : "f";
  }

  @FromString
  public static Boolean stringTo{ upperType }(final String b) {
    return null == b ? null : b.equals("t") ? Boolean.TRUE : Boolean.FALSE;
  }

// ----------------------------------------------------------------------------

  public String convertToString(final Boolean b) {
    return { lowerType }ToString(b);
  }

  public Boolean convertFromString(final Class<? extends Boolean> clazz, final String b) {
    return stringTo{ upperName }(b);
  }
}
