package hr.element.pgscala
package converters

trait PGTypeConverter[T] {
  def toPGString(t: T): String;
  def fromPGString(value: String): T;
}

object PGTypeConverter {
  def toPGString[T](t: T)(implicit impl: PGTypeConverter[T]): String = impl.toPGString(t)

  /* Alternative version */
//  def toPGString[T: PGTypeConverter](t: T): String =
//    implicitly[PGTypeConverter[T]].toPGString(t)

  def fromPGString[T](value: String)(implicit impl: PGTypeConverter[T]): T =
    impl.fromPGString(value)
}
