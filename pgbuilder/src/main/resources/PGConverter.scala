package hr.element.pgscala.converters

{ imports }

object PG{ scalaUpperType }Converter extends PGTypeConverter[{ scalaType }] {
  val PGType = PGNullable{ javaUpperType }Converter.pgType

  def toPGString({ scalaVar }: { scalaType }){ to }

  def fromPGString({ scalaVar }: String){ from }
}
