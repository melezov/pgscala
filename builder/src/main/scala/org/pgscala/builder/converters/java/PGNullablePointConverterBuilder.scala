package org.pgscala
package builder
package converters

object PGNullablePointConverterBuilder extends PGPredefNullableConverterBuilder {
  val pgType = "point"

  val clazz = "java.awt.Point"

  override val upperType = "Point"
  override def javaType  = clazz

  val to = """return "("+ (int) p.getX()+","+ (int) p.getY()+")""""

  val from = """
    if (p.indexOf("nan")!=-1 ||p.indexOf("inf")!=-1) return null;
    int x = Integer.valueOf(p.substring(1, p.indexOf(",")));
    int y = Integer.valueOf(p.substring(p.indexOf(",")+1, p.length()-1));
    return new java.awt.Point(x, y)"""

  override def inject(body: String) =
    super.inject(body)
      .replace(
        "return null == p ? null :"
      , "if (null == p) return null;")
}

