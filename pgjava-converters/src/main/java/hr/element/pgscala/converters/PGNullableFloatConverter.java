package hr.element.pgscala.converters;

import org.joda.convert.*;

public enum PGNullableFloatConverter implements StringConverter<Float> {
  INSTANCE;

  public static final String pgType = "real";

  @ToString
  public static String floatToString(final Float f) {
    return null == f ? null : Float.toString(f);
  }

  @FromString
  public static Float stringToFloat(final String f) {
    return null == f ? null : Float.valueOf(f);
  }

// ----------------------------------------------------------------------------

  public String convertToString(final Float f) {
    return floatToString(f);
  }

  public Float convertFromString(final Class<? extends Float> clazz, final String f) {
    return stringToFloat(f);
  }
}