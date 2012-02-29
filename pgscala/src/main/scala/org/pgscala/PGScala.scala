package org.pgscala

import util._
import converters._

import java.sql.{ Statement, ResultSet}

object PGScala {
}

trait PGScala {
  def arr[T, P1](query: String, p1: P1)(f: PGScalaResultSet => T)(implicit c1: PGConverter[P1]): IndexedSeq[T]
  def arr[T, P1, P2, P3, P4, P5]
      (query: String, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5)
      (f: PGScalaResultSet => T)
      (implicit c1: PGConverter[P1], c2: PGConverter[P2], c3: PGConverter[P3],
                c4: PGConverter[P4], c5: PGConverter[P5]): IndexedSeq[T]
}

class PGScalaViaPreparedStatements(con: java.sql.Connection) {
  def arr[T, P1](query: String, p1: P1)(f: PGScalaResultSet => T)(implicit c1: PGConverter[P1]): IndexedSeq[T] = {

    val body = query
      .replaceFirst("\\$1", "?::" + c1.PGType)
      .replaceAll("\\$1", "\\$1::" + c1.PGType)

    val pS = con prepareStatement(body)

    println(body)

    try {
      val sp1 =

      pS.setString(1, c1 toPGString p1)

      val rS = pS.executeQuery()

      try {
        new PGScalaResultSet(rS) map f toIndexedSeq
      }
      finally {
        rS.close()
      }
    }
    finally {
      pS.close();
    }
  }
}

class PGScalaViaLiterals(con: java.sql.Connection) {
  def arr[T, P1](query: String, p1: P1)(f: PGScalaResultSet => T)(implicit pc1: PGConverter[P1]): IndexedSeq[T] = {
    val s = con createStatement()

    try {
      val sp1 = PGLiteral.quote(pc1.toPGString(p1)) + "::" + pc1.PGType

      val body = query.replace("$1", sp1)

      println(body)

      val rS = s.executeQuery(body)

      try {
        new PGScalaResultSet(rS) map f toIndexedSeq
      }
      finally {
        rS.close()
      }
    }
    finally {
      s.close();
    }
  }
}
