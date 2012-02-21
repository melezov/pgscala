package hr.element.pgscala
package builder

object IntConverterBuilder extends SPredefConverterBuilder {
  val scalaClazz = "scala.Int"

  override val javaClazz = "java.lang.Integer"
}