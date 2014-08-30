package org.pgscala.converters

import java.util.UUID

/** Do not edit - generated in Builder / PGUUIDConverterBuilder.scala */

object PGOptionUUIDConverter extends PGConverter[Option[UUID]] {
  val PGType = PGUUIDConverter.PGType

  def toPGString(ouuid: Option[UUID]): String =
    ouuid match {
      case None =>
        null
      case Some(uuid) =>
        PGUUIDConverter.toPGString(uuid)
    }

  def fromPGString(uuid: String): Option[UUID] =
    uuid match {
      case null | "" =>
        None
      case ouuid =>
        Some(PGUUIDConverter.fromPGString(ouuid))
    }
}
