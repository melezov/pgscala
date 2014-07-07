package hr.element.pgscala

import javax.sql.DataSource
import org.postgresql.ds.PGSimpleDataSource

class PGSimple(creds: PGCredentials) extends PGSimpleDataSource
                                     with PGSessionFactory {
  val ds = this

  override def getParentLogger = sys error "WATCH THIS SPACE"

  setServerName(creds.host)
  setPortNumber(creds.port)
  setDatabaseName(creds.dbName)
  setUser(creds.user)
  setPassword(creds.pass)
}
