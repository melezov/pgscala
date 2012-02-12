package hr.element.pgscala.converters

import org.joda.time._
import org.joda.time.format._

object PGDateTimeConverter extends PGTypeConverter[DateTime] {
  private val PGTimestamptzFormat =
    DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSSZZ")

  def toPGString(dT: DateTime): String =
    PGTimestamptzFormat.print(dT)

  def fromPGString(value: String): DateTime =
    PGTimestamptzFormat.parseDateTime(value)

  override val PGType = java.sql.Types.TIMESTAMP
}
