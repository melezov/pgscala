package hr.element.pgscala

import javax.sql.DataSource

trait PGSessionFactory {
  val ds: DataSource

  def using[T](f: PGScala => T) = {
    val con = ds.getConnection()
    con.setAutoCommit(false)

    try {
      val res = f(new PGScala(con))
      con.commit()
      res
    }
    catch {
      case e: Throwable =>
        con.rollback()
        throw e
    }
    finally {
      con.close()
    }
  }
}
