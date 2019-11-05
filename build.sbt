// Turn this project into a Scala.js project by importing these settings
enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)

name := "Example"

version := "0.2-SNAPSHOT"

scalaVersion := "2.13.1"

resolvers += "jitpack" at "https://jitpack.io"

scalaJSUseMainModuleInitializer := true

testFrameworks += new TestFramework("utest.runner.Framework")

libraryDependencies ++= Seq(
  "com.github.wbillingsley.handy" %%% "handy" % "master-SNAPSHOT",
  "com.github.wbillingsley.veautiful" %%% "veautiful" % "master-SNAPSHOT",
  "com.github.wbillingsley.veautiful" %%% "scatter" % "master-SNAPSHOT",
  "com.github.wbillingsley.veautiful" %%% "veautiful-templates" % "master-SNAPSHOT",
  "com.lihaoyi" %%% "upickle" % "0.8.0",
  "org.scala-js" %%% "scalajs-dom" % "0.9.7",
  "org.scalatest" %%% "scalatest" % "3.0.8" % "test"
)

npmDependencies in Compile += "hjson" -> "3.1.1"

webpackBundlingMode := BundlingMode.LibraryOnly()
emitSourceMaps := false