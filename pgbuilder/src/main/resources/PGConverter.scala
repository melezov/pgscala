package hr.element.pgscala.converters

{ imports }

/** Do not edit - generated in PGBuilder / { builder } */

object PG{ scalaUpperType }Converter extends PGConverter[{ scalaType }] {
  val PGType = PGNullable{ javaUpperType }Converter.pgType

  def toPGString({ scalaVar }: { scalaType }){ to }

  def fromPGString({ scalaVar }: String){ from }
}
