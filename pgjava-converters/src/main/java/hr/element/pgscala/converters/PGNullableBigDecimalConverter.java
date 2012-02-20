package hr.element.pgscala.converters;

import org.joda.convert.*;

{ imports }

public enum PGNullable{ upperType }Converter implements StringConverter<BigDecimal> {
  INSTANCE;

  public static final String pgType = "decimal";

{ body }

  @ToString
  public static String { lowerType }ToString(final BigDecimal bD) {
    return null == bD ? null : bD.toString();
  }

  @FromString
  public static BigDecimal stringTo{ upperType }(final String bD) {
    return null == bD ? null : new BigDecimal(bD);
  }

// ----------------------------------------------------------------------------

  public String convertToString(final BigDecimal bD) {
    return { lowerType }ToString(bD);
  }

  public BigDecimal convertFromString(final Class<? extends BigDecimal> clazz, final String bD) {
    return stringTo{ upperName }(bD);
  }
}
