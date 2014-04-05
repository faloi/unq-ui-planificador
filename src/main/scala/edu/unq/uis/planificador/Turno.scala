package edu.unq.uis.planificador

import com.github.nscala_time.time.Imports._
import edu.unq.uis.planificador.builders.TurnoBuilder
import edu.unq.uis.planificador.disponibilidad.UbicableEnDia

case class Turno(dia : DateTime, horario : Interval) extends UbicableEnDia {
}

object Turno {
  def el(fecha : String) : TurnoBuilder = new TurnoBuilder(new DateTime(fecha))
}