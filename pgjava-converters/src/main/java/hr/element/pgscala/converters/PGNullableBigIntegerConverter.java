package hr.element.pgscala.converters;

import org.joda.convert.*;

import java.math.BigInteger;

public class PGNullableBigIntegerConverter {
  public static final String pgType = "numeric";

  @ToString
  public static String toPGString(final BigInteger bI) {
    return null == bI ? null : bI.toString();
  }

  @FromString
  public static BigInteger fromPGString(final String bI) {
    return null == bI ? null : new BigInteger(bI);
  }
}
