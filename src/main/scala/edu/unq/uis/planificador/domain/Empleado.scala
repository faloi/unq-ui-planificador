package edu.unq.uis.planificador.domain

import edu.unq.uis.planificador.domain.disponibilidad._
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import edu.unq.uis.planificador.domain.calendar._
import com.github.nscala_time.time.Imports._
import org.uqbar.commons.model.Entity
import org.uqbar.commons.utils.Observable
import edu.unq.uis.planificador.domain.patterns.Chain
import Chain._
import edu.unq.uis.planificador.domain.calendar.CalendarSpace
import edu.unq.uis.planificador.domain.calendar.AllDayCalendarSpace
import edu.unq.uis.planificador.domain.calendar.RecurrentCalendarSpace

@Observable
class Empleado(var nombre: String = null, var apellido: String = null, var legajo: String = null) extends Entity {

  val disponibilidades: mutable.Buffer[CalendarElement] = new ListBuffer[CalendarElement]

  val disp =
    (input: Seq[CalendarElement]) =>
      (turno: Turno) =>
        input.filter(it => it.calendarSpace.esDia(turno.fecha) && it.calendarSpace.contains(turno.horario)) match {
          case Nil => Left(turno)
          case x: ListBuffer[CalendarElement] => Right(x.head)
        }

  def chain = disp(get(Restriccion)) +> disp(get(Asignacion)) +> disp(get(Disponible)) +> disp(CalendarElement.noDisponible)

  def get(estado: Disponibilidad): Seq[CalendarElement] = this.disponibilidades.filter {
    case CalendarElement(`estado`, _) => true
    case _ => false
  }

  def disponibilidadPara(turno: Turno): CalendarElement = {
    this.chain(turno) match {
      case Right(x) => x
    }
  }

  def isDisponibleLos(turno: Turno): Disponibilidad =
    disponibilidadPara(turno).disponibilidad

  //TODO: luego remover definitivamente el uso de RecurrentInterval en favor de RecurrentCalendarSpace
  def disponibleLos(interval: RecurrentInterval) = {
    this.disponibilidades --= get(Disponible).filter({
      case CalendarElement(Disponible, RecurrentCalendarSpace(_, _, interval.diaDeSemana)) => true
      case _ => false
    })

    this.disponibilidades += CalendarElement(Disponible, new RecurrentCalendarSpace(interval.inicio, interval.fin, interval.diaDeSemana))
  }

  def restriccionEl(fecha: DateTime) =
    this.disponibilidades += CalendarElement(Restriccion, AllDayCalendarSpace(fecha))

  def asignar(turno: Turno) =
    this.disponibilidades += CalendarElement(Asignacion, new CalendarSpace(turno.fecha, turno.horario))
}
