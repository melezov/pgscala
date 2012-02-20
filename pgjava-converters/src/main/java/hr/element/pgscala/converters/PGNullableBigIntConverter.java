package hr.element.pgscala.converters;

import org.joda.convert.*;

{ imports }

public enum PGNullable{ upperType }Converter implements StringConverter<BigInteger> {
  INSTANCE;

  public static final String pgType = "numeric";

{ body }

  @ToString
  public static String { lowerType }ToString(final BigInteger bI) {
    return null == bI ? null : bI.toString();
  }

  @FromString
  public static BigInteger stringTo{ upperType }(final String bI) {
    return null == bI ? null : new BigInteger(bI);
  }

// ----------------------------------------------------------------------------

  public String convertToString(final BigInteger bI) {
    return { lowerType }ToString(bI);
  }

  public BigInteger convertFromString(final Class<? extends BigInteger> clazz, final String bI) {
    return stringTo{ upperName }(bI);
  }
}
