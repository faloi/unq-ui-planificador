package edu.unq.uis.planificador.builders

import org.joda.time.DateTime
import edu.unq.uis.planificador.Turno
import edu.unq.uis.planificador.timeHelpers.TimeInterval

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