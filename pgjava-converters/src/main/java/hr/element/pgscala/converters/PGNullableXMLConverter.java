package hr.element.pgscala.converters;

import org.joda.convert.*;

public class PGNullableXMLConverter {
  public static final String pgType = "smallint";

  @ToString
  public static String toPGString(final Short s) {
    return null == s ? null : s.toString();
  }

  @FromString
  public static Short fromPGString(final String s) {
    return null == s ? null : Short.valueOf(s);
  }
}
