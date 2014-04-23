package edu.unq.uis.planificador.domain.calendar

import edu.unq.uis.planificador.domain.disponibilidad.{NoDisponible, UbicableEnDia, Disponibilidad}
import scala.collection.mutable.ListBuffer
import edu.unq.uis.planificador.domain.timeHelpers.TimeInterval

case class CalendarElement(disponibilidad: Disponibilidad, calendarSpace: UbicableEnDia with ConRangoHorario){
  def incluye(bloque: Int): Boolean = calendarSpace.contains(TimeInterval.create(bloque, bloque))
}

object CalendarElement {
  val noDisponible : Seq[CalendarElement]= ListBuffer(new CalendarElement(NoDisponible, TrivialCalendarSpace))

}