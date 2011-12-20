package hr.element.pgscala

import scala.collection.immutable.IndexedSeqMap
import org.joda.time.{DateTime,LocalDate}

class PGScalaResultSet(val rs: java.sql.ResultSet) extends Iterator[PGScalaResultSet]{

  /*
   * IndexedSeqMap linking Columns to their respectable positions in the ResultSet,
   * and backwards (if need be) through the IndexedSeqMap.toIndexedSeq interface.
   */

  val colMap = {
    val md = rs.getMetaData
    val columns = IndexedSeqMap.empty ++
      (for (i <- 1 to md.getColumnCount) yield { i -> md.getColumnName(i) })

    val positions =
      columns.groupBy(_._2).map(_._2).flatMap(_.zipWithIndex.map{
        case((p,c),i) => Column(Symbol(c), i+1) -> p}
      ).toIndexedSeq

    IndexedSeqMap.empty ++ positions.sortBy(_._2)
  }

//  ----------------------------------------------------------------

  private var needToReadNext = true
  private var currentHasNext = false

  def hasNext = {
    if (needToReadNext){
      currentHasNext = rs.next()
      needToReadNext = false
    }
    currentHasNext
  }

  def next() = {
    if (!hasNext) throw new java.util.NoSuchElementException("next on empty PGScalaResultSet")
    needToReadNext = true
    this
  }

  def close() = rs.close()

//  ----------------------------------------------------------------

  /**
   * A conversion from <code>java.math.BigDecimal</code> to <code>scala.math.BigDecimal</code>.
   */

  protected def javaBigDecimal2scalaBigDecimal(bD: java.math.BigDecimal) =
    if (null eq bD) null else BigDecimal(bD)

  /**
   * A conversion from <code>java.sql.Timestamp</code> to <code>joda.time.DateTime</code>.
   */

  protected def javaTimestamp2jodaDateTime(tS: java.sql.Timestamp) =
    if (null eq tS) null else new DateTime(tS.getTime())

  /**
   * A conversion from <code>java.sql.Date</code> to <code>joda.time.LocalDate</code>.
   */

  protected def javaDate2jodaLocalDate(d: java.sql.Date) =
    if (null eq d) null else LocalDate.fromDateFields(d)

//  ----------------------------------------------------------------

  def getJavaBool(index: Int): java.lang.Boolean = {
    val b = rs.getBoolean(index)
    if (rs.wasNull) null else b
  }

  def getJavaBool(c: Column): java.lang.Boolean =
    getJavaBool(colMap(c))

  def getBool(index: Int): Boolean = {
    val b = getJavaBool(index)
    if (null eq b) throw new NullPointerException("Boolean value was null!")
    b.booleanValue
  }

  def getBool(c: Column): Boolean =
    getBool(colMap(c))

  protected def javaBool2Opt(b: java.lang.Boolean): Option[Boolean] =
    if (null eq b) None else Some(b.booleanValue)

  def optBool(index: Int): Option[Boolean] =
    javaBool2Opt(getJavaBool(index))

  def optBool(c: Column): Option[Boolean] =
    optBool(colMap(c))

//  ----------------------------------------------------------------

  def getJavaShort(index: Int): java.lang.Short = {
    val s = rs.getShort(index)
    if (rs.wasNull) null else s
  }

  def getJavaShort(c: Column): java.lang.Short =
    getJavaShort(colMap(c))

  def getShort(index: Int): Short = {
    val s = getJavaShort(index)
    if (null eq s) throw new NullPointerException("Short value was null!")
    s.shortValue
  }

  def getShort(c: Column): Short =
    getShort(colMap(c))

  protected def javaShort2Opt(s: java.lang.Short): Option[Short] =
    if (null eq s) None else Some(s.shortValue)

  def optShort(index: Int): Option[Short] =
    javaShort2Opt(getJavaShort(index))

  def optShort(c: Column): Option[Short] =
    optShort(colMap(c))

//  ----------------------------------------------------------------

  def getJavaInt(index: Int): java.lang.Integer = {
    val i = rs.getInt(index)
    if (rs.wasNull) null else i
  }

  def getJavaInt(c: Column): java.lang.Integer =
    getJavaInt(colMap(c))

  def getInt(index: Int): Int = {
    val i = getJavaInt(index)
    if (null eq i) throw new NullPointerException("Integer value was null!")
    i.intValue
  }

  def getInt(c: Column): Int =
    getInt(colMap(c))

  protected def javaInt2Opt(i: java.lang.Integer): Option[Int] =
    if (null eq i) None else Some(i.intValue)

  def optInt(index: Int): Option[Int] =
    javaInt2Opt(getJavaInt(index))

  def optInt(c: Column): Option[Int] =
    optInt(colMap(c))

//  ----------------------------------------------------------------

  def getJavaLong(index: Int): java.lang.Long = {
    val l = rs.getLong(index)
    if (rs.wasNull) null else l
  }

  def getJavaLong(c: Column): java.lang.Long =
    getJavaLong(colMap(c))

  def getLong(index: Int): Long = {
    val l = getJavaLong(index)
    if (null eq l) throw new NullPointerException("Long value was null!")
    l.longValue
  }

  def getLong(c: Column): Long =
    getLong(colMap(c))

  protected def javaLong2Opt(l: java.lang.Long): Option[Long] =
    if (null eq l) None else Some(l.longValue)

  def optLong(index: Int): Option[Long] =
    javaLong2Opt(getJavaLong(index))

  def optLong(c: Column): Option[Long] =
    optLong(colMap(c))

//  ----------------------------------------------------------------

  def getJavaFloat(index: Int): java.lang.Float = {
    val f = rs.getFloat(index)
    if (rs.wasNull) null else f
  }

  def getJavaFloat(c: Column): java.lang.Float =
    getJavaFloat(colMap(c))

  def getFloat(index: Int): Float = {
    val f = getJavaFloat(index)
    if (null eq f) throw new NullPointerException("Float value was null!")
    f.longValue
  }

  def getFloat(c: Column): Float =
    getFloat(colMap(c))

  protected def javaFloat2Opt(f: java.lang.Float): Option[Float] =
    if (null eq f) None else Some(f.longValue)

  def optFloat(index: Int): Option[Float] =
    javaFloat2Opt(getJavaFloat(index))

  def optFloat(c: Column): Option[Float] =
    optFloat(colMap(c))

//  ----------------------------------------------------------------

  def getJavaDouble(index: Int): java.lang.Double = {
    val d = rs.getDouble(index)
    if (rs.wasNull) null else d
  }

  def getJavaDouble(c: Column): java.lang.Double =
    getJavaDouble(colMap(c))

  def getDouble(c: Column): Double =
    getDouble(colMap(c))

  def getDouble(index: Int): Double = {
    val d = getJavaDouble(index)
    if (null eq d) throw new NullPointerException("Double value was null!")
    d.longValue
  }

  protected def javaDouble2Opt(d: java.lang.Double): Option[Double] =
    if (null eq d) None else Some(d.longValue)

  def optDouble(index: Int): Option[Double] =
    javaDouble2Opt(getJavaDouble(index))

  def optDouble(c: Column): Option[Double] =
    optDouble(colMap(c))

//  ----------------------------------------------------------------

  def getJavaStr(index: Int): java.lang.String = {
    val s = rs.getString(index)
    if (rs.wasNull) null else s
  }

  def getJavaStr(c: Column): java.lang.String =
    getJavaStr(colMap(c))

  def getStr(index: Int): String = {
    val s = getJavaStr(index)
    if (null eq s) throw new NullPointerException("String value was null!")
    s
  }

  def getStr(c: Column): String =
    getStr(colMap(c))

  protected def javaStr2Opt(s: java.lang.String): Option[String] =
    if (null eq s) None else Some(s)

  def optStr(index: Int): Option[String] =
    javaStr2Opt(getJavaStr(index))

  def optStr(c: Column): Option[String] =
    optStr(colMap(c))

//  ----------------------------------------------------------------

  def getJavaBigDec(index: Int): java.math.BigDecimal = {
    val bD = rs.getBigDecimal(index)
    if (rs.wasNull) null else bD
  }

  def getJavaBigDec(c: Column): java.math.BigDecimal =
    getJavaBigDec(colMap(c))

  def getBigDec(index: Int): BigDecimal = {
    val bD = getJavaBigDec(index)
    if (null eq bD) throw new NullPointerException("Big decimal value was null!")
    javaBigDecimal2scalaBigDecimal(bD)
  }

  def getBigDec(c: Column): BigDecimal =
    getBigDec(colMap(c))

  protected def javaBigDec2Opt(bD: java.math.BigDecimal): Option[BigDecimal] =
    if (null eq bD) None else Some(BigDecimal(bD))

  def optBigDec(index: Int): Option[BigDecimal] =
    javaBigDec2Opt(getJavaBigDec(index))

  def optBigDec(c: Column): Option[BigDecimal] =
    optBigDec(colMap(c))

//  ----------------------------------------------------------------

  def getJavaBin(index: Int): Array[Byte] = {
    val bA = rs.getBytes(index)
    if (rs.wasNull) null else bA
  }

  def getJavaBin(c: Column): Array[Byte] =
    getJavaBin(colMap(c))

  def getBin(index: Int): Array[Byte] = {
    val bA = getJavaBin(index)
    if (null eq bA) throw new NullPointerException("Binary value was null!")
    bA
  }

  def getBin(c: Column): Array[Byte] =
    getBin(colMap(c))

  protected def javaBin2Opt(bA: Array[Byte]): Option[Array[Byte]] =
    if (null eq bA) None else Some(bA)

  def optBin(index: Int): Option[Array[Byte]] =
    javaBin2Opt(getJavaBin(index))

  def optBin(c: Column): Option[Array[Byte]] =
    optBin(colMap(c))

//  ----------------------------------------------------------------

  def getJavaTime(index: Int): java.sql.Timestamp = {
    val tS = rs.getTimestamp(index)
    if (rs.wasNull) null else tS
  }

  def getJavaTime(c: Column): java.sql.Timestamp =
    getJavaTime(colMap(c))

  def getTime(index: Int): DateTime = {
    val tS = getJavaTime(index)
    if (null eq tS) throw new NullPointerException("Timestamp value was null!")
    javaTimestamp2jodaDateTime(tS)
  }

  def getTime(c: Column): DateTime =
    getTime(colMap(c))

  protected def javaTime2Opt(tS: java.sql.Timestamp): Option[DateTime] =
    if (null eq tS) None else Some(javaTimestamp2jodaDateTime(tS))

  def optTime(index: Int): Option[DateTime] =
    javaTime2Opt(getJavaTime(index))

  def optTime(c: Column): Option[DateTime] =
    optTime(colMap(c))

//  ----------------------------------------------------------------

  def getJavaDate(index: Int): java.sql.Date = {
    val d = rs.getDate(index)
    if (rs.wasNull) null else d
  }

  def getJavaDate(c: Column): java.sql.Date =
    getJavaDate(colMap(c))

  def getDate(index: Int): LocalDate = {
    val d = getJavaDate(index)
    if (null eq d) throw new NullPointerException("Date value was null!")
    javaDate2jodaLocalDate(d)
  }

  def getDate(c: Column): LocalDate =
    getDate(colMap(c))

  protected def javaDate2Opt(d: java.sql.Date): Option[LocalDate] =
    if (null eq d) None else Some(javaDate2jodaLocalDate(d))

  def optDate(index: Int): Option[LocalDate] =
    javaDate2Opt(getJavaDate(index))

  def optDate(c: Column): Option[LocalDate] =
    optDate(colMap(c))
}