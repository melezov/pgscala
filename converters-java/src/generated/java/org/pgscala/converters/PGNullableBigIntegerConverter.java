package org.pgscala.converters;

import org.joda.convert.*;

import java.math.BigInteger;

/** Do not edit - generated in Builder / PGNullableBigIntegerConverterBuilder.scala */

public enum PGNullableBigIntegerConverter implements StringConverter<BigInteger> {
  INSTANCE;

  public static final String pgType = "numeric";

  @ToString
  public static String bigIntegerToString(final BigInteger bi) {
    return null == bi ? null : bi.toString();
  }

  @FromString
  public static BigInteger stringToBigInteger(final String bi) {
    return null == bi ? null : new BigInteger(bi);
  }

// -----------------------------------------------------------------------------

  public String convertToString(final BigInteger bi) {
    return bigIntegerToString(bi);
  }

  public BigInteger convertFromString(final Class<? extends BigInteger> clazz, final String bi) {
    return stringToBigInteger(bi);
  }
}
