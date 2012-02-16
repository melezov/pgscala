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

  def javaVar =
    l(javaType.replaceAll("[a-z]", ""))

  def javaTypeLower =
    l(javaType)

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
  , i("javaVar",        javaVar)
  , i("javaTypeLower",  javaTypeLower)
  , i("to",             to)
  , i("from",           from)
  )

  def inject(body: String) =
    (filters :\ body){ _(_) }
}

import scalax.file._
import scalax.io._
import Codec.UTF8

import builders._

object PGBuilder extends App {
  val nullableConverters = Seq(
    DateTimeBuilder,
    BooleanBuilder
  )

  val nullableTemplate =
    Resource.fromClasspath("PGNullableConverter.java")
      .slurpString(UTF8)

  for (c <- nullableConverters) {
    Path("PGNullable%sConverter.java" format c.javaType)
      .write(c.inject(nullableTemplate))(UTF8)
  }
}