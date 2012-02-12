package hr.element.pgscala.converters;

import org.joda.convert.*;

public class PGNullableCircleConverter {
  public static final String pgType = "circle";

  @ToString
  public static String toPGString(final Boolean b) {
    return null == b ? null : b ? "t" : "f";
  }

  @FromString
  public static Boolean fromPGString(final String b) {
    return null == b ? null : b.equals("t") ? Boolean.TRUE : Boolean.FALSE;
  }
}
