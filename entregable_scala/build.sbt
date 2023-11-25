import sbt.Keys.libraryDependencies
import sbtassembly.AssemblyPlugin.defaultShellScript

import scala.collection.immutable.Seq

ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "2.13.12"
ThisBuild / assemblyPrependShellScript := Some(defaultShellScript)




val mainClassName = "org.ntic.entregable.FlightsLoader"


lazy val root = (project in file("."))
  .settings(
    name := "entregable_scala", // TODO: establece el nombre del proyecto. Tiene que ser el mismo que el nombre que le has dado al proyecto en IntelliJ
    Compile / run / mainClass := Some(mainClassName),// TODO: define la clase principal del proyecto para la etapa `run` de `Compile`
    Compile / packageBin / mainClass := Some(mainClassName), // TODO: define la clase principal del proyecto para la etapa `packageBin` de `Compile`
    assembly / mainClass := Some(mainClassName), // TODO: define la clase principal del proyecto para el ensamblado de `assembly`
    assembly / assemblyJarName := "flights_loader.jar", // TODO: define `flights_loader.jar` como el nombre del jar que se genera en la etapa assembly

    libraryDependencies ++= Seq(
    // TODO añade la dependencia de la librería de configuración de Typesafe
    "com.typesafe" % "config" % "1.4.1",
    "org.scalatest" %% "scalatest" % "3.2.17" % Test,
    "org.scala-lang" %% "toolkit-test" % "0.1.7" % Test
    )
  )
