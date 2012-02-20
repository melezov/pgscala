package hr.element.pgscala.converters;

import org.joda.convert.*;

{ imports }

public enum PGNullable{ upperType }Converter implements StringConverter<Float> {
  INSTANCE;

  public static final String pgType = "real";

{ body }

  @ToString
  public static String { lowerType }ToString(final Float f) {
    return null == f ? null : Float.toString(f);
  }

  @FromString
  public static Float stringTo{ upperType }(final String f) {
    return null == f ? null : Float.valueOf(f);
  }

// ----------------------------------------------------------------------------

  public String convertToString(final Float f) {
    return { lowerType }ToString(f);
  }

  public Float convertFromString(final Class<? extends Float> clazz, final String f) {
    return stringTo{ upperName }(f);
  }
}
