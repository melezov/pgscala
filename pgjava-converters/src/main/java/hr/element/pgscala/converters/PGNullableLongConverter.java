package hr.element.pgscala.converters;

import org.joda.convert.*;

{ imports }

public enum PGNullable{ upperType }Converter implements StringConverter<Long> {
  INSTANCE;

  public static final String pgType = "bigint";

{ body }

  @ToString
  public static String { lowerType }ToString(final Long l) {
    return null == l ? null : Long.toString(l);
  }

  @FromString
  public static Long stringTo{ upperType }(final String l) {
    return null == l ? null : Long.valueOf(l);
  }

// ----------------------------------------------------------------------------

  public String convertToString(final Long l) {
    return { lowerType }ToString(l);
  }

  public Long convertFromString(final Class<? extends Long> clazz, final String l) {
    return stringTo{ upperName }(l);
  }
}
