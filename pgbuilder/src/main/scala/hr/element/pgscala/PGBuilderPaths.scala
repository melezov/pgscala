package hr.element.pgscala
package builder

import scalax.file.Path

trait PGBuilderPaths {
  protected def getProject(lang: Language) =
    lang match {
      case Java => "pgjava-converters"
      case _    => "pgscala-converters"
    }

  protected def getRoot(lang: Language) =
    Path(getProject(lang)) /  "src" / "main" / lang.dir

  def getPath(lang: Language) =
    getRoot(lang) / "hr" / "element" / "pgscala" / "converters"
}
