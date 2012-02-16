package hr.element.pgscala.converters;

import org.joda.convert.*;

import org.joda.time.LocalDate;

public enum PGNullableLocalDateConverter implements StringConverter<LocalDate> {
  INSTANCE;

  public static final String pgType = "date";

  @ToString
  public static String localDateToString(final LocalDate lD) {
    return null == lD ? null : lD.toString();
  }

  @FromString
  public static LocalDate stringToLocalDate(final String lD) {
    return null == lD ? null : new LocalDate(lD);
  }

// ----------------------------------------------------------------------------

  public String convertToString(final LocalDate lD) {
    return localDateToString(lD);
  }

  public LocalDate convertFromString(final Class<? extends LocalDate> clazz, final String lD) {
    return stringToLocalDate(lD);
  }
}