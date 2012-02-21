package hr.element.pgscala
package builder

trait SConverterBuilderLike extends BuilderLike {
  def imports: String        // Scala imports

  def scalaClazz: String     // Fully qualified scala class
  def javaClazz: String      // Fully qualified java class
  def scalaType: String      // Class name, used with imports

  def scalaUpperType: String // Descriptive class name
  def javaUpperType: String  // Descriptive class name
  def scalaVar: String       // Abbreviation of the class name for a variable

  def to: String             // Converter code from current type to String
  def from: String           // Converter code from String to current type

  def filters = Seq(
    i("imports",        imports)
  , i("javaClazz",      javaClazz)
  , i("scalaClazz",     scalaClazz)
  , i("scalaType",      scalaType)
  , i("scalaUpperType", scalaUpperType)
  , i("javaUpperType",  javaUpperType)
  , i("scalaVar",       scalaVar)
  , i("to",             to)
  , i("from",           from)
  )
}

trait SConverterBuilder extends SConverterBuilderLike {
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

  def isUp =
    scalaUpperType.toUpperCase == scalaUpperType

  def imports = "import %s;" format javaClazz

  def to = """ =
    PGNullableConverter.toPGString(%s)""" format(scalaVar)

  override def from = """ =
    PGNullableConverter.fromPGString(%s)""" .format(scalaVar)
}

trait SPredefConverterBuilder extends SConverterBuilder {
  override def imports = ""

  override def to = """ =
    PGNullableConverter.toPGString(%s valueOf %s)""" format(javaClazz, scalaVar)

  override def from = """ =
    PGNullableConverter.fromPGString(%s)""" .format(scalaVar)
}

import scalax.file._
import scalax.io._
import Codec.UTF8

object SConverterBuilder {
  val converters = Seq(
    StringConverterBuilder

  , BooleanConverterBuilder
  , ShortConverterBuilder
  , IntConverterBuilder
  , LongConverterBuilder

  , FloatConverterBuilder
  , DoubleConverterBuilder

  , BigDecimalConverterBuilder
  , BigIntConverterBuilder

  , LocalDateConverterBuilder
  , DateTimeConverterBuilder

  , ByteArrayConverterBuilder
  , UUIDConverterBuilder

  , ElemConverterBuilder
  )

  def buildScalaConverters() {
    val template =
      Resource.fromClasspath("PGConverter.scala")
        .slurpString(UTF8)

    for (c <- converters) {
      val path = Path("pgscala-converters") /
        "src" / "main" / "scala" /
        "hr" / "element" / "pgscala" / "converters" /
        ("PG%sConverter.scala" format c.scalaUpperType)

      path.write(c.inject(template))(UTF8)
    }
  }
}