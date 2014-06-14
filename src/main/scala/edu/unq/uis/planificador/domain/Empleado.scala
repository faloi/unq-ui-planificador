package edu.unq.uis.planificador.domain

import edu.unq.uis.planificador.domain.disponibilidad._
import edu.unq.uis.planificador.domain.calendar._
import com.github.nscala_time.time.Imports._
import org.uqbar.commons.model.{UserException, ObservableUtils, Entity}
import org.uqbar.commons.utils.Observable
import edu.unq.uis.planificador.domain.patterns.Chain
import Chain._
import edu.unq.uis.planificador.domain.calendar.CalendarSpace
import edu.unq.uis.planificador.domain.calendar.AllDayCalendarSpace
import edu.unq.uis.planificador.domain.calendar.RecurrentCalendarSpace

@Observable
class Empleado(var nombre: String = null, var apellido: String = null, var legajo: String = null) extends Entity {
  @transient val estados: DisponibilidadContainer = new DisponibilidadContainer
  def this() = this(null,null,null)
  val chain =
    (input: Seq[CalendarElement]) =>
      (turno: Turno) =>
        input.filter(it => it.calendarSpace.esDia(turno.fecha) && it.calendarSpace.contains(turno.horario)) match {
          case Nil => Left(turno)
          case x: Seq[CalendarElement] => Right(x.head)
        }

  def jerarquiaDeDisponibilidades =
    chain(estados de Restriccion) +> chain(estados de Asignacion) +> chain(estados de Disponible) +> chain(estados de NoDisponible)

  def disponibilidadPara(turno: Turno): CalendarElement = jerarquiaDeDisponibilidades(turno) match {
    case Right(x) => x
    case Left(_) => throw new UserException("Jerarquía de disponibilidades mal definida")
  }

  def isDisponibleLos(turno: Turno): Disponibilidad = disponibilidadPara(turno).disponibilidad

  def restriccionEl(fecha: String) =
    this.estados += CalendarElement(Restriccion, AllDayCalendarSpace(new DateTime(fecha)))

  def asignar(turno: Turno) {
    isDisponibleLos(turno) match {
      case Restriccion => throw new UserException("No puede asignarse un turno para un día con restricción")
      case _ => this.estados += CalendarElement(Asignacion, new CalendarSpace(turno.fecha, turno.horario))
    }
  }

  def disponibilidades =
    (estados de Disponible)
      .map {
      element => element.calendarSpace match {
        case calendar@RecurrentCalendarSpace(_, _, _) => calendar
      }
    }
      .sortBy(_.diaDeSemana)

  def nombreCompleto = s"$nombre $apellido"

  def diasDisponible = disponibilidades.map(_.diaDeSemana.nombreCorto).mkString(", ")

  def restriccionesUserFriendly = restricciones.map(_.calendarSpace.fechaUserFriendly).mkString(" - ")


  def restricciones: Seq[CalendarElement] = {
    (estados de Restriccion)
  }

  def borrarDisponibilidad(unaDisponibilidad: RecurrentCalendarSpace) = {
    estados -= unaDisponibilidad
    fireDisponibilidadesChanged()
  }

  def borrarRestriccion(restriccion: CalendarElement) = {
    estados -= restriccion
  }

  def borrarAsignacionConFecha(unaFecha: DateTime) = {
    (estados de Asignacion).find(_.calendarSpace.esDia(unaFecha)) match {
      case Some(asignacion) => estados -= asignacion; fireDisponibilidadesChanged()
      case None =>
    }
  }

  def disponibleLos(intervals: RecurrentCalendarSpace*) = {
    estados ++= intervals.map {
      it => CalendarElement(Disponible, it)
    }
    fireDisponibilidadesChanged()
  }

  private def fireDisponibilidadesChanged() {
    ObservableUtils.firePropertyChanged(this, "disponibilidades", this.disponibilidades)
  }

  def asignacionPara(fecha: DateTime): Option[CalendarElement] = {
    (estados de Asignacion).find((c: CalendarElement) => c.calendarSpace.esDia(fecha))
  }
}
