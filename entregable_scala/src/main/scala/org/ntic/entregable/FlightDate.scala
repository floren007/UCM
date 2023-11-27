package org.ntic.entregable

import com.sun.media.sound.InvalidFormatException

case class FlightDate(day: Int,
                      month: Int,
                      year: Int) {

  // TODO: Sobreescribir el método toString para que devuelva la fecha en formato dd/mm/yy. Al sobreescribirlo haz que
  //  sea peresozo y sólo se calcule cuando se llame a toString, pero además que se calcule una única vez de forma que
  //  si se llama varias veces a toString no se vuelva a calcular.
  //  Pista: usa interpolator `f` (ver https://docs.scala-lang.org/overviews/core/string-interpolation.html)
  //  Pista: conjuga lazy y la inmutabilidad
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
      case day :: month :: year :: Nil => // TODO: Comprueba que el día, mes y año son correctos y si lo son devuelve
                                          //    un objeto de org.ntic.entregable.FlightDate con esos valores.
                                          //    Si no son correctos asegúrate que el programa lance lanza una excepción
                                          //    de tipo `AssertionError` con el mensaje adecuado.
                                          //  Pista: usa assert (ver https://www.scala-lang.org/api/2.13.12/scala/Predef$.html)
                                          //  Pista: Ten en cuenta que según la documentación del dataset, el año mínimo es 1987
        assert(day >= 1 && day <= 31, "Dias validos ente 1 y 31")
        assert(month >= 1 && month <= 12, "Meses validos entre 1 y 12")
        assert(year >= 1987, "El año debe ser mayor de 1987")
        FlightDate(day, month, year)
      case _ => throw new InvalidFormatException(s"Este date es invalido: $date")
    }
  }
}
