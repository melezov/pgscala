package hr.element.pgscala.converters

{ imports }

object PG{ upperType }Converter extends PGTypeConverter[{ scalaType }] {
  val PGType = PGNullable{ upperType }Converter.pgType

  def toPGString({ scalaVar }: { scalaType }): String =
    PGNullableConverter.toPGString({ scalaVar })

  def fromPGString({ scalaVar }: String): { scalaType } =
    PGNullableConverter.fromPGString({ scalaVar })
}
