package hr.element.pgscala
package builder

object PGLongConverterBuilder extends PGPredefConverterBuilder {
  val scalaClazz = "scala.Long"

  override val javaClazz = "java.lang.Long"
}
