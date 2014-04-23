package edu.unq.uis.planificador.domain.disponibilidad

import com.github.nscala_time.time.Imports._
import edu.unq.uis.planificador.domain.calendar.DiaDeSemana

trait UbicableEnDia {
  implicit def dateToDate(date : DateTime) : DateTimeExtended = new DateTimeExtended(date)

  def fecha: DateTime
  def esDia(dia : DateTime): Boolean = dia == fecha

  def fechaUserFriendly = s"${DiaDeSemana.fromFecha(fecha).nombre} ${fecha.toString("dd/MM")}"
}

object DateTimeHelper {
  implicit def dateToDate(date : DateTime) : DateTimeExtended = new DateTimeExtended(date)
}


class DateTimeExtended(value : DateTime) {
  def ==(another : DateTime) = another.toLocalDate.equals(value.toLocalDate)
}
