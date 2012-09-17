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
    i("imports"          , imports          )
  , i("pgType"           , pgType           )
  , i("clazz"            , clazz            )
  , i("javaType"         , javaType         )
  , i("jasminType"       , jasminType       )
  , i("upperType"        , upperType        )
  , i("lowerType"        , lowerType        )
  , i("javaVar"          , javaVar          )
  , i("body"             , body             )
  , i("to"               , to               )
  , i("from"             , from             )
  , i("builder"          , builder          )
  )
}

trait PGNullableConverterBuilder extends PGNullableConverterBuilderLike {
  override def imports =
    "import %s;" format clazz

  override def javaType =
    clazz.replaceFirst("^.*\\.", "")

  override def jasminType =
    "L%s;" format clazz.replace('.', '/')

  override def upperType =
    javaType

  override def lowerType =
    if (isUp) upperType.toLowerCase else l(upperType)

  override def javaVar =
    if (isUp) upperType.toLowerCase else {
      l(upperType.replaceAll("[a-z]", ""))
    }

  override val body = ""

  def isUp =
    upperType.toUpperCase == upperType

  override def language = Java: Language
}

trait PGPredefNullableConverterBuilder extends PGNullableConverterBuilder {
  override val imports = ""
}

import scalax.file._
import scalax.io._
import Codec.UTF8

object PGNullableConverterBuilder extends PGConverterBuilderPaths {
  val converters = Seq(
   PGNullableStringConverterBuilder

  ,PGNullableBooleanConverterBuilder
  ,PGNullableShortConverterBuilder
  ,PGNullableIntegerConverterBuilder
  ,PGNullableLongConverterBuilder

  ,PGNullableFloatConverterBuilder
  ,PGNullableDoubleConverterBuilder

  ,PGNullableBigDecimalConverterBuilder
  ,PGNullableBigIntegerConverterBuilder

  ,PGNullableLocalDateConverterBuilder
  ,PGNullableDateTimeConverterBuilder

  ,PGNullableByteArrayConverterBuilder
  ,PGNullableUUIDConverterBuilder

  ,PGNullableElemConverterBuilder
  ,PGNullableMapConverterBuilder
  )

  def buildJavaNullableConverters() {
    val nullableTemplate =
      Resource.fromClasspath("PGNullableConverter.java")
        .slurpString(UTF8)

    for (c <- converters) {
      val path = getPath(c.language, Java) /
        ("PGNullable%sConverter.java" format c.upperType)

      println("Generated: " + path.toAbsolute.path)
      path.write(c.inject(nullableTemplate))(UTF8)
    }
  }

  def buildJasminProxy() {
    val nullableTemplate =
      Resource.fromClasspath("PGNullableConverter.j")
        .slurpString(UTF8)

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

    val path = getPath(Scala, Jasmin) /
        "PGNullableConverter.j"

    println("Generated: " + path.toAbsolute.path)
    path.write(sB.toString)(UTF8)
  }
}
