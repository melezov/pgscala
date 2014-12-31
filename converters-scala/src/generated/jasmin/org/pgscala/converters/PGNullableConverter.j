.class public org.pgscala.converters.PGNullableConverter
.super java/lang/Object

; Do not edit - generated in Builder / JConverterBuilder.scala

.method public static fromPGString(Ljava/lang/String;)Ljava/lang/Boolean;
  .limit locals 1
  .limit stack 2
  aload_0
  invokestatic org/pgscala/converters/PGNullableBooleanConverter.stringToBoolean(Ljava/lang/String;)Ljava/lang/Boolean;
  areturn
.end method

.method public static toPGString(Ljava/lang/Boolean;)Ljava/lang/String;
  .limit locals 1
  .limit stack 2
  aload_0
  invokestatic org/pgscala/converters/PGNullableBooleanConverter.booleanToString(Ljava/lang/Boolean;)Ljava/lang/String;
  areturn
.end method

.method public static fromPGString(Ljava/lang/String;)Ljava/lang/Short;
  .limit locals 1
  .limit stack 2
  aload_0
  invokestatic org/pgscala/converters/PGNullableShortConverter.stringToShort(Ljava/lang/String;)Ljava/lang/Short;
  areturn
.end method

.method public static toPGString(Ljava/lang/Short;)Ljava/lang/String;
  .limit locals 1
  .limit stack 2
  aload_0
  invokestatic org/pgscala/converters/PGNullableShortConverter.shortToString(Ljava/lang/Short;)Ljava/lang/String;
  areturn
.end method

.method public static fromPGString(Ljava/lang/String;)Ljava/lang/Integer;
  .limit locals 1
  .limit stack 2
  aload_0
  invokestatic org/pgscala/converters/PGNullableIntegerConverter.stringToInteger(Ljava/lang/String;)Ljava/lang/Integer;
  areturn
.end method

.method public static toPGString(Ljava/lang/Integer;)Ljava/lang/String;
  .limit locals 1
  .limit stack 2
  aload_0
  invokestatic org/pgscala/converters/PGNullableIntegerConverter.integerToString(Ljava/lang/Integer;)Ljava/lang/String;
  areturn
.end method

.method public static fromPGString(Ljava/lang/String;)Ljava/lang/Long;
  .limit locals 1
  .limit stack 2
  aload_0
  invokestatic org/pgscala/converters/PGNullableLongConverter.stringToLong(Ljava/lang/String;)Ljava/lang/Long;
  areturn
.end method

.method public static toPGString(Ljava/lang/Long;)Ljava/lang/String;
  .limit locals 1
  .limit stack 2
  aload_0
  invokestatic org/pgscala/converters/PGNullableLongConverter.longToString(Ljava/lang/Long;)Ljava/lang/String;
  areturn
.end method

.method public static fromPGString(Ljava/lang/String;)Ljava/lang/Float;
  .limit locals 1
  .limit stack 2
  aload_0
  invokestatic org/pgscala/converters/PGNullableFloatConverter.stringToFloat(Ljava/lang/String;)Ljava/lang/Float;
  areturn
.end method

.method public static toPGString(Ljava/lang/Float;)Ljava/lang/String;
  .limit locals 1
  .limit stack 2
  aload_0
  invokestatic org/pgscala/converters/PGNullableFloatConverter.floatToString(Ljava/lang/Float;)Ljava/lang/String;
  areturn
.end method

.method public static fromPGString(Ljava/lang/String;)Ljava/lang/Double;
  .limit locals 1
  .limit stack 2
  aload_0
  invokestatic org/pgscala/converters/PGNullableDoubleConverter.stringToDouble(Ljava/lang/String;)Ljava/lang/Double;
  areturn
.end method

.method public static toPGString(Ljava/lang/Double;)Ljava/lang/String;
  .limit locals 1
  .limit stack 2
  aload_0
  invokestatic org/pgscala/converters/PGNullableDoubleConverter.doubleToString(Ljava/lang/Double;)Ljava/lang/String;
  areturn
.end method

.method public static fromPGString(Ljava/lang/String;)Ljava/math/BigDecimal;
  .limit locals 1
  .limit stack 2
  aload_0
  invokestatic org/pgscala/converters/PGNullableBigDecimalConverter.stringToBigDecimal(Ljava/lang/String;)Ljava/math/BigDecimal;
  areturn
.end method

.method public static toPGString(Ljava/math/BigDecimal;)Ljava/lang/String;
  .limit locals 1
  .limit stack 2
  aload_0
  invokestatic org/pgscala/converters/PGNullableBigDecimalConverter.bigDecimalToString(Ljava/math/BigDecimal;)Ljava/lang/String;
  areturn
.end method

.method public static fromPGString(Ljava/lang/String;)Ljava/math/BigInteger;
  .limit locals 1
  .limit stack 2
  aload_0
  invokestatic org/pgscala/converters/PGNullableBigIntegerConverter.stringToBigInteger(Ljava/lang/String;)Ljava/math/BigInteger;
  areturn
.end method

.method public static toPGString(Ljava/math/BigInteger;)Ljava/lang/String;
  .limit locals 1
  .limit stack 2
  aload_0
  invokestatic org/pgscala/converters/PGNullableBigIntegerConverter.bigIntegerToString(Ljava/math/BigInteger;)Ljava/lang/String;
  areturn
.end method

.method public static fromPGString(Ljava/lang/String;)Lorg/joda/time/LocalDate;
  .limit locals 1
  .limit stack 2
  aload_0
  invokestatic org/pgscala/converters/PGNullableLocalDateConverter.stringToLocalDate(Ljava/lang/String;)Lorg/joda/time/LocalDate;
  areturn
.end method

.method public static toPGString(Lorg/joda/time/LocalDate;)Ljava/lang/String;
  .limit locals 1
  .limit stack 2
  aload_0
  invokestatic org/pgscala/converters/PGNullableLocalDateConverter.localDateToString(Lorg/joda/time/LocalDate;)Ljava/lang/String;
  areturn
.end method

.method public static fromPGString(Ljava/lang/String;)Lorg/joda/time/DateTime;
  .limit locals 1
  .limit stack 2
  aload_0
  invokestatic org/pgscala/converters/PGNullableDateTimeConverter.stringToDateTime(Ljava/lang/String;)Lorg/joda/time/DateTime;
  areturn
.end method

.method public static toPGString(Lorg/joda/time/DateTime;)Ljava/lang/String;
  .limit locals 1
  .limit stack 2
  aload_0
  invokestatic org/pgscala/converters/PGNullableDateTimeConverter.dateTimeToString(Lorg/joda/time/DateTime;)Ljava/lang/String;
  areturn
.end method

.method public static fromPGString(Ljava/lang/String;)[B
  .limit locals 1
  .limit stack 2
  aload_0
  invokestatic org/pgscala/converters/PGNullableByteArrayConverter.stringToByteArray(Ljava/lang/String;)[B
  areturn
.end method

.method public static toPGString([B)Ljava/lang/String;
  .limit locals 1
  .limit stack 2
  aload_0
  invokestatic org/pgscala/converters/PGNullableByteArrayConverter.byteArrayToString([B)Ljava/lang/String;
  areturn
.end method

.method public static fromPGString(Ljava/lang/String;)Ljava/awt/image/BufferedImage;
  .limit locals 1
  .limit stack 2
  aload_0
  invokestatic org/pgscala/converters/PGNullableBufferedImageConverter.stringToBufferedImage(Ljava/lang/String;)Ljava/awt/image/BufferedImage;
  areturn
.end method

.method public static toPGString(Ljava/awt/image/BufferedImage;)Ljava/lang/String;
  .limit locals 1
  .limit stack 2
  aload_0
  invokestatic org/pgscala/converters/PGNullableBufferedImageConverter.bufferedImageToString(Ljava/awt/image/BufferedImage;)Ljava/lang/String;
  areturn
.end method

.method public static fromPGString(Ljava/lang/String;)Ljava/net/URL;
  .limit locals 1
  .limit stack 2
  aload_0
  invokestatic org/pgscala/converters/PGNullableURLConverter.stringToURL(Ljava/lang/String;)Ljava/net/URL;
  areturn
.end method

.method public static toPGString(Ljava/net/URL;)Ljava/lang/String;
  .limit locals 1
  .limit stack 2
  aload_0
  invokestatic org/pgscala/converters/PGNullableURLConverter.urlToString(Ljava/net/URL;)Ljava/lang/String;
  areturn
.end method

.method public static fromPGString(Ljava/lang/String;)Ljava/util/UUID;
  .limit locals 1
  .limit stack 2
  aload_0
  invokestatic org/pgscala/converters/PGNullableUUIDConverter.stringToUUID(Ljava/lang/String;)Ljava/util/UUID;
  areturn
.end method

.method public static toPGString(Ljava/util/UUID;)Ljava/lang/String;
  .limit locals 1
  .limit stack 2
  aload_0
  invokestatic org/pgscala/converters/PGNullableUUIDConverter.uuidToString(Ljava/util/UUID;)Ljava/lang/String;
  areturn
.end method

.method public static fromPGString(Ljava/lang/String;)Lscala/xml/Elem;
  .limit locals 1
  .limit stack 2
  aload_0
  invokestatic org/pgscala/converters/PGNullableElemConverter.stringToElem(Ljava/lang/String;)Lscala/xml/Elem;
  areturn
.end method

.method public static toPGString(Lscala/xml/Elem;)Ljava/lang/String;
  .limit locals 1
  .limit stack 2
  aload_0
  invokestatic org/pgscala/converters/PGNullableElemConverter.elemToString(Lscala/xml/Elem;)Ljava/lang/String;
  areturn
.end method

.method public static fromPGString(Ljava/lang/String;)Lscala.collection.immutable.Map;
  .limit locals 1
  .limit stack 2
  .signature "(Ljava/lang/String;)Lscala.collection.immutable.Map<Ljava/lang/String;Ljava/lang/String;>;"
  aload_0
  invokestatic org/pgscala/converters/PGNullableMapConverter.stringToMap(Ljava/lang/String;)Lscala.collection.immutable.Map;
  areturn
.end method

.method public static toPGString(Lscala.collection.immutable.Map;)Ljava/lang/String;
  .limit locals 1
  .limit stack 2
  .signature "(Lscala.collection.immutable.Map<Ljava/lang/String;Ljava/lang/String;>;)Ljava/lang/String;"
  aload_0
  invokestatic org/pgscala/converters/PGNullableMapConverter.mapToString(Lscala.collection.immutable.Map;)Ljava/lang/String;
  areturn
.end method

.method public static fromPGString(Ljava/lang/String;)Ljava/awt/geom/Point2D;
  .limit locals 1
  .limit stack 2
  aload_0
  invokestatic org/pgscala/converters/PGNullableLocationConverter.stringToLocation(Ljava/lang/String;)Ljava/awt/geom/Point2D;
  areturn
.end method

.method public static toPGString(Ljava/awt/geom/Point2D;)Ljava/lang/String;
  .limit locals 1
  .limit stack 2
  aload_0
  invokestatic org/pgscala/converters/PGNullableLocationConverter.locationToString(Ljava/awt/geom/Point2D;)Ljava/lang/String;
  areturn
.end method

.method public static fromPGString(Ljava/lang/String;)Ljava/awt/geom/Point2D/Double;
  .limit locals 1
  .limit stack 2
  aload_0
  invokestatic org/pgscala/converters/PGNullableLocationDoubleConverter.stringToLocationDouble(Ljava/lang/String;)Ljava/awt/geom/Point2D/Double;
  areturn
.end method

.method public static toPGString(Ljava/awt/geom/Point2D/Double;)Ljava/lang/String;
  .limit locals 1
  .limit stack 2
  aload_0
  invokestatic org/pgscala/converters/PGNullableLocationDoubleConverter.locationDoubleToString(Ljava/awt/geom/Point2D/Double;)Ljava/lang/String;
  areturn
.end method

.method public static fromPGString(Ljava/lang/String;)Ljava/awt/geom/Point2D/Float;
  .limit locals 1
  .limit stack 2
  aload_0
  invokestatic org/pgscala/converters/PGNullableLocationFloatConverter.stringToLocationFloat(Ljava/lang/String;)Ljava/awt/geom/Point2D/Float;
  areturn
.end method

.method public static toPGString(Ljava/awt/geom/Point2D/Float;)Ljava/lang/String;
  .limit locals 1
  .limit stack 2
  aload_0
  invokestatic org/pgscala/converters/PGNullableLocationFloatConverter.locationFloatToString(Ljava/awt/geom/Point2D/Float;)Ljava/lang/String;
  areturn
.end method

.method public static fromPGString(Ljava/lang/String;)Ljava/awt/Point;
  .limit locals 1
  .limit stack 2
  aload_0
  invokestatic org/pgscala/converters/PGNullablePointConverter.stringToPoint(Ljava/lang/String;)Ljava/awt/Point;
  areturn
.end method

.method public static toPGString(Ljava/awt/Point;)Ljava/lang/String;
  .limit locals 1
  .limit stack 2
  aload_0
  invokestatic org/pgscala/converters/PGNullablePointConverter.pointToString(Ljava/awt/Point;)Ljava/lang/String;
  areturn
.end method
