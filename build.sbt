// Turn this project into a Scala.js project by importing these settings
enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)

name := "Example"

version := "0.1-SNAPSHOT"

scalaVersion := "2.12.1"

scalaJSUseMainModuleInitializer := true

testFrameworks += new TestFramework("utest.runner.Framework")

libraryDependencies ++= Seq(
  "com.wbillingsley" %%% "handy" % "0.9.0-SNAPSHOT",
  "org.scala-lang.modules" %%% "scala-parser-combinators" % "1.1.1",
  "com.lihaoyi" %%% "upickle" % "0.6.6",
  "org.scala-js" %%% "scalajs-dom" % "0.9.2",
  "com.lihaoyi" %%% "utest" % "0.4.5" % "test"
)

npmDependencies in Compile += "hjson" -> "3.1.1"

webpackBundlingMode := BundlingMode.LibraryOnly()
emitSourceMaps := false