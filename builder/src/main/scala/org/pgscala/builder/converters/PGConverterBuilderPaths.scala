package org.pgscala
package builder
package converters

import scalax.file.Path

trait PGConverterBuilderPaths {
  protected def normalizeBuilderPath(path: String) =
    Path(Path(path).toAbsolute.path
        .replace("\\", "/")
        .replace("/builder/", "/"), '/')

  protected def getProject(lang: Language) =
    normalizeBuilderPath(lang match {
      case Language.Java => "converters-java"
      case _             => "converters-scala"
    })

  protected def getRoot(project: Language, dir: Language) =
    getProject(project) /  "src" / "generated" / dir.dir

  def getPath(project: Language) =
    getRoot(project, project) / "org" / "pgscala" / "converters"

  def getPath(project: Language, dir: Language) =
    getRoot(project, dir) / "org" / "pgscala" / "converters"
}
