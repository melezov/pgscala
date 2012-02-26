package hr.element.pgscala
package builder

object PGBooleanConverterBuilder extends PGPredefConverterBuilder {
  val scalaClazz = "scala.Boolean"

  override val javaClazz = "java.lang.Boolean"
}