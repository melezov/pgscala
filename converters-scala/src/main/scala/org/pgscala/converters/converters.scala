package org.pgscala
package object converters extends Implicits

trait Implicits {
  import converters._

  implicit val impalePGStringConverter     = PGStringConverter
  implicit val impalePGBooleanConverter    = PGBooleanConverter
  implicit val impalePGShortConverter      = PGShortConverter
  implicit val impalePGIntConverter        = PGIntConverter
  implicit val impalePGLongConverter       = PGLongConverter
  implicit val impalePGFloatConverter      = PGFloatConverter
  implicit val impalePGDoubleConverter     = PGDoubleConverter
  implicit val impalePGBigDecimalConverter = PGBigDecimalConverter
  implicit val impalePGBigIntConverter     = PGBigIntConverter
  implicit val impalePGLocalDateConverter  = PGLocalDateConverter
  implicit val impalePGDateTimeConverter   = PGDateTimeConverter
  implicit val impalePGByteArrayConverter  = PGByteArrayConverter
  implicit val impalePGUUIDConverter       = PGUUIDConverter
  implicit val impalePGElemConverter       = PGElemConverter

  implicit val impalePGOptionStringConverter     = PGOptionStringConverter
  implicit val impalePGOptionBooleanConverter    = PGOptionBooleanConverter
  implicit val impalePGOptionShortConverter      = PGOptionShortConverter
  implicit val impalePGOptionIntConverter        = PGOptionIntConverter
  implicit val impalePGOptionLongConverter       = PGOptionLongConverter
  implicit val impalePGOptionFloatConverter      = PGOptionFloatConverter
  implicit val impalePGOptionDoubleConverter     = PGOptionDoubleConverter
  implicit val impalePGOptionBigDecimalConverter = PGOptionBigDecimalConverter
  implicit val impalePGOptionBigIntConverter     = PGOptionBigIntConverter
  implicit val impalePGOptionLocalDateConverter  = PGOptionLocalDateConverter
  implicit val impalePGOptionDateTimeConverter   = PGOptionDateTimeConverter
  implicit val impalePGOptionByteArrayConverter  = PGOptionByteArrayConverter
  implicit val impalePGOptionUUIDConverter       = PGOptionUUIDConverter
  implicit val impalePGOptionElemConverter       = PGOptionElemConverter
}
