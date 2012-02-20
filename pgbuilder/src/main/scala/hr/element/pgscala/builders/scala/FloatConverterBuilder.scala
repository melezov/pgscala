package hr.element.pgscala
package builder

object FloatConverterBuilder extends SPredefConverterBuilder {
  def pgType = "real"

  def clazz = "scala.Float"
}
