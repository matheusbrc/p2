name := """p2"""
organization := "com.p2"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.6"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test
libraryDependencies += "org.postgresql" % "postgresql" % "9.4.1211"
libraryDependencies += jdbc

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.p2.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.p2.binders._"
