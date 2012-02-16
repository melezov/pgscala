package hr.element.pgscala
package builders

object IntegerBuilder extends Builder {
  val clazz = "Integer"

  val pgType = "integer"

  val to = "Integer.toString(i)"

  val from = "Integer.valueOf(i)"
}
