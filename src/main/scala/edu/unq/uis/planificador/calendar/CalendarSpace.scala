package edu.unq.uis.planificador.calendar

import edu.unq.uis.planificador.disponibilidad.UbicableEnDia
import com.github.nscala_time.time.TypeImports.DateTime
import com.github.nscala_time.time.TypeImports.Interval
import edu.unq.uis.planificador.timeHelpers.TimeInterval
import com.github.nscala_time.time.Imports

case class CalendarSpace(fecha : DateTime, rango : Interval) extends UbicableEnDia with ConRangoHorario

trait FullRango extends ConRangoHorario {
  def rango: Interval = null
  override def contains(i : Interval) = true
}

case class AllDayCalendarSpace(fecha: DateTime) extends UbicableEnDia with FullRango

object TrivialCalendarSpace extends UbicableEnDia with ConRangoHorario with FullRango {
  override def esDia(dia : DateTime): Boolean = true
  override def fecha: DateTime = null
}

case class RecurrentCalendarSpace(inicio: Int, fin: Int, diaDeSemana: Int) extends UbicableEnDia with ConRangoHorario {
  val rango :Interval = TimeInterval.create(inicio, fin)

  override def esDia(dia : DateTime): Boolean = dia.getDayOfWeek == diaDeSemana

  override def fecha: Imports.DateTime = null //TODO: esto no voy a usarlo, no se cual seria la manera correcta
}