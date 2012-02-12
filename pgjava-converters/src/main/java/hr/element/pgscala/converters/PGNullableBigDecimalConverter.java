package hr.element.pgscala.converters;

import org.joda.convert.*;

import java.math.BigDecimal;

public class PGNullableBigDecimalConverter {
  public static final String pgType = "decimal";

  @ToString
  public static String toPGString(final BigDecimal bD) {
    return null == bD ? null : bD.toString();
  }

  @FromString
  public static BigDecimal fromPGString(final String bD) {
    return null == bD ? null : new BigDecimal(bD);
  }
}
