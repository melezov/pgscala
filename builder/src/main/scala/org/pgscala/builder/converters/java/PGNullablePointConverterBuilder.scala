package org.pgscala
package builder
package converters

object PGNullablePointConverterBuilder extends PGPredefNullableConverterBuilder {
  val pgType = "point"

  val clazz = "java.awt.geom.Point2D"

  override val upperType = "Point"
  override def javaType  = clazz
  
  val to = """return "("+ p.getX()+","+p.getY()+")""""

  val from = """
    double x = Double.valueOf(p.substring(1, p.indexOf(",")));
    double y = Double.valueOf(p.substring(p.indexOf(",")+1, p.length()-1));
    return new java.awt.geom.Point2D.Double(x, y)"""

  override def inject(body: String) =
    super.inject(body)
      .replace(
        "return null == p ? null :",
        """if (null == p) return null;
    """)
}

