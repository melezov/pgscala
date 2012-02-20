package hr.element.pgscala
package builder

object JDoubleConverterBuilder extends JConverterBuilder {
  val pgType = "double precision"

  val clazz = "java.lang.Double"

  val to = "Double.toString(d)"

  val from = "Double.valueOf(d)"
}
