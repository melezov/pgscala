package org.pgscala.converters

trait PGConverter[T] {
  val PGType: String

  def toPGString (t: T): String
  def fromPGString (value: String): T
}

object PGConverter {

//   /* Alternative version */
//   def toPGString[T: PGConverter](t: T): String =
//     implicitly[PGConverter[T]].toPGString(t)

  def toPGString[T](t: T)(implicit converter: PGConverter[T]): String
    = converter toPGString t

  def fromPGString[T](t: String)(implicit converter: PGConverter[T]): T
    = converter fromPGString t

  def pgType[T](implicit converter : PGConverter[T]): String
    = converter.PGType
}
