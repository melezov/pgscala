package hr.element.pgscala.converters;

import org.joda.convert.*;

{ javaImports }

public enum PGNullable{ javaType }Converter implements StringConverter<{ javaType }> {
  INSTANCE;

  public static final String pgType = "timestamptz";

{ pre }

  @ToString
  public static String { javaTypeLower }ToString(final { javaType } { javaVar }) {
    return null == { javaVar } ? null : { to };
  }

  @FromString
  public static { javaType } stringTo{ javaType }(final String { javaVar }) {
    return null == { javaVar } ? null : { from };
  }

// ----------------------------------------------------------------------------

  public String convertToString(final { javaType } { javaVar }) {
    return { javaTypeLower }ToString({ javaVar });
  }

  public { javaType } convertFromString(final Class<{ javaType }> clazz, final String { javaVar }) {
    return stringTo{ javaType }({ javaVar });
  }
}