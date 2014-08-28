package org.pgscala
package builder
package converters

trait PGConverterBuilderLike
    extends PGConverterHelper {

  def imports: String        // Scala imports

  def scalaClazz: String     // Fully qualified scala class
  def javaClazz: String      // Fully qualified java class
  def scalaType: String      // Class name, used with imports

  def scalaUpperType: String // Descriptive class name
  def javaUpperType: String  // Descriptive class name

  def scalaVar: String       // Abbreviation of the class name for a variable
  def optionScalaVar: String // Abbreviation for the optional variable name

  def to: String             // Converter code from current type to String
  def from: String           // Converter code from String to current type

  def defaultValue: String

  protected def filters = Seq(
    i("imports"            , imports            )
  , i("javaClazz"          , javaClazz          )
  , i("scalaClazz"         , scalaClazz         )
  , i("scalaType"          , scalaType          )
  , i("scalaUpperType"     , scalaUpperType     )
  , i("javaUpperType"      , javaUpperType      )
  , i("scalaVar"           , scalaVar           )
  , i("optionScalaVar"     , optionScalaVar     )
  , i("to"                 , to                 )
  , i("from"               , from               )
  , i("builder"            , builder            )
  , i("defaultValue"       , defaultValue       )
  )
}

trait PGConverterBuilder extends PGConverterBuilderLike {
  override def javaClazz =
    scalaClazz

  override def scalaType =
    scalaClazz.replaceFirst("^.*\\.", "")

  override def javaUpperType =
    javaClazz.replaceFirst("^.*\\.", "")

  override def scalaUpperType =
    scalaType

  override def scalaVar =
    if (isUp) scalaUpperType.toLowerCase else {
      l(scalaUpperType.replaceAll("[a-z]", ""))
    }

  override def optionScalaVar =
    "o" + scalaVar.head.toUpper + scalaVar.tail

  def isUp =
    scalaUpperType.toUpperCase == scalaUpperType

  def imports = """import %s;
""" format javaClazz

  def to = """ =
    PGNullableConverter.toPGString(%s)""" format(scalaVar)

  override def from = """
    PGNullableConverter.fromPGString(%s)""" .format(scalaVar)
}

trait PGPredefConverterBuilder extends PGConverterBuilder {
  override def imports = ""

  override def to = """ =
    PGNullableConverter.toPGString(%s valueOf %s)""" format(javaClazz, scalaVar)

  override def from = """
    PGNullableConverter.fromPGString(%s)""" .format(scalaVar)
}

import scalax.file._
import scalax.io._
import Codec.UTF8

object PGConverterBuilder extends PGConverterBuilderPaths {
  val converters = Seq(
    PGStringConverterBuilder

  , PGBooleanConverterBuilder
  , PGShortConverterBuilder
  , PGIntConverterBuilder
  , PGLongConverterBuilder

  , PGFloatConverterBuilder
  , PGDoubleConverterBuilder

  , PGBigDecimalConverterBuilder
  , PGBigIntConverterBuilder

  , PGLocalDateConverterBuilder
  , PGDateTimeConverterBuilder

  , PGByteArrayConverterBuilder
  , PGUUIDConverterBuilder

  , PGElemConverterBuilder
  , PGMapConverterBuilder
  )

  def buildScalaConverters() {
    val template =
      Resource.fromClasspath("PGConverter.scala.template")
        .string(UTF8)

    for (c <- converters) {
      val path = getPath(Scala) / "core" /
        ("PG%sConverter.scala" format c.scalaUpperType)

      println("Generated: " + path.toAbsolute.path)
      path.write(c.inject(template))(UTF8)
    }
  }

  def buildScalaOptionConverters() {
    val template =
      Resource.fromClasspath("PGOptionConverter.scala.template")
        .string(UTF8)

    for (c <- converters) {
      val path = getPath(Scala) / "option" /
        ("PGOption%sConverter.scala" format c.scalaUpperType)

      println("Generated: " + path.toAbsolute.path)
      path.write(c.inject(template))(UTF8)
    }
  }

  def buildScalaConverterImplicits() {
    val template =
      Resource.fromClasspath("Implicits.scala.template")
        .string(UTF8)

    val path = getPath(Scala) /
      "Implicits.scala"

    val padding =
      converters.map(_.scalaUpperType.length).max

    val sCI =
      converters.map{c =>
        "  implicit val implicitPG%sConverter %s= PG%1$sConverter" format(
          c.scalaUpperType,
          " " * (padding - c.scalaUpperType.length)
        )
      } mkString "\n"

    val sOCI =
      converters.map{c =>
        "  implicit val implicitPGOption%sConverter %s= PGOption%1$sConverter" format(
          c.scalaUpperType,
          " " * (padding - c.scalaUpperType.length)
        )
      } mkString "\n"

    val body = template
      .replace("{ scalaConverterImplicits }", sCI)
      .replace("{ scalaOptionConverterImplicits }", sOCI)

    println("Generated: " + path.toAbsolute.path)
    path.write(body)(UTF8)
  }
}
