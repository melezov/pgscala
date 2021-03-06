package org.pgscala

import converters._

import org.joda.time.DateTime

class PGScalaResultSet(rS: java.sql.ResultSet) extends Iterator[PGScalaResultSet] {
  private var needToReadNext = true
  private var currentHasNext = false

  def hasNext = {
    if (needToReadNext){
      currentHasNext = rS.next()
      needToReadNext = false
    }

    currentHasNext
  }

  def next() = {
    require(hasNext, "Call to next() on empty PGScalaResultSet")
    needToReadNext = true
    this
  }

  def close() =
    rS.close()

  // ---------------------------------------------------------------------------

  /**
   * Map used for accessing result sets with clashing column names:
   *
   *   SELECT 1 AS a, 'foo' AS a;
   *
   * Use this code to access both columns:
   *
   *   rS.get[Int]("a")       // retrieves the first "a" column by default
   *   rS.get[String]("a", 2) // retrieves the second "a" column (one based index)
   */

  protected lazy val columns = {
    val mD = rS.getMetaData
    (1 to mD.getColumnCount)
      .map(i => mD.getColumnName(i) -> i)
      .groupBy(_._1)
      .mapValues(_.map(_._2))
  }

  //  --------------------------------------------------------------------------

  def one[T](implicit c: PGConverter[T]): T =
    c fromPGString (rS getString 1)

  def get[T](index: Int)(implicit c: PGConverter[T]): T =
    c fromPGString (rS getString index)

  def get[T](column: String)(implicit c: PGConverter[T]): T =
    c fromPGString (rS getString column)

  def get[T](column: String, number: Int)(implicit c: PGConverter[T]): T =
    get(columns(column)(number - 1))(c)

  //  --------------------------------------------------------------------------

  /* Helper methods: get[Option[Long]](...) === opt[Long](...) */

  def opt[T](index: Int)(implicit c: PGConverter[Option[T]]): Option[T] =
    c fromPGString (rS getString index)

  def opt[T](column: String)(implicit c: PGConverter[Option[T]]): Option[T] =
    c fromPGString (rS getString column)

  def opt[T](column: String, number: Int)(implicit c: PGConverter[Option[T]]): Option[T] =
    opt(columns(column)(number - 1))(c)

  //  --------------------------------------------------------------------------

  def arr[T](index: Int)(implicit c: PGConverter[T]): IndexedSeq[T] =
    (new PGTraversableConverter[T]).fromPGString(rS getString index)

  def arr[T](column: String)(implicit c: PGConverter[T]): IndexedSeq[T] =
    (new PGTraversableConverter[T]).fromPGString(rS getString column)

  def arr[T](column: String, number: Int)(implicit c: PGConverter[T]): IndexedSeq[T] =
    arr(columns(column)(number - 1))(c)
}
