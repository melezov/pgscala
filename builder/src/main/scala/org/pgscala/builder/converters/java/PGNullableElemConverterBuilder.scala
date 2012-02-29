package org.pgscala
package builder
package converters

object PGNullableElemConverterBuilder extends PGNullableConverterBuilder {
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

  override val language = Scala
}
