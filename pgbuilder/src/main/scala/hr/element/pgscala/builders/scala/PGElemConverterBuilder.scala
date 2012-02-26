package hr.element.pgscala
package builder

object PGElemConverterBuilder extends PGConverterBuilder {
  val scalaClazz = "Elem"

  override val imports = "import scala.xml.Elem"
}
