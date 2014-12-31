package org.pgscala.converters

trait Implicits {
  implicit val implicitPGStringConverter         = PGStringConverter
  implicit val implicitPGBooleanConverter        = PGBooleanConverter
  implicit val implicitPGShortConverter          = PGShortConverter
  implicit val implicitPGIntConverter            = PGIntConverter
  implicit val implicitPGLongConverter           = PGLongConverter
  implicit val implicitPGFloatConverter          = PGFloatConverter
  implicit val implicitPGDoubleConverter         = PGDoubleConverter
  implicit val implicitPGBigDecimalConverter     = PGBigDecimalConverter
  implicit val implicitPGBigIntConverter         = PGBigIntConverter
  implicit val implicitPGLocalDateConverter      = PGLocalDateConverter
  implicit val implicitPGDateTimeConverter       = PGDateTimeConverter
  implicit val implicitPGByteArrayConverter      = PGByteArrayConverter
  implicit val implicitPGBufferedImageConverter  = PGBufferedImageConverter
  implicit val implicitPGURLConverter            = PGURLConverter
  implicit val implicitPGUUIDConverter           = PGUUIDConverter
  implicit val implicitPGElemConverter           = PGElemConverter
  implicit val implicitPGMapConverter            = PGMapConverter
  implicit val implicitPGLocationDoubleConverter = PGLocationDoubleConverter
  implicit val implicitPGPointConverter          = PGPointConverter

  implicit val implicitPGOptionStringConverter         = PGOptionStringConverter
  implicit val implicitPGOptionBooleanConverter        = PGOptionBooleanConverter
  implicit val implicitPGOptionShortConverter          = PGOptionShortConverter
  implicit val implicitPGOptionIntConverter            = PGOptionIntConverter
  implicit val implicitPGOptionLongConverter           = PGOptionLongConverter
  implicit val implicitPGOptionFloatConverter          = PGOptionFloatConverter
  implicit val implicitPGOptionDoubleConverter         = PGOptionDoubleConverter
  implicit val implicitPGOptionBigDecimalConverter     = PGOptionBigDecimalConverter
  implicit val implicitPGOptionBigIntConverter         = PGOptionBigIntConverter
  implicit val implicitPGOptionLocalDateConverter      = PGOptionLocalDateConverter
  implicit val implicitPGOptionDateTimeConverter       = PGOptionDateTimeConverter
  implicit val implicitPGOptionByteArrayConverter      = PGOptionByteArrayConverter
  implicit val implicitPGOptionBufferedImageConverter  = PGOptionBufferedImageConverter
  implicit val implicitPGOptionURLConverter            = PGOptionURLConverter
  implicit val implicitPGOptionUUIDConverter           = PGOptionUUIDConverter
  implicit val implicitPGOptionElemConverter           = PGOptionElemConverter
  implicit val implicitPGOptionMapConverter            = PGOptionMapConverter
  implicit val implicitPGOptionLocationDoubleConverter = PGOptionLocationDoubleConverter
  implicit val implicitPGOptionPointConverter          = PGOptionPointConverter
}
