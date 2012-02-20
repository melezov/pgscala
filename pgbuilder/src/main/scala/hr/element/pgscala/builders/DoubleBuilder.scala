package hr.element.pgscala
package builders

object DoubleBuilder extends Builder {
  val clazz = "java.lang.Double"

  val pgType = "double precision"

  val to = "Double.toString(d)"

  val from = "Double.valueOf(d)"

  override def javaImports = ""
}
