package hr.element.pgscala.util
package types

import org.joda.convert.StringConverter

trait PGTypeConverter[T] extends StringConverter[T] {

// -----------------------------------------------------------------------------

  def convertToString(t: T): String = toString(t)
  def convertFromString(clazz: Class[_ <: T], value: String): T = fromString(value)

  def toString(t: T): String;
  def fromString(value: String): T;
}

object PGTypeConverter {
  def toArray[T](elements: Traversable[T], converter: PGTypeConverter[T]): String =
    PGArray.pack(elements.map(converter.toString).toArray)

  def fromArray[T](array: String, converter: PGTypeConverter[T]): IndexedSeq[T] =
    PGArray.unpack(array).map(converter.fromString)
}

/*
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

  def toArrayElement(oT: Option[T]): String = {
    oT match {
      case Some(t) =>
        toArrayElement(t)
      case None =>
        "NULL"
    }
  }

  def fromArrayElement(unpackedElement: String): Option[T] = {
    if (unpackedElement eq null) {
      None
    }
    else {
      Some(fromString(unpackedElement))
    }
  }
*/
