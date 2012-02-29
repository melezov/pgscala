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

  protected def getRoot(project: Language, dir: Language) =
    Path(getProject(project)) /  "src" / "main" / dir.dir

  def getPath(project: Language) =
    getRoot(project, project) / "org" / "pgscala" / "converters"

  def getPath(project: Language, dir: Language) =
    getRoot(project, dir) / "org" / "pgscala" / "converters"
}
