package edu.unq.uis.planificador.domain.calendar

import edu.unq.uis.planificador.domain.disponibilidad.{NoDisponible, UbicableEnDia, Disponibilidad}
import scala.collection.mutable.ListBuffer
import edu.unq.uis.planificador.domain.timeHelpers.TimeInterval
import org.uqbar.commons.utils.Observable

@Observable
case class CalendarElement(disponibilidad: Disponibilidad, calendarSpace: UbicableEnDia with ConRangoHorario){
  def incluye(bloque: Int): Boolean = calendarSpace.contains(TimeInterval.create(bloque, bloque))

  def fechaUserFriendly = calendarSpace.fechaUserFriendly
}

object CalendarElement {
  val noDisponible : Seq[CalendarElement]= ListBuffer(new CalendarElement(NoDisponible, TrivialCalendarSpace))

}