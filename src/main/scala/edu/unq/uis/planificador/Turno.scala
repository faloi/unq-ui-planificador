package edu.unq.uis.planificador

import com.github.nscala_time.time.Imports._

/**
 * Created by faloi on 3/26/14.
 */
case class Turno(dia : DateTime, horario : Interval) {
  def esDia(unaFecha : DateTime) = unaFecha.toLocalDate.equals(dia.toLocalDate)
}