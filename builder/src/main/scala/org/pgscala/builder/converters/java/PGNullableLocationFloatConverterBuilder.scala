package org.pgscala
package builder
package converters

object PGNullableLocationFloatConverterBuilder extends PGNullableLocationConverterBuilderLike {
  override val clazz = "java.awt.geom.Point2D.Float"
  override val upperType = "LocationFloat"

  override val body = s"""
    private static Float getFloatFromString(String s) {
        if (s.equals("nan"))
            return Float.NaN;
        else if (s.equals("inf"))
            return Float.POSITIVE_INFINITY;
        else if (s.equals("-inf"))
            return Float.NEGATIVE_INFINITY;
        else
            return Float.valueOf(s);
    }

   $commonMethods
  """

  override val from = """
    float x = getFloatFromString(l.substring(1, l.indexOf(",")));
    float y = getFloatFromString(l.substring(l.indexOf(",")+1, l.length()-1));
    return new java.awt.geom.Point2D.Float(x, y)"""
}

