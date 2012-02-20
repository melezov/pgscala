package hr.element.pgscala
package builders

object IntegerBuilder extends Builder {
  val clazz = "java.lang.Integer"

  val pgType = "integer"

  val to = "Integer.toString(i)"

  val from = "Integer.valueOf(i)"

  override def scalaType =
    "Int"

  override def fileName =
    scalaType
}
