package hr.element.pgscala
package builder

object BooleanConverterBuilder extends SPredefConverterBuilder {
  val scalaClazz = "scala.Boolean"

  override val javaClazz = "java.lang.Boolean"
}