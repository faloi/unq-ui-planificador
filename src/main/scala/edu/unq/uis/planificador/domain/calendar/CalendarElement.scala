package edu.unq.uis.planificador.domain.calendar

import edu.unq.uis.planificador.domain.disponibilidad.{NoDisponible, UbicableEnDia, Disponibilidad}
import scala.collection.mutable.ListBuffer

case class CalendarElement(disponibilidad: Disponibilidad, calendarSpace: UbicableEnDia with ConRangoHorario){


}

object CalendarElement {
  val noDisponible : Seq[CalendarElement]= ListBuffer(new CalendarElement(NoDisponible, TrivialCalendarSpace))

}