package org.pgscala.converters;

import org.joda.convert.*;

/** Do not edit - generated in PGBuilder / PGNullableIntegerConverterBuilder.scala */

public enum PGNullableIntegerConverter implements StringConverter<Integer> {
  INSTANCE;

  public static final String pgType = "integer";

  @ToString
  public static String integerToString(final Integer i) {
    return null == i ? null : Integer.toString(i);
  }

  @FromString
  public static Integer stringToInteger(final String i) {
    return null == i ? null : Integer.valueOf(i);
  }

// -----------------------------------------------------------------------------

  public String convertToString(final Integer i) {
    return integerToString(i);
  }

  public Integer convertFromString(final Class<? extends Integer> clazz, final String i) {
    return stringToInteger(i);
  }
}
