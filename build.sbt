Common.settings
name := """spring-mm"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  cache,
  ws,
  filters
)
routesGenerator := InjectedRoutesGenerator
unmanagedJars in Compile += file("lib/bcpkix-jdk15on-1.52.jar")
unmanagedJars in Compile += file("lib/bcprov-jdk15on-1.52.jar")
unmanagedJars in Compile += file("lib/regkassen-loyalcraft-1.0.0.jar")
unmanagedJars in Compile += file("lib/regkassen-verification-common-1.0.10.jar")
libraryDependencies ++= Common.commonDependencies