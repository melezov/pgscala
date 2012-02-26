package hr.element.pgscala
package builder

object PGDoubleConverterBuilder extends PGPredefConverterBuilder {
  val scalaClazz = "scala.Double"

  override val javaClazz = "java.lang.Double"
}