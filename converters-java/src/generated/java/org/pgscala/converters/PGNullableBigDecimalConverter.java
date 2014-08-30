package org.pgscala.converters;

import org.joda.convert.*;

import java.math.BigDecimal;

/** Do not edit - generated in Builder / PGNullableBigDecimalConverterBuilder.scala */

public enum PGNullableBigDecimalConverter implements StringConverter<BigDecimal> {
  INSTANCE;

  public static final String pgType = "decimal";

  @ToString
  public static String bigDecimalToString(final BigDecimal bd) {
    return null == bd ? null : bd.toString();
  }

  @FromString
  public static BigDecimal stringToBigDecimal(final String bd) {
    return null == bd ? null : new BigDecimal(bd);
  }

// -----------------------------------------------------------------------------

  public String convertToString(final BigDecimal bd) {
    return bigDecimalToString(bd);
  }

  public BigDecimal convertFromString(final Class<? extends BigDecimal> clazz, final String bd) {
    return stringToBigDecimal(bd);
  }
}
