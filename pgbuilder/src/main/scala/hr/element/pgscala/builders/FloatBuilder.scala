package hr.element.pgscala
package builders

object FloatBuilder extends Builder {
  val clazz = "Float"

  val pgType = "real"

  val to = "Float.toString(f)"

  val from = "Float.valueOf(f)"
}
