package hr.element.pgscala.converters;

import org.joda.convert.*;

public class PGNullableIntegerConverter {
  public static final String pgType = "integer";

  @ToString
  public static String toPGString(final Short i) {
    return null == i ? null : i.toString();
  }

  @FromString
  public static Integer fromPGString(final String i) {
    return null == i ? null : Integer.valueOf(i);
  }
}
