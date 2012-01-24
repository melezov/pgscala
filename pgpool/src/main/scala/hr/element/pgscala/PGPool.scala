package hr.element.pgscala

case class PGCredentials(
  host: String,
  port: Int,
  dbName: String,
  user: String,
  pass: String
)

import com.mchange.v2.c3p0.ComboPooledDataSource

class PGPool(creds: PGCredentials) {
  private val pool = {
    val cpds = new ComboPooledDataSource()

    cpds.setDriverClass("org.postgresql.Driver")
    cpds.setJdbcUrl("jdbc:postgresql://%s/%s"
      format(creds.host, creds.port))

    cpds.setUser(creds.user)
    cpds.setPassword(creds.pass)

    cpds.setMinPoolSize(8)
    cpds.setMaxPoolSize(64)

    cpds.setAcquireIncrement(8)
    cpds.setAcquireRetryAttempts(120)
    cpds.setAcquireRetryDelay(250) // ms

    cpds.setMaxStatements(128)
    cpds.setMaxStatementsPerConnection(8)

    cpds
  }

  def txn[T](f: PGScala => T) = {
    val con = pool.getConnection()
    con.setAutoCommit(false)

    try {
      val res = f(new PGScala(con))
      con.commit()
      res
    }
    catch {
      case e =>
        con.rollback()
        throw e
    }
    finally {
      con.close()
    }
  }
}
