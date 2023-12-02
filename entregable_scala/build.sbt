import sbt.Keys.libraryDependencies
import sbtassembly.AssemblyPlugin.defaultShellScript

import scala.collection.immutable.Seq

ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "2.13.12"
ThisBuild / assemblyPrependShellScript := Some(defaultShellScript)



// nombre del paquete
val mainClassName = "org.ntic.entregable.FlightsLoader"


lazy val root = (project in file("."))
  .settings(
    name := "entregable_scala", // nombre del proyecto
    Compile / run / mainClass := Some(mainClassName), // clase principal del proyecto para la etapa `run` de `Compile`
    Compile / packageBin / mainClass := Some(mainClassName), // clase principal del proyecto para la etapa `packageBin` de `Compile`
    assembly / mainClass := Some(mainClassName), // clase principal del proyecto para el ensamblado de `assembly`
    assembly / assemblyJarName := "flights_loader.jar",

    libraryDependencies ++= Seq(
    // dependencias de la librería
    "com.typesafe" % "config" % "1.4.1",
    "org.scalatest" %% "scalatest" % "3.2.17" % Test,
    "org.scala-lang" %% "toolkit-test" % "0.1.7" % Test
    )
  )
