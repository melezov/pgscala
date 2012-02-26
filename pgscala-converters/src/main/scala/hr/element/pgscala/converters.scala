package hr.element.pgscala
package object converters {
  trait PGConverter[T] {
    val PGType: String

    def toPGString (t: T): String
    def fromPGString (value: String): T
  }
}
