package hr.element.pgscala
package builder

object JFloatConverterBuilder extends JPredefConverterBuilder {
  val pgType = "real"

  val clazz = "java.lang.Float"

  val to = "Float.toString(f)"

  val from = "Float.valueOf(f)"
}
