package org.pgscala

import javax.sql.DataSource
import com.mchange.v2.c3p0.ComboPooledDataSource

class PGPool(creds: PGCredentials) extends PGSessionFactory {
  val ds = {
    val cpds = new ComboPooledDataSource()

    cpds.setDriverClass("org.postgresql.Driver")
    cpds.setJdbcUrl("jdbc:postgresql://%s:%d/%s"
      format(creds.host, creds.port, creds.dbName))

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
}
