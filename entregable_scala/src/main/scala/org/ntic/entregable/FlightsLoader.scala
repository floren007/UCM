package org.ntic.entregable
import FileUtils.loadFile

import java.io._
import org.ntic.entregable.Flight

import scala.math

object FlightsLoader extends App {

  def writeObject(flights: Seq[Flight], outputFilePath: String): Unit = {
    val out = new ObjectOutputStream(new FileOutputStream(outputFilePath))
    out.writeObject(flights)
    out.close()
  }
  // carga el fichero
  val flights = loadFile(FlightsLoaderConfig.filePath)

  // hago un loop sobre los origenes filtrados
  for (origin <- FlightsLoaderConfig.filteredOrigin) {
    // filtro los vuelos por origen
    val filteredFligths: Seq[Flight] = flights.filter(flight => flight.origin.code == origin)
    // ordena los vuelos por tiempo
    implicit val flightOrdering: Ordering[Flight] = Ordering.by(_.actualArrTime)
    // filtro y ordeno los vuelos retrasados y por origen
    val delayedFlights: Seq[Flight] = filteredFligths.filter(_.isDelayed).sorted
    // filtro los vuelos no retrasados
    val notDelayedFlights: Seq[Flight] = filteredFligths.filterNot(_.isDelayed)
    // escribo el path del fichero de salida para los vuelos no retrasados
    val flightObjPath: String = s"${FlightsLoaderConfig.outputDir}/$origin.obj"
    println(flightObjPath)
    // escribo el path de fichero de salida para los vuelos que se han retrasado
    val delayedFlightsObj: String = s"${FlightsLoaderConfig.outputDir}/${origin}_delayed.obj"

    // utilizo la funcion writeObject para escribir los ficheros en el directorio de salida
    // los vuelos no retrasados
    writeObject(notDelayedFlights, flightObjPath)

    // utilizo la funcion writeObject para escribir los ficheros en el directorio de salida
    // los vuelos retrasados
    writeObject(delayedFlights, delayedFlightsObj)
  }
}
