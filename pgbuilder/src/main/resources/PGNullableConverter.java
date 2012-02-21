package hr.element.pgscala.converters;

import org.joda.convert.*;

{ imports }

public enum PGNullable{ upperType }Converter implements StringConverter<{ javaType }> {
  INSTANCE;

  public static final String pgType = "{ pgType }";

{ body }

  @ToString
  public static String { lowerType }ToString(final { javaType } { javaVar }) {
    return null == { javaVar } ? null : { to };
  }

  @FromString
  public static { javaType } stringTo{ upperType }(final String { javaVar }) {
    return null == { javaVar } ? null : { from };
  }

// -----------------------------------------------------------------------------

  public String convertToString(final { javaType } { javaVar }) {
    return { lowerType }ToString({ javaVar });
  }

  public { javaType } convertFromString(final Class<? extends { javaType }> clazz, final String { javaVar }) {
    return stringTo{ upperType }({ javaVar });
  }
}
