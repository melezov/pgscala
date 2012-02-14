package hr.element.pgscala.converters;

import org.joda.convert.*;

import org.joda.time.*;
import org.joda.time.format.*;

public final class PGNullableDateTimeConverter implements StringConverter<DateTime> {
  public static final String pgType = "timestamptz";

  private static final DateTimeFormatter dateTimeFormat =
    DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSSZZ");

  @ToString
  public static String toPGString(final DateTime dT) {
    return null == dT ? null : dateTimeFormat.print(dT);
  }

  @FromString
  public static DateTime fromPGString(final String dT) {
    return null == dT ? null : dateTimeFormat.parseDateTime(dT);
  }
  
// ----------------------------------------------------------------------------

  private PGNullableDateTimeConverter() {}
  
  public static final PGNullableDateTimeConverter INSTANCE =
    new PGNullableDateTimeConverter();
  
  public String convertToString(final DateTime dT) {
    return toPGString(dT);
  }
  
  public DateTime convertFromString(final Class<? extends DateTime> clazz, final String dT) {
    return fromPGString(dT);
  }
}
