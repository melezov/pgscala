package org.pgscala
package builder
package converters

object PGNullableLocationConverterBuilder extends PGPredefNullableConverterBuilder {
  val pgType = "point"

  val clazz = "java.awt.geom.Point2D"

  override val upperType = "Location"
  override def javaType  = clazz

  override val body = """
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
  """
  
  val to = """return "("+ l.getX()+","+l.getY()+")""""

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

