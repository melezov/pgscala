package org.pgscala
package builder
package converters

trait PGNullableLocationConverterBuilderLike extends PGPredefNullableConverterBuilder {
  val pgType = "point"

  val clazz = "java.awt.geom.Point2D"

  override val upperType = "Location"
  override def javaType  = clazz
  override val javaVar   = "l"

  protected var commonMethods = """
    private static String getStringFromDouble(Double s) {
        if (s.isNaN())
            return "nan";
        else if (s == Double.POSITIVE_INFINITY)
            return  "inf";
        else if (s == Double.NEGATIVE_INFINITY)
            return  "-inf";
        else
            return String.valueOf(s);
    }
  """
  override val body = s"""
    private static Double getDoubleFromString(String s) {
        if (s.equals("nan"))
            return Double.NaN;
        else if (s.equals("inf"))
            return Double.POSITIVE_INFINITY;
        else if (s.equals("-inf"))
            return Double.NEGATIVE_INFINITY;
        else
            return Double.valueOf(s);
    }

    ${commonMethods}
  """

  val to = """return "("+ getStringFromDouble(l.getX())+","+getStringFromDouble(l.getY())+")""""

  val from = """
    double x = getDoubleFromString(l.substring(1, l.indexOf(",")));
    double y = getDoubleFromString(l.substring(l.indexOf(",")+1, l.length()-1));
    return new java.awt.geom.Point2D.Double(x, y)"""

  override def inject(body: String) =
    super.inject(body)
      .replace(
        "return null == l ? null :"
      , "if (null == l) return null;")
}

