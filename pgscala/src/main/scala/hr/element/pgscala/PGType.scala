package hr.element.pgscala

import org.joda.time.{LocalDate, DateTime}
import java.sql.Types

object PG extends Enumeration{
  val Null        = Value("null",                     "null",        Types.NULL,      "Null"       )
  val Bool        = Value("boolean",                  "bool",        Types.BIT,       "Boolean"    )
  val Int2        = Value("smallint",                 "int2",        Types.SMALLINT,  "Short"      )
  val Int4        = Value("integer",                  "int4",        Types.INTEGER,   "Int"        )
  val Int8        = Value("bigint",                   "int8",        Types.BIGINT,    "Long"       )
  val Float4      = Value("real",                     "float4",      Types.FLOAT,     "Float"      )
  val Float8      = Value("double precision",         "float8",      Types.DOUBLE,    "Double"     )
  val Text        = Value("text",                     "text",        Types.VARCHAR,   "String"     )
  val Numeric     = Value("numeric",                  "numeric",     Types.NUMERIC,   "BigInt"     )
  val Bytea       = Value("bytea",                    "bytea",       Types.BINARY,    "Array[Byte]")
  val Date        = Value("date",                     "date",        Types.DATE,      "LocalDate"  )
  val Timestamptz = Value("timestamp with time zone", "timestamptz", Types.TIMESTAMP, "DateTime"   )

  case class PGType(name: String, enum: String, tipe: Int, clazz: String) extends Val(enum)

  protected def Value(name: String, enum: String, tipe: Int, clazz: String): PGType =
    new PGType(name, enum, tipe, clazz)

  def find(full: String): Option[PGType] =
    values.find(_.asInstanceOf[PGType].name==full).map(_.asInstanceOf[PGType])
}
