package hr.element.pgscala.converters;

import org.joda.convert.*;

{ imports }

public enum PGNullable{ upperType }Converter implements StringConverter<Double> {
  INSTANCE;

  public static final String pgType = "double precision";

{ body }

  @ToString
  public static String { lowerType }ToString(final Double d) {
    return null == d ? null : Double.toString(d);
  }

  @FromString
  public static Double stringTo{ upperType }(final String d) {
    return null == d ? null : Double.valueOf(d);
  }

// ----------------------------------------------------------------------------

  public String convertToString(final Double d) {
    return { lowerType }ToString(d);
  }

  public Double convertFromString(final Class<? extends Double> clazz, final String d) {
    return stringTo{ upperName }(d);
  }
}
