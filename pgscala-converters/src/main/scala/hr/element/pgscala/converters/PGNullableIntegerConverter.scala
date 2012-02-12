package hr.element.pgscala.converters

object PGNullableIntegerConverter extends PGTypeConverter[Option[Int]] {
  def toPGString(value: Option[Int]): String =
    value match {
      case Some(i) =>
        PGIntegerConverter.toPGString(i)
      case _ =>
        null
    }

  def fromPGString(value: String): Option[Int] =
    if(value != null && value.nonEmpty) {
      Some(PGIntegerConverter.fromPGString(value))
    }
    else {
      None
    }

  override val PGType = java.sql.Types.INTEGER
}
