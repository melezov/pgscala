package hr.element.pgscala
package builder

object PGIntConverterBuilder extends PGPredefConverterBuilder {
  val scalaClazz = "scala.Int"

  override val javaClazz = "java.lang.Integer"
}
