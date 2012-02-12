package hr.element.pgscala.converters;

import org.joda.convert.*;

public class PGNullablePolygonConverter {
  public static final String pgType = "real";

  @ToString
  public static String toPGString(final Short f) {
    return null == f ? null : f.toString();
  }

  @FromString
  public static Float fromPGString(final String f) {
    return null == f ? null : Float.valueOf(f);
  }
}
