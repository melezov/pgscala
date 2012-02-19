package hr.element.pgscala.converters

{ scalaImports }

object PG{ fileName }Converter extends PGTypeConverter[{ scalaType }] {
  val PGType = PGNullable{ fileName }Converter.pgType

  def toPGString({ javaVar }: { scalaType }): String =
    PGNullableConverter.toPGString({ javaVar })

  def fromPGString({ javaVar }: String): { scalaType } =
    PGNullableConverter.fromPGString({ javaVar })
}
