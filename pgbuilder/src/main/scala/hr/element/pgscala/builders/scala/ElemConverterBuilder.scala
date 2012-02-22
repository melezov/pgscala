package hr.element.pgscala
package builder

object ElemConverterBuilder extends SConverterBuilder {
  val scalaClazz = "Elem"

  override val imports = "import scala.xml.Elem"
}