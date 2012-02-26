package hr.element.pgscala.converters

/** Do not edit - generated in PGBuilder / PGIntConverterBuilder.scala */

object PGOptionIntConverter extends PGConverter[Option[Int]] {
  val PGType = PGIntConverter.PGType

  def toPGString(oI: Option[Int]): String =
    oI match {
      case None =>
        null
      case Some(i) =>
        PGIntConverter.toPGString(i)
    }

  def fromPGString(i: String): Option[Int] =
    i match {
      case null | "" =>
        None
      case oI =>
        Some(PGIntConverter.fromPGString(oI))
    }
}
