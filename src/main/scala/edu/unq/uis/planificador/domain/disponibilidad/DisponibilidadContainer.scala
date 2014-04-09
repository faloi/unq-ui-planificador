package edu.unq.uis.planificador.domain.disponibilidad

import scala.collection.mutable
import edu.unq.uis.planificador.domain.calendar.{DiaDeSemana, RecurrentCalendarSpace, CalendarElement}
import org.joda.time.DateTime

class DisponibilidadContainer {

  implicit class EnhancedMap[A, B, C](m: mutable.Map[(A, B), C]) {
    def update(a: A, b: B, c: C) {
      m((a, b)) = c
    }
  }

  val disponibilidades: mutable.Map[(Disponibilidad, Either[DateTime, DiaDeSemana]), CalendarElement] =
    mutable.HashMap.empty[(Disponibilidad, Either[DateTime, DiaDeSemana]), CalendarElement]

  /**
   * @param estado : Disponible, Asignacion, Restriccion o NoDisponible
   * @return Todas las disponibilidades correspondientes al estado
   */
  def de(estado: Disponibilidad): Seq[CalendarElement] = estado match {
    case NoDisponible => CalendarElement.noDisponible
    case _ => disponibilidades.values.filter {
      case CalendarElement(`estado`, _) => true
      case _ => false
    }.toSeq
  }

  /**
   * Agrega un CalendarElement al mapa de disponibilidades
   */
  def +=(ce: CalendarElement) = ce match {
    case CalendarElement(Disponible, space: RecurrentCalendarSpace) =>
      disponibilidades += ((Disponible, Right(space.diaDeSemana)) -> CalendarElement(Disponible, space))
    case e: CalendarElement => disponibilidades += ((e.disponibilidad, Left(e.calendarSpace.fecha)) -> e)
  }
}
