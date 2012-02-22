package hr.element.pgscala
package builder

object JElemConverterBuilder extends JConverterBuilder {
  override val imports = """import scala.io.Source;
import scala.xml.Elem;
import scala.xml.parsing.ConstructingParser;"""

  val pgType = "xml"

  val clazz = "scala.xml.Elem"

  val to = "e.toString()"

  val from = """
      (Elem) ConstructingParser
        .fromSource(Source.fromString(e), true)
        .document().docElem()"""

  override val isScala = true
}
