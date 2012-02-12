package hr.element.pgscala.converters;

import org.joda.convert.*;

import org.joda.time.*;

public class PGNullableDateConverter {
  public static final String pgType = "date";

  @ToString
  public static String toPGString(final LocalDate lD) {
    return null == lD ? null : lD.toString();
  }

  @FromString
  public static LocalDate fromPGString(final String dT) {
    return null == dT ? null : new LocalDate(dT);
  }
}
