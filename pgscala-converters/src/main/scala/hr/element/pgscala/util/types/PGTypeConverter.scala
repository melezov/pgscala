package hr.element.pgscala.util
package types

import org.joda.convert.StringConverter

trait PGTypeConverter[T] extends StringConverter[T] {
  // -----------------------------------------------------------------------------

  def convertToString(t: T): String = toString
  def convertFromString(clazz: Class[_ <: T], value: String): T = fromString(value)

  def toString(t: T): String;
  def fromString(value: String): T;

  // -----------------------------------------------------------------------------

  def toLiteralString(t: T): String =
    PGLiteral.quote(toString(t))

  def toLiteralString(oT: Option[T]): String = {
    oT match {
      case Some(t) =>
        toLiteralString(t)
      case None =>
        "NULL"
    }
  }

  def fromLiteralString(literal: String): Option[T] = {
    if (!literal.equalsIgnoreCase("NULL")) {
      Some(fromString(PGLiteral.unquote(literal)))
    }
    else {
      None
    }
  }

  // -----------------------------------------------------------------------------

  def toRecordValue(t: T): String = {
    PGRecord.quote(toString(t))
  }

  def toRecordValue(oT: Option[T]): String = {
    oT match {
      case Some(t) =>
        toRecordValue(t)
      case None =>
        ""
    }
  }

  def fromRecordValue(value: String): Option[T] = {
    if (!value.isEmpty()) {
      Some(fromString(PGRecord.unquote(value)))
    }
    else {
      None
    }
  }

  // -----------------------------------------------------------------------------

  def toArrayElement(t: T) = {
    PGArray.quote(toString(t))
  }

  def toArrayElement(oT: Option[T]): String = {
    oT match {
      case Some(t) =>
        toArrayElement(t)
      case None =>
        "NULL"
    }
  }

  def fromArrayElement(element: String): Option[T] = {
    if (!element.equalsIgnoreCase("NULL")) {
      Some(fromString(PGLiteral.unquote(element)))
    }
    else {
      None
    }
  }
}
