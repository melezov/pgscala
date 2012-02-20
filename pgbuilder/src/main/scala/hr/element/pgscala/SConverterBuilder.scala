package hr.element.pgscala
package builder

trait SConverterBuilderLike extends BuilderLike {
  def imports: String       // Scala imports

  def clazz: String         // Fully qualified java class
  def scalaType: String     // Class name, used with imports

  def upperType: String     // Descriptive class name
  def scalaVar: String      // Abbreviation of the class name for a variable

  def to: String            // Converter code from current type to String
  def from: String          // Converter code from String to current type
  
  def filters = Seq(
    i("imports",    imports)
  , i("clazz",      clazz)
  , i("scalaType",  scalaType)
  , i("upperType",  upperType)
  , i("scalaVar",   scalaVar)
  , i("to",         to)
  , i("from",       from)
  )
}

trait SConverterBuilder extends SConverterBuilderLike {
  override def scalaType = 
    clazz.replaceFirst("^.*\\.", "")

  override def upperType = 
    scalaType
    
  override def scalaVar =
    if (isUp) upperType.toLowerCase else {
      l(upperType.replaceAll("[a-z]", ""))
    }
    
  override val to = ""
  override val from = ""

  def isUp = 
    upperType.toUpperCase == upperType
}

trait SPredefConverterBuilder extends SConverterBuilder {
  override val imports = ""
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
/*
  , BigDecimalConverterBuilder
  , BigIntConverterBuilder

  , LocalDateConverterBuilder
  , DateTimeConverterBuilder

  , ByteArrayConverterBuilder
  , UUIDConverterBuilder
*/  )

  def buildScalaConverters() {
    val template =
      Resource.fromClasspath("PGConverter.scala")
        .slurpString(UTF8)

    for (c <- converters) {
      val path = Path("pgscala-converters") /
        "src" / "main" / "scala" /
        "hr" / "element" / "pgscala" / "converters" /
        ("PG%sConverter.scala" format c.upperType)

      path.write(c.inject(template))(UTF8)
    }
  }
}