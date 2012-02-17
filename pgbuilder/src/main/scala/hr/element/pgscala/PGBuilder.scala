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

  val nullableTemplate =
    Resource.fromClasspath("PGNullableConverter.java")
      .slurpString(UTF8)

  val sB = new StringBuilder

  sB ++=
""".class public hr.element.pgscala.converters.PGNullableConverter
.super java/lang/Object
"""

  for (c <- nullableConverters) {
    val path = Path("pgjava-converters") /
      "src" / "main" / "java" /
      "hr" / "element" / "pgscala" / "converters" /
      ("PGNullable%sConverter.java" format c.fileName)

    path.write(c.inject(nullableTemplate))(UTF8)

    val body = """
.method public static fromString(Ljava/lang/String;){ jasminClass }
  .limit locals 1
  .limit stack 2

  aload_0
  invokestatic hr/element/pgscala/converters/PGNullable{ fileName }Converter.stringTo{ fileName }(Ljava/lang/String;){ jasminClass }
  areturn
.end method
"""

    sB ++= (c.filters :\ body)( _(_) )
  }

  val jasminPath = Path("pgscala-converters") /
      "src" / "main" / "jasmin" /
      "hr" / "element" / "pgscala" / "converters" /
      "PGNullableConverter.j"

  jasminPath.write(sB.toString)(UTF8)
}
