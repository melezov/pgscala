package org.pgscala
package builder
package converters

import scalax.file.Path

trait PGConverterBuilderPaths {
  protected def getProject(lang: Language) =
    lang match {
      case Java => "converters-java"
      case _    => "converters-scala"
    }

  protected def getRoot(lang: Language) =
    Path(getProject(lang)) /  "src" / "main" / lang.dir

  def getPath(lang: Language) =
    getRoot(lang) / "org" / "pgscala" / "converters"
}
