// dependencia de la clase
package org.ntic.entregable
import com.typesafe.config.{Config, ConfigFactory}

//  de typesafe config en build.sbt
object FlightsLoaderConfig {
  /**
   * This object is used to load the configuration file
   */
    // carga el fichero de configuracion
  val config: Config = ConfigFactory.load()
  // obtengo la ruta del fichero csv
  val filePath: String = config.getString("flightsLoader.filePath")
  // obtiene el valor hasHeaders que esta a true
  val hasHeaders: Boolean = config.getBoolean("flightsLoader.hasHeaders")
  // obtiene el tamaño de los headers que es 13
  val headersLength: Int = config.getInt("flightsLoader.headersLength")
  // obtiene el valor del delimitador ;
  val delimiter: String = config.getString("flightsLoader.delimiter")
  // obtiene la ruta del salida para los objetos
  val outputDir: String = config.getString("flightsLoader.outputDir")

  val headers: List[String] = config.getStringList("flightsLoader.headers").toArray.map(x => x.asInstanceOf[String]).toList
  val columnIndexMap: Map[String, Int] = headers.map(x => (x, headers.indexOf(x))).toMap
  val filteredOrigin: List[String] = config.getStringList("flightsLoader.filteredOrigin").toArray.map(x => x.asInstanceOf[String]).toList
}
