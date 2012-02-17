.class public hr.element.pgscala.converters.PGNullableConverter
.super java/lang/Object

.method public static fromString(Ljava/lang/String;)Ljava/lang/String;
  .limit locals 1
  .limit stack 1
  
  aload_0
  areturn
.end method

.method public static fromString(Ljava/lang/String;)Ljava/lang/Boolean;
  .limit locals 1
  .limit stack 2

  aload_0
  invokestatic hr/element/pgscala/converters/PGNullableBooleanConverter.stringToBoolean(Ljava/lang/String;)Ljava/lang/Boolean;
  areturn
.end method

.method public static fromString(Ljava/lang/String;)Ljava/lang/Short;
  .limit locals 1
  .limit stack 2

  aload_0
  invokestatic hr/element/pgscala/converters/PGNullableShortConverter.stringToShort(Ljava/lang/String;)Ljava/lang/Short;
  areturn
.end method

.method public static fromString(Ljava/lang/String;)Ljava/lang/Integer;
  .limit locals 1
  .limit stack 2

  aload_0
  invokestatic hr/element/pgscala/converters/PGNullableIntegerConverter.stringToInteger(Ljava/lang/String;)Ljava/lang/Integer;
  areturn
.end method

.method public static fromString(Ljava/lang/String;)Ljava/lang/Long;
  .limit locals 1
  .limit stack 2

  aload_0
  invokestatic hr/element/pgscala/converters/PGNullableLongConverter.stringToLong(Ljava/lang/String;)Ljava/lang/Long;
  areturn
.end method

.method public static fromString(Ljava/lang/String;)Ljava/lang/Float;
  .limit locals 1
  .limit stack 2

  aload_0
  invokestatic hr/element/pgscala/converters/PGNullableFloatConverter.stringToFloat(Ljava/lang/String;)Ljava/lang/Float;
  areturn
.end method

.method public static fromString(Ljava/lang/String;)Ljava/lang/Double;
  .limit locals 1
  .limit stack 2

  aload_0
  invokestatic hr/element/pgscala/converters/PGNullableDoubleConverter.stringToDouble(Ljava/lang/String;)Ljava/lang/Double;
  areturn
.end method

.method public static fromString(Ljava/lang/String;)Ljava/math/BigDecimal;
  .limit locals 1
  .limit stack 2

  aload_0
  invokestatic hr/element/pgscala/converters/PGNullableBigDecimalConverter.stringToBigDecimal(Ljava/lang/String;)Ljava/math/BigDecimal;
  areturn
.end method

.method public static fromString(Ljava/lang/String;)Ljava/math/BigInteger;
  .limit locals 1
  .limit stack 2

  aload_0
  invokestatic hr/element/pgscala/converters/PGNullableBigIntegerConverter.stringToBigInteger(Ljava/lang/String;)Ljava/math/BigInteger;
  areturn
.end method

.method public static fromString(Ljava/lang/String;)[B
  .limit locals 1
  .limit stack 2

  aload_0
  invokestatic hr/element/pgscala/converters/PGNullableByteArrayConverter.stringToByteArray(Ljava/lang/String;)[B
  areturn
.end method

.method public static fromString(Ljava/lang/String;)Lorg/joda/time/LocalDate;
  .limit locals 1
  .limit stack 2

  aload_0
  invokestatic hr/element/pgscala/converters/PGNullableLocalDateConverter.stringToLocalDate(Ljava/lang/String;)Lorg/joda/time/LocalDate;
  areturn
.end method

.method public static fromString(Ljava/lang/String;)Lorg/joda/time/DateTime;
  .limit locals 1
  .limit stack 2

  aload_0
  invokestatic hr/element/pgscala/converters/PGNullableDateTimeConverter.stringToDateTime(Ljava/lang/String;)Lorg/joda/time/DateTime;
  areturn
.end method

.method public static fromString(Ljava/lang/String;)Ljava/util/UUID;
  .limit locals 1
  .limit stack 2

  aload_0
  invokestatic hr/element/pgscala/converters/PGNullableUUIDConverter.stringToUUID(Ljava/lang/String;)Ljava/util/UUID;
  areturn
.end method
