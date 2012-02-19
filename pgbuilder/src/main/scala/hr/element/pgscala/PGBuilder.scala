package hr.element.pgscala

trait Builder {
  def imports: String = ""
  def pre: String = ""

  def pgType: String
  def clazz: String

  def to: String
  def from: String

  def javaType =
    clazz.replaceFirst(".*?([^.]+)$", "$1")

  def fileName = javaType

  def javaVar = {
    if (fileName.toUpperCase == fileName) {
      fileName.toLowerCase
    }
    else {
      val v = fileName.replaceAll("[a-z]", "")
      v.head.toLower + v.tail
    }
  }

  def javaTypeLower =
    if (fileName.toUpperCase == fileName) {
      fileName.toLowerCase
    }
    else {
      fileName.head.toLower + fileName.tail
    }

  def jasminClass =
    "L%s;" format clazz.replace('.', '/')

  def javaImports =
    (if (clazz.contains('.')) {
"""import %s;
""" format(clazz)
    } else "") + imports

  def l(text: String) =
    text.head.toLower + text.tail

  def i(key: String, value: String) =
    (_: String).replace("{ "+ key +" }", value)

  def filters = Seq(
    i("pre",            pre)
  , i("javaImports",    javaImports)
  , i("pgType",         pgType)
  , i("javaType",       javaType)
  , i("jasminClass",    jasminClass)
  , i("fileName",       fileName)
  , i("javaVar",        javaVar)
  , i("javaTypeLower",  javaTypeLower)
  , i("to",             to)
  , i("from",           from)
  )

  def inject(body: String) = (
    (filters :\ body){ _(_) }
      split("\n{3,}")
      mkString "\n\n"
  )
}

import scalax.file._
import scalax.io._
import Codec.UTF8

import builders._

object PGBuilder extends App {
  val nullableConverters = Seq(
    StringBuilder

  , BooleanBuilder
  , ShortBuilder
  , IntegerBuilder
  , LongBuilder

  , FloatBuilder
  , DoubleBuilder

  , BigDecimalBuilder
  , BigIntegerBuilder

  , ByteArrayBuilder

  , LocalDateBuilder
  , DateTimeBuilder

  , UUIDBuilder
  )

  def buildJavaConverters() {
    val nullableTemplate =
      Resource.fromClasspath("PGNullableConverter.java")
        .slurpString(UTF8)

    for (c <- nullableConverters) {
      val path = Path("pgjava-converters") /
        "src" / "main" / "java" /
        "hr" / "element" / "pgscala" / "converters" /
        ("PGNullable%sConverter.java" format c.fileName)

      path.write(c.inject(nullableTemplate))(UTF8)
    }
  }

  def buildJasminProxies() {
    val nullableTemplate =
      Resource.fromClasspath("PGNullableConverter.j")
        .slurpString(UTF8)

    val sB = new StringBuilder

    for (c <- nullableConverters) {
      val templateBody =
        if (c ne nullableConverters.head) {
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

  buildJasminProxies()
}
