package hr.element.pgscala.converters;

import org.joda.convert.*;

{ imports }

public enum PGNullable{ upperType }Converter implements StringConverter<Integer> {
  INSTANCE;

  public static final String pgType = "integer";

{ body }

  @ToString
  public static String { lowerType }ToString(final Integer i) {
    return null == i ? null : Integer.toString(i);
  }

  @FromString
  public static Integer stringTo{ upperType }(final String i) {
    return null == i ? null : Integer.valueOf(i);
  }

// ----------------------------------------------------------------------------

  public String convertToString(final Integer i) {
    return { lowerType }ToString(i);
  }

  public Integer convertFromString(final Class<? extends Integer> clazz, final String i) {
    return stringTo{ upperName }(i);
  }
}
