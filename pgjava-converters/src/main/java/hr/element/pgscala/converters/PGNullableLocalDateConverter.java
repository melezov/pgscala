package hr.element.pgscala.converters;

import org.joda.convert.*;

{ imports }

public enum PGNullable{ upperType }Converter implements StringConverter<LocalDate> {
  INSTANCE;

  public static final String pgType = "date";

{ body }

  @ToString
  public static String { lowerType }ToString(final LocalDate lD) {
    return null == lD ? null : lD.toString();
  }

  @FromString
  public static LocalDate stringTo{ upperType }(final String lD) {
    return null == lD ? null : new LocalDate(lD);
  }

// ----------------------------------------------------------------------------

  public String convertToString(final LocalDate lD) {
    return { lowerType }ToString(lD);
  }

  public LocalDate convertFromString(final Class<? extends LocalDate> clazz, final String lD) {
    return stringTo{ upperName }(lD);
  }
}
