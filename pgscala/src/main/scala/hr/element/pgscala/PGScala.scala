package hr.element.pgscala

import hr.element.pgscala.converters.PGTypeConverter

import scala.collection.immutable.{VectorBuilder, IndexedSeqMap}
import scala.collection.mutable.LinkedHashMap

import org.postgresql.ds.PGSimpleDataSource
import org.joda.time.{DateTime, LocalDate}

import java.util.regex.Matcher
import javax.sql.DataSource
import java.sql.PreparedStatement 

object PGScala{

  val esc = util.PGLiteral.quote _

  protected val QueryEnd = ";$".r
  def prolongQuery(query: String, chunk: String) =
    QueryEnd.replaceFirstIn(query, Matcher.quoteReplacement(chunk))
}

/**
 *  This class implements a bridge between the <a href="http://jdbc.postgresql.org/"
 *  target="contentFrame">PostgreSQL JDBC driver</a> and Scala classes.
 *
 *  PGScala is not an ORM.
 *
 *  @author  Element d.o.o.
 *  @version 0.7.3, 25/01/2012
 */

class PGScala(con: java.sql.Connection) {

  /**
   * Conversion from <code>joda.time.DateTime</code> to <code>java.sql.Timestamp</code>.
   */

  protected def jodaDateTime2javaTimestamp(dT: DateTime) =
    if (null eq dT) null else new java.sql.Timestamp(dT.getMillis)

  /**
   * Conversion from <code>joda.time.LocalDate</code> to <code>java.sql.Date</code>.
   */

  protected def jodaLocalDate2javaDate(lD: LocalDate) =
    if (null eq lD) null else new java.sql.Date(lD.toDateTimeAtStartOfDay.getMillis)

//  ----------------------------------------------------------------

  import PGTypeConverter.pgType

  protected def parametrify(st: java.sql.PreparedStatement, params: Any*) {

    var index = 1
    for(p <- params){
      setParam(p)
      index += 1
    }

    def setObject[T](pT: PG.PGType, value: Any){
      st.setObject(index, value, pT.tipe)
    }

    def setArray[T](pT: PG.PGType, arr: Array[T]){
      st.setArray(index, con.createArrayOf(pT.enum, arr.map(_.asInstanceOf[java.lang.Object])))
    }

    def setParam(p: Any){
        p match {
          case null | None =>             setObject(PG.Null, null)

          case b: Boolean =>              setObject(PG.Bool, b)
          case Some(b: Boolean) =>        setObject(PG.Bool, b)
          case bA: Array[Boolean] =>      setArray(PG.Bool, bA)

          case s: Short =>                setObject(PG.Int2, s)
          case Some(s: Short) =>          setObject(PG.Int2, s)
          case sA: Array[Short] =>        setArray(PG.Int2, sA)

          case i: Int =>                  setObject(PG.Int4, i)
          case Some(i: Int) =>            setObject(PG.Int4, i)
          case iA: Array[Int] =>          setArray(PG.Int4, iA)

          case l: Long =>                 setObject(PG.Int8, l)
          case Some(l: Long) =>           setObject(PG.Int8, l)
          case lA: Array[Long] =>         setArray(PG.Int8, lA)

          case f: Float =>                setObject(PG.Float4, f)
          case Some(f: Float) =>          setObject(PG.Float4, f)
          case fA: Array[Float] =>        setArray(PG.Float4, fA)

          case d: Double =>               setObject(PG.Float8, d)
          case Some(d: Double) =>         setObject(PG.Float8, d)
          case dA: Array[Double] =>       setArray(PG.Float8, dA)

          case s: String =>               setObject(PG.Text, s)
          case Some(s: String) =>         setObject(PG.Text, s)
          case sA: Array[String] =>       setArray(PG.Text, sA)

          case bD: BigDecimal =>          setObject(PG.Numeric, bD)
          case Some(bD: BigDecimal) =>    setObject(PG.Numeric, bD)
          case bDA: Array[BigDecimal] =>  setArray(PG.Numeric, bDA)

          case bA: Array[Byte] =>         setObject(PG.Bytea, bA)
          case Some(bA: Array[Byte]) =>   setObject(PG.Bytea, bA)
//        Bug in JDBC setArray driver; will convert form bytea[] to byte[][]
//        case bAA: Array[Array[Byte]] => setArray(PG.Bytea, bAA)

          case lD: LocalDate =>           setObject(PG.Date, jodaLocalDate2javaDate(lD))
          case Some(lD: LocalDate) =>     setObject(PG.Date, jodaLocalDate2javaDate(lD))
          case lDA: Array[LocalDate] =>   setArray(PG.Date, lDA.map(jodaLocalDate2javaDate))

          case dT: DateTime =>            setObject(PG.Timestamptz, jodaDateTime2javaTimestamp(dT))
          case Some(dT: DateTime) =>      setObject(PG.Timestamptz, jodaDateTime2javaTimestamp(dT))
          case dTA: Array[DateTime] =>    setArray(PG.Timestamptz, dTA.map(jodaDateTime2javaTimestamp))

          case _ => throw new IllegalArgumentException("Unhandled query parameter type!")
        }
    }
  }

//  ################################################################


  protected def prep_1 [R] (query: String) (f: PreparedStatement => R): R = {
    val st = con.prepareStatement (query)
    try f (st) finally st.close
  }

  protected def prep_1 [T1: PGTypeConverter, R]
    (query: String, param1: T1)
    (f: PreparedStatement => R): R = {

    val st = con.prepareStatement (query)
    try {
      st.setObject (1, param1, pgType [T1])
      f (st)
    } finally st.close
  }

  protected def prep_1
    [T1: PGTypeConverter, T2: PGTypeConverter, R]
    (query: String, param1: T1, param2: T2)
    (f: PreparedStatement => R): R = {

    val st = con.prepareStatement (query)
    try {
      st.setObject (1, param1, pgType [T1])
      st.setObject (2, param2, pgType [T2])
      f (st)
    } finally st.close
  }


  protected def prep[P](query: String, params: Any*)(useStatement: java.sql.PreparedStatement => P) = {
    val st = con.prepareStatement(query)
    try{
      parametrify(st, params: _*)
      useStatement(st)
    }
    finally{
      st.close()
    }
  }

//   bracket [R, A] (create: => R, destroy: (R) => Any) (consume: R => A): A = {
//     val r = create
//     try consume (r) finally destroy (r)
//   }


  /**
   * Generic database query
   */

  protected def qry_1 [Q](query: String)(f: PGScalaResultSet => Q) =
    prep_1 (query) { st =>
      val rS = new PGScalaResultSet(st.executeQuery())
      try f (rS) finally rS.close
    }
  protected def qry_1 [T1: PGTypeConverter, Q](query: String, param1: T1)(f: PGScalaResultSet => Q) =
    prep_1 (query, param1) { st =>
      val rS = new PGScalaResultSet(st.executeQuery())
      try f (rS) finally rS.close
    }

  protected def qry_1 [T1: PGTypeConverter, T2: PGTypeConverter, Q](query: String, param1: T1, param2: T1)(f: PGScalaResultSet => Q) =
    prep_1 (query, param1, param2) { st =>
      val rS = new PGScalaResultSet(st.executeQuery())
      try f (rS) finally rS.close
    }

  protected def qry[Q](query: String, params: Any*)(usePGScalaResultSet: PGScalaResultSet => Q) =
    prep(query, params: _*){ st => {
      val rs = new PGScalaResultSet(st.executeQuery())
      try{
        usePGScalaResultSet(rs)
      }
      finally{
        rs.close()
      }
    }}

  /**
    * Query multiple database tuples (can be empty), and retrieve all results in an IndexedSeq.
    */

  def arr[A](query: String, params: Any*)(processTuple: PGScalaResultSet => A): IndexedSeq[A] =
    qry(query, params: _*){_.map(processTuple).toIndexedSeq}

  def arr[T1: PGTypeConverter, A]
      (query: String, param1: T1) 
      (f: PGScalaResultSet => A)
      : IndexedSeq[A]
      = qry (query, param1) (_ map f toIndexedSeq)

  /**
   * Query one database tuple.
   */

  def row[A](query: String, params: Any*)(processTuple: PGScalaResultSet => A): Option[A] = {
    val res = arr(query, params: _*)(processTuple)
    res.size match {
      case 0 =>
        None
      case 1 =>
        Some(res.head)
      case x =>
        throw new java.sql.SQLException("Row query returned more than one row (%d)!".format(x))
    }
  }

  /**
    * Query database for a key-value map.
    */

  def map[A, B](query: String, params: Any*)(processTuple: PGScalaResultSet => (A, B)): Map[A, B] = {
    val akv = arr(query, params: _*)(processTuple)
    val res = IndexedSeqMap.empty ++ akv

    if (akv.size != res.size)
        throw new java.sql.SQLException("Duplicate map keys detected! (only " + res.size + "/" + akv.size + " unique keys)")
    res
  }

  /**
    * Query database for a key-value bag.
    */

  def bag[A, B](query: String, params: Any*)(processTuple: PGScalaResultSet => (A, B)): Map[A,IndexedSeq[B]] =
    qry(query, params: _*){ rs => {
      val rM = LinkedHashMap.empty[A,VectorBuilder[B]]

      while(rs.hasNext){
        val kv = processTuple(rs.next)

        rM.getOrElse(kv._1, {
          val vB = new VectorBuilder[B]()
          rM(kv._1) = vB
          vB
        }) += kv._2
      }

      IndexedSeqMap.empty ++ rM.map(kv => kv.copy(_2=kv._2.result))
    }}

  /**
   * Perform a database insert, update or delete.
   */

  def iud(query: String, params: Any*): Int =
    prep(query, params: _*){ _.executeUpdate() }

//  ----------------------------------------------------------------

  def getBoolArr(query: String, params: Any*) = arr(query, params: _*){_.getBool(1)}
  def getIntArr (query: String, params: Any*) = arr(query, params: _*){_.getInt(1)}
  def getLongArr(query: String, params: Any*) = arr(query, params: _*){_.getLong(1)}
  def getStrArr (query: String, params: Any*) = arr(query, params: _*){_.getStr(1)}

//  ----------------------------------------------------------------

  def getBool(query: String, params: Any*) = row(query, params: _*){_.getBool(1)}.get
  def getInt (query: String, params: Any*) = row(query, params: _*){_.getInt(1)}.get
  def getLong(query: String, params: Any*) = row(query, params: _*){_.getLong(1)}.get
  def getStr (query: String, params: Any*) = row(query, params: _*){_.getStr(1)}.get

//  ----------------------------------------------------------------

  def iudRet(query: String, params: Any*)(implicit returning: Option[Column] = None) =
    returning match {
      case Some(col) => {
        val retQuery = PGScala.prolongQuery(query, toQuery(" RETURNING ", col, ";"))
        getInt(retQuery, params: _*)
      }
      case None => iud(query, params: _*)
    }

//  ----------------------------------------------------------------

  protected def toQuery(fragments: Any*): String =
    fragments.map(_ match {
      case query: String => query
      case ident: Ident => ident.toString
    }).toList.mkString

  def exists(table: Table, column: Column, value: Any) =
    getBool(toQuery("SELECT EXISTS(SELECT 1 FROM ", table, " WHERE ", column, "=?);"), value)

  def insert(table: Table, valueMap: Map[Column,Any])(
      implicit returning: Option[Column] = None) = {

    val columns = valueMap.map(col => toQuery(col._1)).mkString("(", ",", ")")
    val qMarks = List.fill(valueMap.size){"?"}.mkString("(", ",", ")")
    val params = valueMap.map(_._2).toArray

    val query = toQuery("INSERT INTO ", table, columns, "VALUES", qMarks, ";")
    iudRet(query, params: _*)(returning)
  }

  def insertCC(table: Table, cc: AnyRef, blackList: Set[Column] = Set())(
      implicit returning: Option[Column] = None) = {

    val params = IndexedSeqMap.empty ++ (
      for{ fld <- cc.getClass.getDeclaredFields
        val col = Column(Symbol(fld.getName))
        if (!blackList.contains(col))
      } yield {
        fld.setAccessible(true)
        col -> fld.get(cc).asInstanceOf[Any]
      }
    )

    insert(table, params)(returning)
  }

  def update(table: Table, updateKey: Column, valueMap: Map[Column,Any])(
      implicit returning: Option[Column] = None) = {

    val keyValue = valueMap(updateKey)
    val updateMap = valueMap - updateKey
    val columns = updateMap.map(col => { toQuery(col._1, "=?") }).mkString(",")
    val params = (updateMap.map(_._2).toList ::: keyValue :: Nil).toArray

    val query = toQuery("UPDATE ", table, " SET ", columns, " WHERE ", updateKey, "=?;")
    iudRet(query, params: _*)(returning)
  }

  def updateCC(table: Table, updateKey: Column, cc: AnyRef, blackList: Set[Column] = Set())(
      implicit returning: Option[Column] = None) = {

    val params = IndexedSeqMap.empty ++ (
      for{ fld <- cc.getClass.getDeclaredFields
        val col = Column(Symbol(fld.getName))
        if (!blackList.contains(col))
      } yield {
        fld.setAccessible(true)
        col -> fld.get(cc).asInstanceOf[Any]
      }
    )

    update(table, updateKey, params)(returning)
  }
}
