package edu.unq.uis.planificador.domain.builders

import edu.unq.uis.planificador.domain.calendar.{RecurrentCalendarSpace, DiaDeSemana}

case class RecurrentCalendarSpaceBuilder(day: DiaDeSemana) {
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
  implicit def diaDeSemanaToBuilder(dia: DiaDeSemana) = new RecurrentCalendarSpaceBuilder(dia)
}