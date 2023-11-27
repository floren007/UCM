package org.ntic.entregable

import scala.math.Numeric.Implicits.infixNumericOps

case class Flight(flDate: String,
                  origin: AirportInfo,
                  dest: AirportInfo,
                  scheduledDepTime: Time,
                  scheduledArrTime: Time,
                  depDelay: Double,
                  arrDelay: Double)  {

  val flightDate: FlightDate = FlightDate.fromString(flDate)  // TODO: define el campo flightDate (perezoso e inmutable) de tipo org.ntic.entregable.FlightDate a partir del campo flDate
                                    //    que es de tipo String.
                                    //    Pista: usa el método fromString de org.ntic.entregable.FlightDate

  val actualDepTime: Time = Time.fromMinutes((scheduledDepTime.minutes + depDelay).toInt) //  TODO: define el campo actualDepTime (perezoso e inmutable) de tipo org.ntic.entregable.Time a partir de los campos
                                //    Ten en cuenta que este campo debe representar la hora de salida real del vuelo, esto quiere decir que debe
                                //    tener en cuenta el retraso, el campo depDelay representa el retraso en minutos, puede ser negativo y es Double.
                                //    Pista: usa el método fromMinutes de org.ntic.entregable.Time

  val  actualArrTime: Time = Time.fromMinutes((scheduledArrTime.minutes + arrDelay).toInt) //  TODO: define el campo actualArrTime (perezoso e inmutable) de tipo org.ntic.entregable.Time a partir de los campos
                                //    scheduledArrTime y arrDelay.
                                //    Ten en cuenta que este campo debe representar la hora de llegada real del vuelo, esto quiere decir que debe
                                //    tener en cuenta el retraso, el campo arrDelay representa el retraso en minutos, puede ser negativo y es Double.
                                //    Pista: usa el método fromMinutes de org.ntic.entregable.Time

  val  isDelayed: Boolean = depDelay != 0 || arrDelay != 0  // TODO: define el atirbuto inmutable `isDelayed` de tipo Boolean que indica si el vuelo está retrasado o no.
                               //  Pista: un vuelo está retrasado si el campo depDelay o el campo arrDelay son distintos de 0

  // TODO: la clase org.ntic.entregable.Flight debe poderse ordenar por el campo actualArrTime, para ello la clase debe implementar el trait Ordered
  //  Pista: para implementar el trait Ordered debes implementar el método compare
  //  Pista: el método compare debe devolver un Int
  //  Pista: el método compare debe comparar el atributo actualArrTime del objeto que invoque a la función con el
  //    atributo actualArrTime de la clase que se le pasa como parámetro
  //  Pista: actualArrTime es de tipo org.ntic.entregable.Time
  // Implementación del método compare del trait Ordered
  def compare(that: Flight): Int = {
    this.actualArrTime.compare(that.actualArrTime)
  }
}


object Flight {


  def fromString(flightInfoRow: String): Flight = {
    val columns: Array[String] = flightInfoRow.split(FlightsLoaderConfig.delimiter)  //  TODO: genera un array de Strings a partir de la variable flightInfoRow
                                      //    Pista: usa el método split de la clase String
                                      //    Pista: el delimitador está en la configuración
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
