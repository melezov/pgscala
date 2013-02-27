package org.pgscala

import util._
import converters._

import java.sql.{ Statement, ResultSet }

import org.slf4j.{ Logger, LoggerFactory }

class PGScala(con: java.sql.Connection) {
  import Parametrifier.ParamText

  /**
   * Perform a database insert, update or delete.
   */

  def iud(query: String, params: ParamText[_]*): Int = {
    val p = Parametrifier(query, IndexedSeq(params: _*))

    if (p.preparedParams.isEmpty) {
      val st = con.createStatement()
      try {
        st.executeUpdate(p.preparedQuery)
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

        pS.executeUpdate()
      }
      finally {
        pS.close()
      }
    }
  }

  /**
   * Generic database query
   */

  def qry[T](query: String, params: ParamText[_]*)(f: PGScalaResultSet => T): T = {
    val p = Parametrifier(query, IndexedSeq(params: _*))

    if (p.preparedParams.isEmpty) {
      val st = con.createStatement()
      try {
        val rS = st.executeQuery(p.preparedQuery)
        try {
          f(new PGScalaResultSet(rS))
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
          f(new PGScalaResultSet(rS))
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

  /**
   * Query one database tuple.
   */

  def row[T](query: String, params: ParamText[_]*)(f: PGScalaResultSet => T): Option[T] =
    qry(query, params: _*){ rS =>
      if (rS.isEmpty) {
        None
      }
      else {
        val res = f(rS.next())

        assert(
           !rS.hasNext,
          "Row query returned more than one row!"
        )

        Some(res)
      }
    }

  /**
   * Query multiple database tuples (can be empty), and retrieve all results as an IndexedSeq.
   */

  def arr[T](query: String, params: ParamText[_]*)(f: PGScalaResultSet => T): IndexedSeq[T] =
    qry(query, params: _*){ rS =>
      rS map f toIndexedSeq
    }

  /**
   * Query multiple database tuples (can be empty), and retrieve all results as an IndexedSeqSet.
   */

  def set[T](query: String, params: ParamText[_]*)(f: PGScalaResultSet => T): IndexedSeqSet[T] = {
    val at = arr(query, params: _*)(f)
    val res = IndexedSeqSet.empty ++ at

    res.ensuring(
      _.size == at.size,
      "Duplicate entries detected! (only " + res.size + "/" + at.size + " unique elements!)"
    )
  }

  /**
   * Query database for a key-value map.
   */

  def map[K, V](query: String, params: ParamText[_]*)(f: PGScalaResultSet => (K, V)): IndexedSeqMap[K, V] = {
    val akv = arr(query, params: _*)(f)
    val res = (IndexedSeqMap.empty ++ akv).asInstanceOf[IndexedSeqMap[K, V]]

    res.ensuring(
      _.size == akv.size,
      "Duplicate map keys detected! (only " + res.size + "/" + akv.size + " unique keys)"
    )
  }

  /**
   * Query database for a key-value bag.
   */

  import scala.collection.mutable.LinkedHashMap
  import scala.collection.immutable.VectorBuilder

  def bag[K, V](query: String, params: ParamText[_]*)(f: PGScalaResultSet => (K, V)): IndexedSeqMap[K, IndexedSeq[V]] = {
    qry(query, params: _*){ rS =>
      val rM = LinkedHashMap.empty[K, VectorBuilder[V]]

      while(rS.hasNext){
        val kv = f(rS.next())

        rM.getOrElse(kv._1, {
          val vB = new VectorBuilder[V]
          rM(kv._1) = vB
          vB
        }) += kv._2
      }

      IndexedSeqMap.empty[K, IndexedSeq[V]] ++ rM.mapValues(_.result)
    }}

  //  --------------------------------------------------------------------------

  /* Helper method for queries returning a scalar value */
  def get[T](query: String, params: ParamText[_]*)(implicit c: PGConverter[T]): T =
    row(query, params: _*){_.one[T]}.get

  /* Helper method for aggregating simple values */
  def getArr[T](query: String, params: ParamText[_]*)(implicit c: PGConverter[T]): IndexedSeq[T] =
    arr(query, params: _*){_.one[T]}
}
