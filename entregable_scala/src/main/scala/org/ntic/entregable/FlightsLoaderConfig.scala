package org.ntic.entregable  // TODO: Para que esta clase funcione, debes añadir la dependencia
import com.typesafe.config.{Config, ConfigFactory}

//  de typesafe config en build.sbt
object FlightsLoaderConfig {
  /**
   * This object is used to load the configuration file
   */
  val config: Config = ConfigFactory.load() // TODO: Carga el fichero de configuración de la aplicación y obtén la configuración para el objeto flightsLoader
  val filePath: String = config.getString("flightsLoader.filePath")  // TODO: Obtén el valor de filePath del fichero de configuración
  val hasHeaders: Boolean = config.getBoolean("flightsLoader.hasHeaders") // TODO: Obtén el valor de hasHeaders del fichero de configuración
  val headersLength: Int = config.getInt("flightsLoader.headersLength")  // TODO: Obtén el valor de headersLength del fichero de configuración
  val delimiter: String = config.getString("flightsLoader.delimiter") // TODO: Obtén el valor de delimiter del fichero de configuración
  val outputDir: String = config.getString("flightsLoader.outputDir") // TODO: Obtén el valor de outputDir del fichero de configuración
  val headers: List[String] = config.getStringList("flightsLoader.headers").toArray.map(x => x.asInstanceOf[String]).toList
  val columnIndexMap: Map[String, Int] = headers.map(x => (x, headers.indexOf(x))).toMap
  val filteredOrigin: List[String] = config.getStringList("flightsLoader.filteredOrigin").toArray.map(x => x.asInstanceOf[String]).toList
}
