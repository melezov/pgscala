import sbt._
import Keys._

object BuildSettings {
  import Default._

//  ###########################################################################

  val bsUtil = javaSettings ++ Seq(
    name    := "pgscala-util"
  , version := "0.3.6-1"
  , initialCommands := "import org.pgscala.util._"
  )

  val bsIORC = scalaSettings ++ Seq(
    name    := "pgscala-iorc"
  , version := "0.1.7-1"
  , initialCommands := "import org.pgscala.iorc._"
  )

//  ===========================================================================

  val proxy = TaskKey[Unit]("proxy", "Compiles jasmin proxy methods (return type overloading for Scala)")
  val proxyTask = proxy <<= (baseDirectory, scalaVersion) map { (bD, sV) =>
    val src = bD / ".."  / "converters-scala" /
      "src" / "main" / "jasmin" / "org" / "pgscala" / "converters" / "PGNullableConverter.j"

    val dst = bD / ".."  / "converters-scala" /
      "target" / ("scala-%s" format (if (sV startsWith "2.10") "2.10" else sV)) / "classes"

    jasmin.Main.main(Array(src.absolutePath, "-d", dst.absolutePath))
  }

  val bsBuilder = scalaSettings ++ Seq(
    name    := "builder"
  , version := "0.0.0-SNAPSHOT"
  , proxyTask
  )

  //  ---------------------------------------------------------------------------

  val bsConvertersJava = javaSettings ++ Seq(
    name    := "pgscala-converters-java"
  , version := "0.2.9-1"
  , initialCommands := "import org.pgscala.converters._"
  )

  val bsConvertersScala = scalaSettings ++ Seq(
    name    := "pgscala-converters-scala"
  , version := "0.2.15-1"
  , initialCommands := "import org.pgscala.converters._"
  , unmanagedSourceDirectories in Compile <<= (scalaSource in Compile, javaSource in Compile)(_ :: _ :: Nil)
  )

  val bsPGScala = scalaSettings ++ Seq(
    name    := "pgscala"
  , version := "0.7.23-1"
  , initialCommands := "import org.pgscala._"
  )

  val bsPool = scalaSettings ++ Seq(
    name    := "pgscala-pool"
  , version := "0.2.16-1"
  , initialCommands := "import org.pgscala._"
  )
}

//  ---------------------------------------------------------------------------

object Dependencies {
  lazy val jodaTime = "joda-time" % "joda-time" % "2.2"
  lazy val jodaConvert = "org.joda" % "joda-convert" % "1.3.1"

  lazy val postgres = "postgresql" % "postgresql" % "9.2-1002.jdbc4"

  lazy val c3p0 = "com.mchange" % "c3p0" % "0.9.2.1"

  lazy val slf4j = "org.slf4j" % "slf4j-api" % "1.7.5"
  lazy val log4jOverSlf4j = "org.slf4j" % "log4j-over-slf4j" % "1.7.5"

  lazy val logback = "ch.qos.logback" % "logback-classic" % "1.0.11"
  lazy val scalaIo = (sV: String) =>
    "com.github.scala-incubator.io" % "scala-io-file_".+(sV match {
      case "2.9.2" | "2.9.3" => "2.9.2"
      case x if x startsWith "2.10" => "2.10"
      case x => "2.9.1"
    }) % "0.4.2"

  lazy val scalaTest = "org.scalatest" %% "scalatest" % "2.0.M5b"
}

//  ###########################################################################

object PGScalaBuild extends Build {
  import BuildSettings._
  import Default._
  import Dependencies._

  lazy val util = Project(
    "util"
  , file("util")
  , settings = bsUtil :+ deps(
      slf4j
    , scalaTest % "test"
    )
  )

  lazy val iorc = Project(
    "iorc"
  , file("iorc")
  , settings = bsIORC :+ deps(
      scalaTest % "test"
    )
  )

  lazy val builder = Project(
    "builder"
  , file("builder")
  , settings = bsBuilder :+ deps(
      scalaIo
    )
  )

  lazy val pgjavaConverters = Project(
    "converters-java"
  , file("converters-java")
  , settings = bsConvertersJava :+ deps(
      jodaTime
    , jodaConvert
    , scalaTest % "test"
    )
  )

  lazy val pgscalaConverters = Project(
    "converters-scala"
  , file("converters-scala")
  , settings = bsConvertersScala :+ deps(
      scalaTest % "test"
    )
  ) dependsOn(pgjavaConverters)

  lazy val pgscala = Project(
    "pgscala"
  , file("pgscala")
  , settings = bsPGScala :+ deps(
      postgres
    , slf4j
    , scalaTest % "test"
    , logback % "test"
    )
  ) dependsOn(util, iorc, pgscalaConverters)

  lazy val pgscalaPool = Project(
    "pool"
  , file("pool")
  , settings = bsPool :+ deps(
      c3p0
    , log4jOverSlf4j
    , scalaTest % "test"
    , logback % "test"
    )
  ) dependsOn(pgscala)
}

//  ---------------------------------------------------------------------------

object Repositories {
  val ElementNexus     = "Element Nexus"     at "http://repo.element.hr/nexus/content/groups/public/"
  val ElementReleases  = "Element Releases"  at "http://repo.element.hr/nexus/content/repositories/releases/"
  val ElementSnapshots = "Element Snapshots" at "http://repo.element.hr/nexus/content/repositories/snapshots/"
}

//  ---------------------------------------------------------------------------

object Resolvers {
  import Repositories._

  lazy val settings = Seq(
    resolvers := Seq(ElementNexus, ElementReleases, ElementSnapshots)
  , externalResolvers <<= resolvers map { r =>
      Resolver.withDefaultResolvers(r, mavenCentral = false)
    }
  )
}

//  ---------------------------------------------------------------------------

object Publishing {
  import Repositories._

  lazy val settings = Seq(
    publishTo <<= version { version => Some(
      if (version endsWith "SNAPSHOT") ElementSnapshots else ElementReleases
    )}
  , credentials += Credentials(Path.userHome / ".config" / "pgscala" / "nexus.config")
  , publishArtifact in (Compile, packageDoc) := false
  )
}

//  ---------------------------------------------------------------------------

object ScalaOptions {
  val scala2_8 = Seq(
    "-unchecked"
  , "-deprecation"
  , "-optimise"
  , "-encoding", "UTF-8"
  , "-Xcheckinit"
  , "-Xfatal-warnings"
  , "-Yclosure-elim"
  , "-Ydead-code"
  , "-Yinline"
  )

  val scala2_9 = Seq(
    "-Xmax-classfile-name", "72"
  )

  val scala2_9_1 = Seq(
    "-Yrepl-sync"
  , "-Xlint"
  , "-Xverify"
  , "-Ywarn-all"
  )

  val scala2_10 = Seq(
    "-feature"
  , "-language:postfixOps"
  , "-language:implicitConversions"
  , "-language:existentials"
  )

  def apply(version: String) = scala2_8 ++ (version match {
    case x if (x startsWith "2.10.")                => scala2_9 ++ scala2_9_1 ++ scala2_10
    case x if (x startsWith "2.9.") && x >= "2.9.1" => scala2_9 ++ scala2_9_1
    case x if (x startsWith "2.9.")                 => scala2_9
    case _ => Nil
  } )
}

object Default {
  //Eclipse plugin
  import com.typesafe.sbteclipse.plugin.EclipsePlugin._

  //Dependency graph plugin
  import net.virtualvoid.sbt.graph.Plugin._

  lazy val scalaSettings =
    Defaults.defaultSettings ++
    eclipseSettings ++
    graphSettings ++
    Resolvers.settings ++
    Publishing.settings ++ Seq(
      organization := "org.pgscala"

    , scalaVersion <<= crossScalaVersions(_.last)
    , crossScalaVersions := Seq(
        "2.10.1"
      )
    , scalacOptions <<= scalaVersion map ( ScalaOptions(_) )

    , javaHome := sys.env.get("JDK16_HOME").map(file(_))
    , javacOptions := Seq(
        "-deprecation"
      , "-encoding", "UTF-8"
      , "-Xlint:unchecked"
      , "-source", "1.6"
      , "-target", "1.6"
      )

    , unmanagedSourceDirectories in Compile <<= (scalaSource in Compile)(_ :: Nil)
    , unmanagedSourceDirectories in Test    <<= (scalaSource in Test   )(_ :: Nil)
    )

  lazy val javaSettings =
    scalaSettings ++ Seq(
      autoScalaLibrary := false
    , crossPaths := false
    , unmanagedSourceDirectories in Compile <<= (javaSource in Compile)(_ :: Nil)
    )

  def deps(module: String => ModuleID) =
    libraryDependencies <+= scalaVersion( sV => module(sV) )

  def deps(modules: ModuleID*) =
    libraryDependencies ++= modules
}
