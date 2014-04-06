package edu.unq.uis.planificador

import com.github.nscala_time.time.Imports._
import edu.unq.uis.planificador.builders.TurnoBuilder

/**
 * Created by faloi on 3/26/14.
 */
case class Turno(dia: DateTime, horario: Interval) {
}

object Turno {
  def el(fecha: String): TurnoBuilder = new TurnoBuilder(new DateTime(fecha))
}

