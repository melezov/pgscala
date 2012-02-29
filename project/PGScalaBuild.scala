import sbt._
import Keys._

object BuildSettings {
  import Default._

//  ###########################################################################

  val bsUtil = javaSettings ++ Seq(
    name    := "pgscala-util"
  , version := "0.3.0-SNAPSHOT"
  , initialCommands := "import org.pgscala.util._"
  )
  
//  ===========================================================================

  val proxy = TaskKey[Unit]("proxy", "Compiles jasmin proxy methods (return type overloading for Scala)")
  val proxyTask = proxy <<= (baseDirectory, scalaVersion) map { (bD, sV) =>
    val src = bD / ".."  / "converters-scala" /
      "src" / "main" / "jasmin" / "org" / "pgscala" / "converters" / "PGNullableConverter.j"

    val dst = bD / ".."  / "converters-scala" /
      "target" / ("scala-%s" format sV) / "classes"

    jasmin.Main.main(Array(src.absolutePath, "-d", dst.absolutePath))
  }

  val bsBuilder = scalaSettings ++ Seq(
    name    := "builder"
  , version := "0.0.0-SNAPSHOT"
  , proxyTask
  )

  //  ---------------------------------------------------------------------------

/*
  val bsConvertersJava = javaSettings ++ Seq(
    name    := "pgscala-converters-java"
  , version := "0.1.0-SNAPSHOT"
  , initialCommands := "import org.pgscala.converters._"
  )

  val bsPGScalaConverters = scalaSettings ++ Seq(
    name    := "pgscala-converters"
  , version := "0.3.0-6-pjc"
  , initialCommands := "import org.pgscala.converters._"
  , unmanagedSourceDirectories in Compile <<= (scalaSource in Compile, javaSource in Compile)( _ :: _ :: Nil)
  )

  val bsPGScala = scalaSettings ++ Seq(
    name    := "pgscala"
  , version := "0.7.4-6-pjc"
  )

  val bsPGScalaPool = scalaSettings ++ Seq(
    name    := "pgscala-pool"
  , version := "0.1.7-3"
  )
*/
}

//  ---------------------------------------------------------------------------

object Publications {
  val pgscalaUtil       = "org.pgscala" %  "pgscala-util"        % "0.1.0-SNAPSHOT"
/*
  val pgjavaConverters  = "org.pgscala" %  "pgjava-converters"  % "0.3.0-6-pjc"
  val pgscalaConverters = "org.pgscala" %% "pgscala-converters" % "0.3.0-6-pjc"
  val pgscala           = "org.pgscala" %% "pgscala"            % "0.7.4-6-pjc"
  val pgpool            = "org.pgscala" %% "pgscala-pool"       % "0.1.7-2"
*/
}

//  ---------------------------------------------------------------------------

object Dependencies {
  import Publications._
/*
  val jodaTime = Seq(
    "org.joda" % "joda-convert" % "1.2",
    "joda-time" % "joda-time" % "2.1"
  )
*/
  val scalaIo = "com.github.scala-incubator.io" % "scala-io-file_2.9.1" % "0.3.0"

/*
  val iorc = "org.etb" %% "iorc" % "0.0.21"

  val postgres = "postgresql" % "postgresql" % "9.1-901.jdbc4"

  val c3p0 = "c3p0" % "c3p0" % "0.9.1.2"

  val configrity = "org.streum" % "configrity-core_2.9.1" % "0.10.0"

  val scalaTest = "org.scalatest" %% "scalatest" % "1.7.1" % "test"
*/  
}

//  ---------------------------------------------------------------------------

import Implicits._

object ProjectDeps {
  import Dependencies._
  import Publications._

  val depsUtil = libDeps()

  val depsBuilder = libDeps(
    scalaIo
  )

/*
  val depsPGJavaConverters = libDeps(
    jodaTime
  )

  val depsPGScalaConverters = libDeps(
    jodaTime
  , pgjavaConverters
  , pgjavaUtil % "test"
  , postgres % "test"
  , configrity
  , scalaTest
  )

  val depsPGScala = libDeps(
    pgjavaUtil
  , pgscalaConverters
  , iorc
  , postgres
  , configrity
  , scalaTest
  )

  val depsPGScalaPool = libDeps(
    pgscala
  , c3p0

    //test
  , configrity
  , scalaTest
  )
*/
}

//  ###########################################################################

object PGScalaBuild extends Build {
  import BuildSettings._
  import ProjectDeps._

  lazy val util = Project(
    "util",
    file("util"),
    settings = bsUtil :+ depsUtil
  )

  lazy val builder = Project(
    "builder",
    file("builder"),
    settings = bsBuilder :+ depsBuilder
  )

/*
  lazy val pgjavaConverters = Project(
    "pgjava-converters",
    file("pgjava-converters"),
    settings = bsPGJavaConverters :+ depsPGJavaConverters
  )// dependsOn(pgjavaUtil % "test")

  lazy val pgscalaConverters = Project(
    "pgscala-converters",
    file("pgscala-converters"),
    settings = bsPGScalaConverters :+ depsPGScalaConverters
  )// dependsOn(pgjavaConverters, pgjavaUtil % "test")

  lazy val pgscala = Project(
    "pgscala",
    file("pgscala"),
    settings = bsPGScala :+ depsPGScala
  )// dependsOn(pgjavaUtil, pgscalaConverters)

  lazy val pgscalaPool = Project(
    "pgscala-pool",
    file("pgscala-pool"),
    settings = bsPGScalaPool :+ depsPGScalaPool
  )// dependsOn(pgscala)
*/  
}

//  ---------------------------------------------------------------------------

object Repositories {
  val ElementNexus     = "Element Nexus"     at "http://maven.element.hr/nexus/content/groups/public/"
  val ElementReleases  = "Element Releases"  at "http://maven.element.hr/nexus/content/repositories/releases/"
  val ElementSnapshots = "Element Snapshots" at "http://maven.element.hr/nexus/content/repositories/snapshots/"
}

//  ---------------------------------------------------------------------------

object Resolvers {
  import Repositories._

  val settings = Seq(
    resolvers := Seq(ElementNexus, ElementReleases, ElementSnapshots),
    externalResolvers <<= resolvers map { rs =>
      Resolver.withDefaultResolvers(rs, mavenCentral = false, scalaTools = false)
    }
  )
}

//  ---------------------------------------------------------------------------

object Publishing {
  import Repositories._

  val settings = Seq(
    publishTo <<= (version) { version => Some(
      if (version.endsWith("SNAPSHOT")) ElementSnapshots else ElementReleases
    )},
    credentials += Credentials(Path.userHome / ".publish" / "element.credentials"),
    publishArtifact in (Compile, packageDoc) := false
  )
}

//  ---------------------------------------------------------------------------

object Default {
  val scalaSettings =
    Defaults.defaultSettings ++
    Resolvers.settings ++
    Publishing.settings ++ Seq(
      organization := "org.pgscala"
    , crossScalaVersions := Seq("2.9.1", "2.9.0-1", "2.9.0")
    , scalaVersion <<= (crossScalaVersions) { versions => versions.head }
    , scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "UTF-8", "-optimise")
    , unmanagedSourceDirectories in Compile <<= (scalaSource in Compile)( _ :: Nil)
    , unmanagedSourceDirectories in Test    <<= (scalaSource in Test   )( _ :: Nil)
    )

  val javaSettings =
    scalaSettings ++ Seq(
      autoScalaLibrary := false
    , crossPaths := false
    , javacOptions := Seq("-deprecation", "-encoding", "UTF-8", "-source", "1.5", "-target", "1.5")
    , unmanagedSourceDirectories in Compile <<= (javaSource in Compile)( _ :: Nil)
    )
}

//  ---------------------------------------------------------------------------

object Implicits {
  implicit def depToSeq(m: ModuleID) = Seq(m)

  def libDeps(deps: (Seq[ModuleID])*) =
    libraryDependencies ++= deps.flatten
}
