package hr.element.pgscala.converters;

import org.joda.convert.*;

import java.util.regex.Pattern;

import org.joda.time.*;

public class PGNullableDurationConverter {
  public static final String pgType = "interval";

  private static final Pattern durationPattern =
    Pattern.compile(
      "(?:([-+]?\\d+) years?)? ?" +
      "(?:([-+]?\\d+) mons?)? ?" +
      "(?:([-+]?\\d+) days?)? ?" +
      "(?:([-+]\\d{2}):(\\d{2}):(\\d{2}\\.\\d+))?"
    );

  @ToString
  public static String toPGString(final Duration d) {
    return null == d ? null : d.toString();
  }

  @FromString
  public static Duration fromPGString(final String d) {
    return null == d ? null : null;
  }
}
