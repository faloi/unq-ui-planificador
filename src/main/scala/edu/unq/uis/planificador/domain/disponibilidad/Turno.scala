package edu.unq.uis.planificador.domain.disponibilidad

import com.github.nscala_time.time.Imports._
import edu.unq.uis.planificador.domain.builders.TurnoBuilder

case class Turno(fecha: DateTime, horario: Interval) extends UbicableEnDia {
}

object Turno {
  def el(fecha: String): TurnoBuilder = new TurnoBuilder(new DateTime(fecha))
  def el(fecha: DateTime): TurnoBuilder = new TurnoBuilder(fecha)
}