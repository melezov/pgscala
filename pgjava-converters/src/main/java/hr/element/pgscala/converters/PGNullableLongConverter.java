package hr.element.pgscala.converters;

import org.joda.convert.*;

public class PGNullableLongConverter {
  public static final String pgType = "bigint";

  @ToString
  public static String toPGString(final Short l) {
    return null == l ? null : l.toString();
  }

  @FromString
  public static Long fromPGString(final String l) {
    return null == l ? null : Long.valueOf(l);
  }
}
