package org.pgscala
package test

import java.sql.{ Statement, ResultSet }
import org.postgresql.ds.PGSimpleDataSource
import java.util.Properties
import org.slf4j.LoggerFactory

object PGTestDb {
  private lazy val dbConfig = {
    val props = new Properties
    props.load(getClass.getResourceAsStream("/db.properties"))
    props
  }

  private val credentials = PGCredentials(
    host = dbConfig.getProperty("host")
  , port = dbConfig.getProperty("port").toInt
  , dbName = dbConfig.getProperty("dbname")
  , user = dbConfig.getProperty("user")
  , password = dbConfig.getProperty("password")
  )

  val sessionFactory: PGSessionFactory =
    new PGSimple(credentials)
}
