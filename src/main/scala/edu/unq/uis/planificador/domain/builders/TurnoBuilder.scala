package edu.unq.uis.planificador.domain.builders

import org.joda.time.DateTime
import edu.unq.uis.planificador.domain.timeHelpers.TimeInterval
import edu.unq.uis.planificador.domain.disponibilidad.Turno

/**
 * Created by faloi on 3/26/14.
 */
class TurnoBuilder(dia: DateTime) {
  var inicio = 0
  var fin = 0

  def de(unInicio: Int): TurnoBuilder = {
    this.inicio = unInicio
    this
  }

  def a(unFin: Int): Turno = {
    this.fin = unFin
    new Turno(dia, TimeInterval.create(inicio, fin))
  }
}