import sbt._
import Keys._

object BuildSettings {
  import Default._

//  ---------------------------------------------------------------------------

  val bsPGJavaUtil = javaSettings ++ Seq(
    name    := "pgjava-util",
    version := "0.2.6"
  )

  val bsPGJavaConverters = javaSettings ++ Seq(
    name    := "pgjava-converters",
    version := "0.0.1"
  )

  val bsPGScalaConverters = scalaSettings ++ Seq(
    name    := "pgscala-converters",
    version := "0.2.2"
  )

  val bsPGScala = scalaSettings ++ Seq(
    name    := "pgscala",
    version := "0.7.4"
  )

  val bsPGScalaPool = scalaSettings ++ Seq(
    name    := "pgscala-pool",
    version := "0.1.7"
  )
}

//  ---------------------------------------------------------------------------

object Publications {
  val pgjavaUtil       = "hr.element.pgscala"  %  "pgjava-util"        % "0.2.6"
  val pgjavaConverters = "hr.element.pgscala"  %  "pgjava-converters"  % "0.0.1"
  val pgscalaConverters = "hr.element.pgscala" %% "pgscala-converters" % "0.2.2"
  val pgscala           = "hr.element.pgscala" %% "pgscala"            % "0.7.4"
  val pgpool            = "hr.element.pgscala" %% "pgscala-pool"       % "0.1.7"
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

  val depsPGJavaUtil = libDeps(
    //test
    postgres % "test",
    configrity,
    scalaTest
  )

  val depsPGJavaConverters = libDeps(
    jodaTime,

    //test
//    pgjavaUtil % "test",
    postgres % "test",
    configrity,
    scalaTest
  )

  val depsPGScalaConverters = libDeps(
    jodaTime,

    //test
//    pgjavaUtil % "test",
    postgres % "test",
    configrity,
    scalaTest
  )

  val depsPGScala = libDeps(
//     pgscalaUtil,
//     pgscalaConverters,

    iorc,
    postgres,

    //test
    configrity,
    scalaTest
  )

  val depsPGScalaPool = libDeps(
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

  lazy val pgjavaUtil = Project(
    "pgjava-util",
    file("pgjava-util"),
    settings = bsPGJavaUtil :+ depsPGJavaUtil
  )

  lazy val pgjavaConverters = Project(
    "pgjava-converters",
    file("pgjava-converters"),
    settings = bsPGJavaConverters :+ depsPGJavaConverters
  ) dependsOn(pgjavaUtil % "test")

  lazy val pgscalaConverters = Project(
    "pgscala-converters",
    file("pgscala-converters"),
    settings = bsPGScalaConverters :+ depsPGScalaConverters
  ) dependsOn(pgjavaConverters, pgjavaUtil % "test")

  lazy val pgscala = Project(
    "pgscala",
    file("pgscala"),
    settings = bsPGScala :+ depsPGScala
  ) dependsOn(pgjavaUtil, pgscalaConverters)

  lazy val pgscalaPool = Project(
    "pgscala-pool",
    file("pgscala-pool"),
    settings = bsPGScalaPool :+ depsPGScalaPool
  ) dependsOn(pgscala)
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
      organization := "hr.element.pgscala",
      crossScalaVersions := Seq("2.9.1", "2.9.0-1", "2.9.0"),
      scalaVersion <<= (crossScalaVersions) { versions => versions.head },
      scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "UTF-8", "-optimise"), // , "-Yrepl-sync"
      unmanagedSourceDirectories in Compile <<= (scalaSource in Compile)( _ :: Nil),
      unmanagedSourceDirectories in Test    <<= (scalaSource in Test   )( _ :: Nil)
    )

  val javaSettings =
    scalaSettings ++ Seq(
      autoScalaLibrary := false,
      crossPaths := false,
      javacOptions := Seq("-deprecation", "-encoding", "UTF-8", "-source", "1.5", "-target", "1.5"),      
      unmanagedSourceDirectories in Compile <<= (javaSource in Compile)( _ :: Nil)
    )    
}

//  ---------------------------------------------------------------------------

object Implicits {
  implicit def depToFunSeq(m: ModuleID) = Seq((_: String) => m)
  implicit def depFunToSeq(fm: String => ModuleID) = Seq(fm)
  implicit def depSeqToFun(mA: Seq[ModuleID]) = mA.map(m => ((_: String) => m))

  def libDeps(deps: (Seq[String => ModuleID])*) = {
    libraryDependencies <++= scalaVersion( sV =>
      for (depSeq <- deps; dep <- depSeq) yield dep(sV)
    )
  }
}
