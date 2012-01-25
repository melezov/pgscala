package hr.element.pgscala

package object converters extends Implicits

trait Implicits {
  import converters._

  implicit val impalePGBigDecimalConverter: PGTypeConverter[BigDecimal] = PGBigDecimalConverter
  implicit val impalePGBooleanConverter: PGTypeConverter[Boolean] = PGBooleanConverter
/*  implicit val impalePGDateConverter = PGDateConverter
  implicit val impalePGDateTimeConverter = PGDateTimeConverter
  implicit val impalePGDoubleConverter = PGDoubleConverter
  implicit val impalePGElemConverter = PGElemConverter
  implicit val impalePGFloatConverter = PGFloatConverter
  implicit val impalePGIntegerConverter = PGIntegerConverter
  implicit val impalePGLongConverter = PGLongConverter
  implicit val impalePGNullableBigDecimalConverter = PGNullableBigDecimalConverter
  implicit val impalePGNullableBooleanConverter = PGNullableBooleanConverter
  implicit val impalePGNullableDateConverter = PGNullableDateConverter
  implicit val impalePGNullableDateTimeConverter = PGNullableDateTimeConverter
  implicit val impalePGNullableDoubleConverter = PGNullableDoubleConverter
  implicit val impalePGNullableElemConverter = PGNullableElemConverter
  implicit val impalePGNullableFloatConverter = PGNullableFloatConverter
  implicit val impalePGNullableIntegerConverter = PGNullableIntegerConverter
  implicit val impalePGNullableLongConverter = PGNullableLongConverter
  implicit val impalePGNullableShortConverter = PGNullableShortConverter
  implicit val impalePGNullableStringConverter = PGNullableStringConverter
  implicit val impalePGNullableUUIDConverter = PGNullableUUIDConverter
  implicit val impalePGShortConverter = PGShortConverter
  implicit val impalePGStringConverter = PGStringConverter
  implicit val impalePGUUIDConverter = PGUUIDConverter
*/
}
