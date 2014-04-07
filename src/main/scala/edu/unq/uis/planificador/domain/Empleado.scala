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
import edu.unq.uis.planificador.exceptions.UnexpectedBusinessException

@Observable
class Empleado(var nombre: String = null, var apellido: String = null, var legajo: String = null) extends Entity {

  val estados: DisponibilidadContainer = new DisponibilidadContainer

  val chain =
    (input: Seq[CalendarElement]) =>
      (turno: Turno) =>
        input.filter(it => it.calendarSpace.esDia(turno.fecha) && it.calendarSpace.contains(turno.horario)) match {
          case Nil => Left(turno)
          case x: Seq[CalendarElement] => Right(x.head)
        }

  def jerarquiaDeDisponibilidades = 
    chain (estados de Restriccion) +> chain (estados de Asignacion) +> chain (estados de Disponible) +> chain (estados de NoDisponible)


  def disponibilidadPara(turno: Turno): CalendarElement = jerarquiaDeDisponibilidades(turno) match {
    case Right(x) => x
    case Left(_) => throw UnexpectedBusinessException("Jerarquía de disponibilidades mal definida")
  }


  def isDisponibleLos(turno: Turno): Disponibilidad = disponibilidadPara(turno).disponibilidad


  def disponibleLos(interval: RecurrentCalendarSpace) =
    estados += CalendarElement(Disponible, interval)


  def restriccionEl(fecha: String) =
    this.estados += CalendarElement(Restriccion, AllDayCalendarSpace(new DateTime(fecha)))


  def asignar(turno: Turno) =
    this.estados += CalendarElement(Asignacion, new CalendarSpace(turno.fecha, turno.horario))

}
