import sbt._
import Keys._

object BuildSettings {
  val commonSettings =
    Default.settings ++ Seq(
      organization := "hr.element.pgscala"
    )

//  ---------------------------------------------------------------------------

  val bsPGScalaUtil = commonSettings ++ Seq(
    name    := "PGScala-Util",
    version := "0.2.5",

    unmanagedSourceDirectories in Compile <<= (javaSource in Compile)( _ :: Nil),
    unmanagedSourceDirectories in Test    <<= (scalaSource in Test   )( _ :: Nil),

    javacOptions := Seq("-deprecation", "-encoding", "UTF-8", "-source", "1.5", "-target", "1.5"),
    compileOrder := CompileOrder.JavaThenScala,
    autoScalaLibrary := false,
    crossPaths := false
  )

  val bsPGScalaConverters = commonSettings ++ Seq(
    name    := "PGScala-Converters",
    version := "0.1.0"
  )

  val bsPGScala = commonSettings ++ Seq(
    name    := "PGScala",
    version := "0.7.2"
  )

  val bsPGScalaPGPool = commonSettings ++ Seq(
    name    := "PGScala-PGPool",
    version := "0.1.5"
  )
}

//  ---------------------------------------------------------------------------

object Publications {
  val pgscalaUtil       = "hr.element.pgscala" %  "pgscala-util"       % "0.2.5"
  val pgscalaConverters = "hr.element.pgscala" %% "pgscala-converters" % "0.1.0"
  val pgscala           = "hr.element.pgscala" %% "pgscala"            % "0.7.2"
  val pgpool            = "hr.element.pgscala" %% "pgscala-pgpool"     % "0.1.5"
}

//  ---------------------------------------------------------------------------

object Dependencies {
  import Publications._

  val jodaTime = Seq(
    "org.joda" % "joda-convert" % "1.1",
    "joda-time" % "joda-time" % "2.0"
  )

  val iorc = "hr.element.etb" %% "iorc" % "0.0.21"

  val postgres = "postgresql" % "postgresql" % "9.1-901.jdbc4"

  val c3p0 = "c3p0" % "c3p0" % "0.9.1.2"

  val configrity = (scalaVersion: String) => {
    val sV = scalaVersion match {
      case "2.9.0" => "2.9.0-1"
      case x => x
    }

    "org.streum" % ("configrity_" + sV) % "0.9.0" % "test"
  }

  val scalaTest = "org.scalatest" %% "scalatest" % "1.6.1" % "test"
}

//  ---------------------------------------------------------------------------

import Implicits._

object ProjectDeps {
  import Dependencies._
  import Publications._

  val depsPGScalaUtil = libDeps(
    //test
    postgres % "test",
    configrity,
    scalaTest
  )

  val depsPGScalaConverters = libDeps(
    jodaTime,

    //test
    pgscalaUtil % "test",
    postgres % "test",
    configrity,
    scalaTest
  )

  val depsPGScala = libDeps(
    pgscalaUtil,
    pgscalaConverters,

    iorc,
    postgres,

    //test
    configrity,
    scalaTest
  )

  val depsPGScalaPGPool = libDeps(
    pgscala,
    c3p0,

    //test
    configrity,
    scalaTest
  )
}

//  ---------------------------------------------------------------------------

object PGScalaBuild extends Build {
  import BuildSettings._
  import ProjectDeps._

  lazy val pgscalaUtil = Project(
    "Util",
    file("util"),
    settings = bsPGScalaUtil :+ depsPGScalaUtil
  )

  lazy val pgscalaConverters = Project(
    "Converters",
    file("converters"),
    settings = bsPGScalaConverters :+ depsPGScalaConverters
  ) // dependsOn(pgscalaUtil % "test")

  lazy val pgscala = Project(
    "PGScala",
    file("pgscala"),
    settings = bsPGScala :+ depsPGScala
  ) // dependsOn(pgscalaUtil, pgscalaConverters)

  lazy val pgscalaPGPool = Project(
    "PGPool",
    file("pgpool"),
    settings = bsPGScalaPGPool :+ depsPGScalaPGPool
  ) // dependsOn(pgscala)
}
