resolvers := Seq(
  "Element Nexus" at "http://repo.element.hr/nexus/content/groups/public/"
, Resolver.url("Element Nexus (Ivy)",
    url("http://repo.element.hr/nexus/content/groups/public/"))(Resolver.ivyStylePatterns)
)

externalResolvers <<= resolvers map { r =>
  Resolver.withDefaultResolvers(r, mavenCentral = false)
}

libraryDependencies += "net.sf.jasmin" % "jasmin" % "2.4"

// =======================================================================================

// +-------------------------------------------------------------------------------------+
// | SBT Eclipse (https://github.com/typesafehub/sbteclipse)                             |
// | Creates .project and .classpath files for easy Eclipse project imports              |
// |                                                                                     |
// | See also: Eclipse downloads (http://www.eclipse.org/downloads/)                     |
// | See also: Scala IDE downloads (http://download.scala-ide.org/)                      |
// +-------------------------------------------------------------------------------------+

addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "2.1.0")

// +-------------------------------------------------------------------------------------+
// | Dependency report plugin (hhttps://github.com/mslinn/dependencyReport)              |
// | Lists all jars in a nicely formatted way for easy inspection.                       |
// +-------------------------------------------------------------------------------------+

addSbtPlugin("com.micronautics" % "dependencyreport" % "0.1.1", "0.12.1")
