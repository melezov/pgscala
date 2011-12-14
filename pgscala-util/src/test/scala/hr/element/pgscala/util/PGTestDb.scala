package hr.element.pgscala.util
package test

import org.streum.configrity._

import com.mchange.v2.c3p0.ComboPooledDataSource
import java.sql.ResultSet

object PGTestDb {

  private lazy val dbConfig =
    Configuration.loadResource("/db.conf").detach("db")

  lazy val host = dbConfig[String]("host")
  lazy val port = dbConfig[Int]("port")
  lazy val dbName = dbConfig[String]("dbname")
  lazy val user = dbConfig[String]("user")
  lazy val pass = dbConfig[String]("pass")

  private lazy val pool = {
    val driver = "org.postgresql.Driver"
    Class.forName(driver)

    val url =
      "jdbc:postgresql://%s/%s" format(host, dbName)

    val cpds = new ComboPooledDataSource()
    cpds.setDriverClass(driver)
    cpds.setJdbcUrl(url)
    cpds.setUser(user)
    cpds.setPassword(pass)
    cpds.setMaxPoolSize(128)
    cpds.setMaxStatements(0)
    cpds
  }

  def qry[T](body: String)(f: ResultSet => T): T = {
    try {
      val con = pool.getConnection();
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
