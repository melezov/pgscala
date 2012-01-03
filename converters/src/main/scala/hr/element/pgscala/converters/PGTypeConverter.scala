package hr.element.pgscala
package converters

import org.joda.convert.StringConverter

trait PGTypeConverter[T] extends StringConverter[T] {

// -----------------------------------------------------------------------------

  def convertToString(t: T): String = toString(t)
  def convertFromString(clazz: Class[_ <: T], value: String): T = fromString(value)

  def toString(t: T): String;
  def fromString(value: String): T;
}
