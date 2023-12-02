package org.ntic.entregable

import scala.math.Numeric.Implicits.infixNumericOps

case class Flight(flDate: String,
                  origin: AirportInfo,
                  dest: AirportInfo,
                  scheduledDepTime: Time,
                  scheduledArrTime: Time,
                  depDelay: Double,
                  arrDelay: Double)  extends Ordered[Flight]{

  // se define lazy para vez que entre al camapo flightDate, no lo calcule 2 veces,
  // el campo es de tipo org.ntic.entregable.FlightDate
  // devuelve 3 parametros, dia, mes y año
  lazy val flightDate: FlightDate = FlightDate.fromString(flDate)

  // devuelve la hora de salida en minutos del vuelo teniendo en cuenta los retrasos
  lazy val actualDepTime: Time = Time.fromMinutes((scheduledDepTime.minutes + depDelay).toInt)

  // devuelve la hora de llegada del vuelo teniendo en cuenta los minutos de retraso
  lazy val  actualArrTime: Time = Time.fromMinutes((scheduledArrTime.minutes + arrDelay).toInt)

  // devuelve si el vuelo esta retrasado en booleano
  val  isDelayed: Boolean = depDelay != 0 || arrDelay != 0

  // Aqui lo que hacemos es comparar el metodo actualArrTime
  override def compare(that: Flight): Int =  this.actualArrTime.compare(that.actualArrTime)

}


object Flight {
  def fromString(flightInfoRow: String): Flight = {
    // para sacar el nombre de cada columna lo que hago es separar las columnas por su delimitador
    val columns: Array[String] = flightInfoRow.split(FlightsLoaderConfig.delimiter)
    def getColValue(colName: String): String = {
      /**
       * This function is used to get the value of a column from the array of String generated from the row of the csv
       * and stored in the variable `columns`.
       * @param colName: String name of the column
       * @return String value of the column
       */
      // TODO: Implementar esta función
      //  Pista: usa el mapa columnIndexMap de la clase FlightsLoaderConfig,
      //    tiene como clave el nombre de la columna y como valor el índice de la columna
      //  Pista: puedes usar el método apply de la clase Array para obtener el valor de la columna
      //    del array de Strings `columns` usando el índice
      columns(FlightsLoaderConfig.columnIndexMap(colName))
    }
    val oriAirport = AirportInfo(
      airportId = getColValue("ORIGIN_AIRPORT_ID").toLong,
      code = getColValue("ORIGIN"),
      cityName = getColValue("ORIGIN_CITY_NAME"),
      stateAbr = getColValue("ORIGIN_STATE_ABR"))
    val destAirport = AirportInfo(
      airportId = getColValue("DEST_AIRPORT_ID").toLong,
      code = getColValue("DEST"),
      cityName = getColValue("DEST_CITY_NAME"),
      stateAbr = getColValue("DEST_STATE_ABR"))
    Flight(
      flDate = getColValue("FL_DATE"),
      origin = oriAirport,
      dest = destAirport,
      scheduledDepTime = Time.fromString(getColValue("DEP_TIME")),
      scheduledArrTime = Time.fromString(getColValue("ARR_TIME")),
      depDelay = getColValue("DEP_DELAY").toDouble,
      arrDelay = getColValue("ARR_DELAY").toDouble
    )
  }
}
