package org.pgscala.util
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

  lazy val host = dbConfig.getProperty("host")
  lazy val port = dbConfig.getProperty("port").toInt
  lazy val dbName = dbConfig.getProperty("dbname")
  lazy val user = dbConfig.getProperty("user")
  lazy val password = dbConfig.getProperty("password")

  private lazy val pgds = {
    val driver = "org.postgresql.Driver"
    Class.forName(driver)

    val pgsds = new PGSimpleDataSource()
    pgsds.setServerName(host)
    pgsds.setPortNumber(port)
    pgsds.setDatabaseName(dbName)
    pgsds.setUser(user)
    pgsds.setPassword(password)
    pgsds
  }

  def testInSchema(schema: String)(f: => Unit): Unit = {
    iud("CREATE SCHEMA %s;" format PGIdent.quote(schema))
    try {
      f
    }
    finally{
      iud("DROP SCHEMA %s CASCADE;" format PGIdent.quote(schema))
    }
  }

  def prepareSchemaQuery(schema: Option[String], st: Statement) =
    schema match {
      case Some(s) =>
        st.execute("SET search_path TO %s;" format PGIdent.quote(s))
      case _ =>
    }

  def qry[T](body: String)(f: ResultSet => T)(implicit schema: Option[String] = None): T = {
    val con = pgds.getConnection();
    try {
      val st = con.createStatement()
      try {
        prepareSchemaQuery(schema, st)
        val rS = st.executeQuery(body)
        try {
          f(rS)
        }
        finally rS.close()
      }
      finally st.close()
    }
    finally con.close()
  }

  def iud(body: String)(implicit schema: Option[String] = None): Unit = {
    val con = pgds.getConnection();
    try {
      val st = con.createStatement()
      try {
        prepareSchemaQuery(schema, st)
        st.execute(body)
      }
      finally st.close()
    }
    finally con.close()
  }
}
