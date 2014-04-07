package edu.unq.uis.planificador.domain.builders

import org.joda.time.DateTimeConstants
import edu.unq.uis.planificador.domain.calendar.{RecurrentCalendarSpace, RecurrentInterval}

case class RecurrentCalendarSpaceBuilder(day: Int) {
  var inicio: Int = 0
  var fin: Int = 0

  def a(unFin: Int): RecurrentCalendarSpace = {
    this.fin = unFin
    new RecurrentCalendarSpace(this.inicio, this.fin, this.day)
  }

  def de(unInicio: Int): RecurrentCalendarSpaceBuilder = {
    this.inicio = unInicio
    this
  }
}

object RecurrentCalendarSpaceBuilder {
  val Lunes: RecurrentCalendarSpaceBuilder = RecurrentCalendarSpaceBuilder(DateTimeConstants.MONDAY)
  val Martes: RecurrentCalendarSpaceBuilder = RecurrentCalendarSpaceBuilder(DateTimeConstants.TUESDAY)
  val Miercoles: RecurrentCalendarSpaceBuilder = RecurrentCalendarSpaceBuilder(DateTimeConstants.WEDNESDAY)
  val Jueves: RecurrentCalendarSpaceBuilder = RecurrentCalendarSpaceBuilder(DateTimeConstants.THURSDAY)
  val Viernes: RecurrentCalendarSpaceBuilder = RecurrentCalendarSpaceBuilder(DateTimeConstants.FRIDAY)
  val Sabado: RecurrentCalendarSpaceBuilder = RecurrentCalendarSpaceBuilder(DateTimeConstants.SATURDAY)
  val Domingo: RecurrentCalendarSpaceBuilder = RecurrentCalendarSpaceBuilder(DateTimeConstants.SUNDAY)
}