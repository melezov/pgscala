package hr.element.pgscala.converters;

import org.joda.convert.*;

public class PGNullableStringConverter {
  public static final String pgType = "text";

  @ToString
  public static String toPGString(final String s) {
    return s;
  }

  @FromString
  public static String fromPGString(final String s) {
    return s;
  }
}
