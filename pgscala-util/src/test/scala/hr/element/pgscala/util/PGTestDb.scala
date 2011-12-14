package hr.element.pgscala.util
package test

import org.streum.configrity._
import java.sql.ResultSet
import org.postgresql.ds.PGSimpleDataSource

object PGTestDb {

  private lazy val dbConfig =
    Configuration.loadResource("/db.conf").detach("db")

  lazy val host = dbConfig[String]("host")
  lazy val port = dbConfig[Int]("port")
  lazy val dbName = dbConfig[String]("dbname")
  lazy val user = dbConfig[String]("user")
  lazy val pass = dbConfig[String]("pass")

  private lazy val pgds = {
    val driver = "org.postgresql.Driver"
    Class.forName(driver)

    val pgsds = new PGSimpleDataSource()
    pgsds.setServerName(host)
    pgsds.setPortNumber(port)
    pgsds.setDatabaseName(dbName)
    pgsds.setUser(user)
    pgsds.setPassword(pass)
    pgsds
  }

  def qry[T](body: String)(f: ResultSet => T): T = {
    try {
      val con = pgds.getConnection();
      try {
        val st = con.createStatement()
        try {
          val rs = st.executeQuery(body)
          try {
            f(rs)
          }
          finally rs.close()
        }
        finally st.close()
      }
      finally con.close()
    }
  }
}
