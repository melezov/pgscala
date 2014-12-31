package org.pgscala
package builder
package converters

trait PGNullableConverterBuilderLike extends PGConverterHelper {
  def imports: String           // Java imports
  def pgType: String            // PostgreSQL type

  def clazz: String             // Fully qualified java class
  def javaType: String          // Class name, used with imports
  def jasminType: String        // JVM name (Ljava/lang/String;)

  def upperType: String         // Descriptive class name
  def lowerType: String         // Same as above, but first char lowercase
  def javaVar: String           // Abbreviation of the class name for a variable

  def body: String              // Additional functionality not contained elsewhere
  def to: String                // Converter code from current type to String
  def from: String              // Converter code from String to current type

  def language: Language        // Should the source be build in java or scala project

  protected def filters = Seq(
    i("imports"            , imports            )
  , i("pgType"             , pgType             )
  , i("clazz"              , clazz              )
  , i("javaType"           , javaType           )
  , i("jasminType"         , jasminType         )
  , i("upperType"          , upperType          )
  , i("lowerType"          , lowerType          )
  , i("javaVar"            , javaVar            )
  , i("body"               , body               )
  , i("to"                 , to                 )
  , i("from"               , from               )
  , i("builder"            , builder            )
  , i("jasminToSignature"  , jasminToSignature  )
  , i("jasminFromSignature", jasminFromSignature)
  , i("toAnnotation"       , toAnnotation       )
  , i("fromAnnotation"     , fromAnnotation     )
  , i("toThrows"           , toThrows           )
  , i("fromThrows"         , fromThrows         )
  , addToExceptionWrapper
  , addFromExceptionWrapper
  )

  def jasminToSignature: String
  def jasminFromSignature: String

  def toThrows: String
  def fromThrows: String

  def toAnnotation = "@ToString"
  def fromAnnotation = "@FromString"

  def addToExceptionWrapper = identity[String] _
  def addFromExceptionWrapper = identity[String] _
}

trait PGNullableConverterBuilder extends PGNullableConverterBuilderLike {
  def imports = ""

  override def javaType =
    clazz.replaceFirst("^.*\\.", "")

  override def jasminType =
    "L%s;" format clazz.replace('.', '/')

  override def upperType =
    javaType

  override def lowerType =
    lu(upperType)

  override def javaVar =
    upperType.replaceAll("[a-z]", "").toLowerCase

  override val body = ""

  override def language = Language.Java: Language

  def toThrowsExceptions = Seq.empty[String]
  def fromThrowsExceptions = Seq.empty[String]

  private def addThrows(exceptions: Seq[String]) =
    if (exceptions.isEmpty) "" else exceptions.mkString("throws ", ", ", " ")

  override def toThrows = addThrows(toThrowsExceptions)
  override def fromThrows = addThrows(fromThrowsExceptions)

  override def addToExceptionWrapper = (body: String) =>
    if (toThrowsExceptions.isEmpty) body else {
      body.replaceFirst(
          """(public String convertToString.*?)\n( +return.*?;)""",
          """$1
        try {
    $2
        } catch (final """ + toThrowsExceptions.head + s""" e) {
            throw new RuntimeException("Could not convert ${javaType} to String");
        }""")
    }

  override def addFromExceptionWrapper = (body: String) =>
    if (fromThrowsExceptions.isEmpty) body else {
      body.replaceFirst(
          """(public .*? convertFromString.*?)\n( +return.*?;)""",
          """$1
        try {
    $2
        } catch (final """ + fromThrowsExceptions.head + s""" e) {
            throw new RuntimeException("Could not convert String to ${javaType}");
        }""")
    }

  override def jasminToSignature = ""
  override def jasminFromSignature = ""
}

trait PGPredefNullableConverterBuilder extends PGNullableConverterBuilder

import scalax.file._
import scalax.io._
import Codec.UTF8

object PGNullableConverterBuilder extends PGConverterBuilderPaths {
  val converters = Seq(
    PGNullableStringConverterBuilder

  , PGNullableBooleanConverterBuilder
  , PGNullableShortConverterBuilder
  , PGNullableIntegerConverterBuilder
  , PGNullableLongConverterBuilder

  , PGNullableFloatConverterBuilder
  , PGNullableDoubleConverterBuilder

  , PGNullableBigDecimalConverterBuilder
  , PGNullableBigIntegerConverterBuilder

  , PGNullableLocalDateConverterBuilder
  , PGNullableDateTimeConverterBuilder

  , PGNullableByteArrayConverterBuilder
  , PGNullableBufferedImageConverterBuilder

  , PGNullableURLConverterBuilder
  , PGNullableUUIDConverterBuilder

  , PGNullableElemConverterBuilder
  , PGNullableMapConverterBuilder
  , PGNullableLocationConverterBuilder
  , PGNullableLocationDoubleConverterBuilder
  , PGNullableLocationFloatConverterBuilder
  , PGNullablePointConverterBuilder
  )

  def buildJavaNullableConverters() {
    val nullableTemplate =
      Resource.fromClasspath("PGNullableConverter.java.template")
        .string(UTF8)

    for (c <- converters) {
      val path = getPath(c.language, Language.Java) /
        ("PGNullable%sConverter.java" format c.upperType)

      println("Generated: " + path.toAbsolute.path)
      path.write(c.inject(nullableTemplate))(UTF8)
    }
  }

  def buildJasminProxy() {
    val nullableTemplate =
      Resource.fromClasspath("PGNullableConverter.j.template")
        .string(UTF8)

    val sB = new StringBuilder

    for (c <- converters) {
      val lines =
        nullableTemplate.split('\n')

      val templateBody =
        (if (c eq converters.head) {
          lines.take(4)
        }
        else {
          lines.drop(4)
        }).mkString("\n")

      sB ++= c.inject(templateBody) += '\n'
    }

    val path = getPath(Language.Scala, Language.Jasmin) /
        "PGNullableConverter.j"

    println("Generated: " + path.toAbsolute.path)
    path.write(sB.toString)(UTF8)
  }
}
