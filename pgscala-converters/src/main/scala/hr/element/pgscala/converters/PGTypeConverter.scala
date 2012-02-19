package hr.element.pgscala
package converters

trait PGTypeConverter[T] {
  import PGTypeConverter._

  def toPGString(t: T): String
  def fromPGString(t: String): T
}

object PGTypeConverter {
  def toPGString[T](t: T)(implicit converter: PGTypeConverter[T]): String =
    converter toPGString t

  def fromPGString[T](t: String)(implicit converter: PGTypeConverter[T]): T =
    converter fromPGString t
/*
  def pgType[T](implicit converter : PGTypeConverter[T]): PGTypeRep =
    converter.PGType
*/
}

// // A bundle of type-specific PG functionality.
// trait PGC [T] {

//   val PGType : PGTypeRep
//   type PGT <: AnyRef
//   def toBaseRep (t: T): PGT
//   def fromBaseRep (pg: PGT):

// }

// trait LowPriImplicits {
//   def metaImpale1 [T: PG] : PG[TraversableOnce[PG]] = new PG[..] {
//   }
// }

// trait Implicits extends LowPriImplicits {
//   val impaleBool : PG[Boolean] = new PG [....] ...
//   val impaleInt  : PG[Int] = new PG [Int] {...}

//   impaleByteArray : PG[Array[Byte]] = new

// }

// object Imports extends Implicits
