package org.pgscala

import util._
import converters._

import java.sql.{ Statement, ResultSet}

object PGScala {
}

/*
  def arr[T, P1](query: String, p1: P1)(f: PGScalaResultSet => T)(implicit c1: PGConverter[P1]): IndexedSeq[T]
  def arr[T, P1, P2, P3, P4, P5]
      (query: String, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5)
      (f: PGScalaResultSet => T)
      (implicit c1: PGConverter[P1], c2: PGConverter[P2], c3: PGConverter[P3],
                c4: PGConverter[P4], c5: PGConverter[P5]): IndexedSeq[T]
*/

class PGScala(con: java.sql.Connection) {
  import Parametrifier._

  private def arr[T](query: String, params: IndexedSeq[ParamText[_]])(f: PGScalaResultSet => T): IndexedSeq[T] = {
    val p = Parametrifier(query, params)

    if (p.preparedParams.isEmpty) {
      val st = con.createStatement()
      try {
        val rS = st.executeQuery(p.preparedQuery)
        try {
          new PGScalaResultSet(rS) map f toIndexedSeq
        }
        finally {
          rS.close()
        }
      }
      finally {
        st.close()
      }
    }
    else {
      val pS = con prepareStatement(p.preparedQuery)
      try {
        var index = 1
        for (p <- p.preparedParams) {
          pS.setString(index, p)
          index += 1
        }

        val rS = pS.executeQuery()
        try {
          new PGScalaResultSet(rS) map f toIndexedSeq
        }
        finally {
          rS.close()
        }
      }
      finally {
        pS.close()
      }
    }
  }

  type PGArr[T] = Function1[PGScalaResultSet => T, IndexedSeq[T]]

  def arr[T](query: String): PGArr[T] =
    arr(query, IndexedSeq.empty)

  def arr[T, P1](query: String, p1: P1)(implicit c1: PGConverter[P1]): PGArr[T] =
    arr(query, IndexedSeq(ParamText(p1, c1)))
}