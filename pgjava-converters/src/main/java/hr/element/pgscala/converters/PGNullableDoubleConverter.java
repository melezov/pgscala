package hr.element.pgscala.converters;

import org.joda.convert.*;

public class PGNullableDoubleConverter {
  public static final String pgType = "double precision";

  @ToString
  public static String toPGString(final Short d) {
    return null == d ? null : d.toString();
  }

  @FromString
  public static Double fromPGString(final String d) {
    return null == d ? null : Double.valueOf(d);
  }
}
