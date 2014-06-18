package edu.unq.uis.planificador.domain.calendar

import com.github.nscala_time.time.Imports
import com.github.nscala_time.time.TypeImports.{DateTime, Interval}
import edu.unq.uis.planificador.domain.disponibilidad.UbicableEnDia
import edu.unq.uis.planificador.domain.timeHelpers.TimeInterval
import org.uqbar.commons.model.UserException
import org.uqbar.commons.utils.Observable

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

@Observable
case class RecurrentCalendarSpace(var inicio: Int = 9, var fin: Int = 18, var diaDeSemana: DiaDeSemana = DiaDeSemana.Lunes)
  extends UbicableEnDia with ConRangoHorario with Serializable {

  def this() = this(9, 18, DiaDeSemana.Lunes)

  validar()
  @transient val rango: Interval = TimeInterval.create(inicio, fin)

  override def esDia(dia: DateTime): Boolean = dia.getDayOfWeek == diaDeSemana.value

  override def fecha: Imports.DateTime = null //TODO: esto no voy a usarlo, no se cual seria la manera correcta

  def validar() = {
    if (inicio < 0) throw new UserException("Desde debe ser mayor o igual a 0")
    if (fin > 23) throw new UserException("Workaholic! No se puede trabajar después de la hora 24!")
    if (inicio > fin) throw new UserException("No tiene sentido terminar de trabajar antes de empezar :)")
  }
}