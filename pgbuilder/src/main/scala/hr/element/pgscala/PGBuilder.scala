package hr.element.pgscala
package builder

trait BuilderLike {
  def filters: Seq[String => String]

  def inject(body: String) = (
    (filters :\ body)( _(_) )  // (-_(-_-)_-) The hood is watching
      split("\n{3,}")
      mkString "\n\n"
  )

  def i(key: String, value: String) =
    (_: String).replace("{ "+ key +" }", value)

  def l(word: String) =
    word.head.toLower + word.tail
}

sealed abstract class Language(val name: String)
case object Java extends Language("java")
case object Scala extends Language("scala")
case object Jasmin extends Language("jasmin")

object PGBuilder extends App {
  JConverterBuilder.buildJavaNullableConverters()
  JConverterBuilder.buildJasminProxy()
  SConverterBuilder.buildScalaConverters()
}

import scalax.file.Path

trait PGBuilder {
  protected def getProject(lang: Language) =
    lang match {
      case Java => "pgjava-converters"
      case _    => "pgscala-converters"
    }

  protected def getRoot(lang: Language) =
    Path(getProject(lang)) /  "src" / "main" / lang.name

  def getPath(lang: Language) =
    getRoot(lang) / "hr" / "element" / "pgscala" / "converters"
}
