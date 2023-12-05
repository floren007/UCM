package org.ntic.entregable

import com.sun.media.sound.InvalidFormatException

import java.lang.AssertionError

case class FlightDate(day: Int,
                      month: Int,
                      year: Int) {

  // lazy que sea perezoso
  // uso interpolator para escribir variables dentro de un string
  // pongo las variables en orden
  override lazy val toString: String = f"$day%02d/$month%02d/$year%02d"
}

object FlightDate {
  def fromString(date: String): FlightDate = {
    /**
     * This function is used to convert a string to a org.ntic.entregable.FlightDate
     * @param date: String
     * @return org.ntic.entregable.FlightDate
     */
    date.split(" ").head.split("/").map(x => x.toInt).toList match {
      // he tenido que cambiar la posicion month por day, ya que en el csv primero va el mes
      // despues el dia y ultimo el año
      case month :: day :: year :: Nil =>
        println(s"El dia: $day, el mes: $month, el año: $year")
        // utilizo un try catch, para que agarre el error sin interrumpir la ejecucion
        // compruebo que las fechas son correctas con los assert
        // hago un return de FlightDate con la fecha como parametros
        try{
          assert(day >= 1 && day <= 31, "Dias validos ente 1 y 31")
          assert(month >= 1 && month <= 12, "Meses validos entre 1 y 12")
          assert(year >= 1987, "El año debe ser mayor de 1987")
          FlightDate(day, month, year)
        }catch {
          case _ => throw new AssertionError(s"Este date es invalido: $date")
        }
    }
  }
}
