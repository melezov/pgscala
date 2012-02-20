package hr.element.pgscala
package builder

trait JConverterBuilderLike {
  def imports: String       // Java imports
  def pgType: String        // PostgreSQL type

  def clazz: String         // Fully qualified java class
  def javaType: String      // Class name, used with imports
  def jasminType: String    // JVM name (Ljava/lang/String;)

  def upperType: String     // Descriptive class name
  def lowerType: String     // Same as above, but first char lowercase
  def javaVar: String       // Abbreviation of the class name for a variable

  def body: String          // Additional functionality not contained elsewhere
  def to: String            // Converter code from current type to String
  def from: String          // Converter code from String to current type

  def i(key: String, value: String) =
    (_: String).replace("{ "+ key +" }", value)

  def filters = Seq(
    i("imports",    imports)
  , i("pgType",     pgType)
  , i("clazz",      clazz)
  , i("javaType",   javaType)
  , i("jasminType", jasminType)
  , i("upperType",  upperType)
  , i("lowerType",  lowerType)
  , i("javaVar",    javaVar)
  , i("body",       body)
  , i("to",         to)
  , i("from",     from)
  )

  def inject(body: String) = (
    (filters :\ body)( _(_) )  // (-_(-_-)_-) The hood is watching
      split("\n{3,}")
      mkString "\n\n"
  )
}

trait JConverterBuilder extends JConverterBuilderLike {
  override def imports = 
    "import %s;" format clazz

  override def javaType =
    clazz.replaceFirst("^.*\\.", "")

  override def jasminType =
    "L%s;" format clazz.replace('.', '/')

  override def upperType =
    javaType

  override def lowerType =
    lowerize(upperType)

  override def javaVar = {
    if (upperType.toUpperCase == upperType) {
      upperType.toLowerCase
    }
    else {
      lowerize(upperType.replaceAll("[a-z]", ""))
    }
  }

  override def body = ""

  def lowerize(word: String) =
    word.head.toLower + word.tail
}

trait JPredefConverterBuilder extends JConverterBuilder {
  override val imports = ""
}

import scalax.file._
import scalax.io._
import Codec.UTF8

object JConverterBuilder {
  val converters = Seq(
    JStringConverterBuilder
  , JBooleanConverterBuilder
  , JShortConverterBuilder
  , JIntegerConverterBuilder
  , JLongConverterBuilder

  , JFloatConverterBuilder
  , JDoubleConverterBuilder

  , JBigDecimalConverterBuilder
  , JBigIntegerConverterBuilder

  , JLocalDateConverterBuilder
  , JDateTimeConverterBuilder

  , JByteArrayConverterBuilder
  , JUUIDConverterBuilder
  )

  def buildJavaConverters() {
    val nullableTemplate =
      Resource.fromClasspath("PGNullableConverter.java")
        .slurpString(UTF8)

    for (c <- converters) {
      val path = Path("pgjava-converters") /
        "src" / "main" / "java" /
        "hr" / "element" / "pgscala" / "converters" /
        ("PGNullable%sConverter.java" format c.upperType)

      path.write(c.inject(nullableTemplate))(UTF8)
    }
  }
}

/*

import builders._

object PGBuilder extends App {

  def buildJasminProxies() {
    val nullableTemplate =
      Resource.fromClasspath("PGNullableConverter.j")
        .slurpString(UTF8)

    val sB = new StringBuilder

    for (c <- converters) {
      val templateBody =
        if (c ne converters.head) {
          nullableTemplate.split('\n').drop(2).mkString("\n")
        }
        else nullableTemplate

        sB ++= c.inject(templateBody)
    }

    val path = Path("pgscala-converters") /
      "src" / "main" / "jasmin" /
      "hr" / "element" / "pgscala" / "converters" /
      "PGNullableConverter.j"

    path.write(sB.toString)(UTF8)
  }

  def buildScalaConverters() {
    val baseTemplate =
      Resource.fromClasspath("PGConverter.scala")
        .slurpString(UTF8)

    for (c <- converters) {
      val path = Path("pgscala-converters") /
        "src" / "main" / "scala" /
        "hr" / "element" / "pgscala" / "converters" /
        ("PG%sConverter.scala" format c.fileName)

      path.write(c.inject(baseTemplate))(UTF8)
    }
  }

  buildJavaConverters()
  buildJasminProxies()
  buildScalaConverters()
}
*/