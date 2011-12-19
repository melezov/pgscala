//package hr.element.pgscala.util
//package test
//
//import org.streum.configrity._
//import java.sql.{Statement, ResultSet}
//import org.postgresql.ds.PGSimpleDataSource
//
//object PGTestDb {
//
//  private lazy val dbConfig =
//    Configuration.loadResource("/db.conf").detach("db")
//
//  lazy val host = dbConfig[String]("host")
//  lazy val port = dbConfig[Int]("port")
//  lazy val dbName = dbConfig[String]("dbname")
//  lazy val user = dbConfig[String]("user")
//  lazy val pass = dbConfig[String]("pass")
//
//  private lazy val pgds = {
//    val driver = "org.postgresql.Driver"
//    Class.forName(driver)
//
//    val pgsds = new PGSimpleDataSource()
//    pgsds.setServerName(host)
//    pgsds.setPortNumber(port)
//    pgsds.setDatabaseName(dbName)
//    pgsds.setUser(user)
//    pgsds.setPassword(pass)
//    pgsds
//  }
//
//  def testInSchema(schema: String)(f: => Unit) {
//    iud("CREATE SCHEMA %s;" format PGIdent.quote(schema))
//    try {
//      f
//    }
//    finally{
//      iud("DROP SCHEMA %s CASCADE;" format PGIdent.quote(schema))
//    }
//  }
//
//  def prepareSchemaQuery(schema: Option[String], st: Statement) =
//    schema match {
//      case Some(s) =>
//        st.execute("SET search_path TO %s;" format PGIdent.quote(s))
//      case _ =>
//    }
//
//  def qry[T](body: String)(f: ResultSet => T)(implicit schema: Option[String] = None): T = {
//    try {
//      val con = pgds.getConnection();
//      try {
//        val st = con.createStatement()
//        try {
//          prepareSchemaQuery(schema, st)
//          val rS = st.executeQuery(body)
//          try {
//            f(rS)
//          }
//          finally rS.close()
//        }
//        finally st.close()
//      }
//      finally con.close()
//    }
//  }
//
//  def iud(body: String)(implicit schema: Option[String] = None) {
//    try {
//      val con = pgds.getConnection();
//      try {
//        val st = con.createStatement()
//        try {
//          prepareSchemaQuery(schema, st)
//          st.execute(body)
//        }
//        finally st.close()
//      }
//      finally con.close()
//    }
//  }
//}
