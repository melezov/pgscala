package hr.element.pgscala.converters;

import org.joda.convert.*;

import java.math.BigDecimal;

public enum PGNullableBigDecimalConverter implements StringConverter<BigDecimal> {
  INSTANCE;

  public static final String pgType = "decimal";

  @ToString
  public static String bigDecimalToString(final BigDecimal bD) {
    return null == bD ? null : bD.toString();
  }

  @FromString
  public static BigDecimal stringToBigDecimal(final String bD) {
    return null == bD ? null : new BigDecimal(bD);
  }

// ----------------------------------------------------------------------------

  public String convertToString(final BigDecimal bD) {
    return bigDecimalToString(bD);
  }

  public BigDecimal convertFromString(final Class<? extends BigDecimal> clazz, final String bD) {
    return stringToBigDecimal(bD);
  }
}