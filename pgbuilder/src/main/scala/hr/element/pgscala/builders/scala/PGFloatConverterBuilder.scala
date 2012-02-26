package hr.element.pgscala
package builder

object PGFloatConverterBuilder extends PGPredefConverterBuilder {
  val scalaClazz = "scala.Float"

  override val javaClazz = "java.lang.Float"
}
