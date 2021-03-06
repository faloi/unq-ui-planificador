package edu.unq.uis.planificador.domain.calendar

import com.github.nscala_time.time.Imports._

trait ConRangoHorario {
  def rango: Interval

  def contains(otroRango: Interval): Boolean = rango.contains(otroRango)

  def entrada = {
    rango.getStart.hourOfDay.get
  }

  def salida = {
    rango.getEnd.hourOfDay.get
  }
}
