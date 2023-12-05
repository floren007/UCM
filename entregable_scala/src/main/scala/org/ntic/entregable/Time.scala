package org.ntic.entregable

case class Time(hours: Int, minutes: Int) extends Ordered[Time] {
  require(hours >= 0 && hours <= 24, "Validar que las horas esten entre 0 y 24")
  require(minutes >= 0 && minutes <= 59, "Validar que los minutos  esten entre 0 y 59")
  val asMinutes = hours*60 + minutes
  override lazy val toString: String = f"$hours%02d:$minutes%02d"

  def minus(that: Time): Int =
    this.asMinutes - that.asMinutes

  def -(that: Time): Int =
    minus(that)

  override def compare(that: Time): Int =
    this - that
}

object Time {

  val totalMinutesInADay = 1440

  def fromString(timeStr: String): Time = {
    val formatted: String = f"${timeStr.toInt}%04d"
    // para poder extraer las horas utilizo la funcion substring y como se que las horas son las 2 primeros
    val hours: Int = formatted.substring(0, 2).toInt

    // extraigo las horas al igual que lo minutos mediente la funcion substring
    val minutes: Int = formatted.substring(2, 4).toInt

    // devuelvo el objeto Time con los parametros hours y minutes
    Time(hours, minutes)
  }

  def fromMinutes(minutes: Int): Time = {
    val normalized = minutes / totalMinutesInADay
    Time(normalized / 60, normalized % 60)
  }
}