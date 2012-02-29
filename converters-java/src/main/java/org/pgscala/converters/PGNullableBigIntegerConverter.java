package org.pgscala.converters;

import org.joda.convert.*;

import java.math.BigInteger;

/** Do not edit - generated in PGBuilder / PGNullableBigIntegerConverterBuilder.scala */

public enum PGNullableBigIntegerConverter implements StringConverter<BigInteger> {
  INSTANCE;

  public static final String pgType = "numeric";

  @ToString
  public static String bigIntegerToString(final BigInteger bI) {
    return null == bI ? null : bI.toString();
  }

  @FromString
  public static BigInteger stringToBigInteger(final String bI) {
    return null == bI ? null : new BigInteger(bI);
  }

// -----------------------------------------------------------------------------

  public String convertToString(final BigInteger bI) {
    return bigIntegerToString(bI);
  }

  public BigInteger convertFromString(final Class<? extends BigInteger> clazz, final String bI) {
    return stringToBigInteger(bI);
  }
}
