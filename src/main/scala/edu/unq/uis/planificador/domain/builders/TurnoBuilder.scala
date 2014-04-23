package edu.unq.uis.planificador.domain.builders

import org.joda.time.DateTime
import edu.unq.uis.planificador.domain.timeHelpers.TimeInterval
import edu.unq.uis.planificador.domain.disponibilidad.Turno
import org.uqbar.commons.model.UserException

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
    validar()
    new Turno(dia, TimeInterval.create(inicio, fin))
  }

  def validar() = {
    if (inicio < 0) throw new UserException("Desde debe ser mayor o igual a 0")
    if (fin > 23) throw new UserException("Workaholic! No se puede trabajar después de la hora 24!")
    if (inicio > fin) throw new UserException("No tiene sentido terminar de trabajar antes de empezar :)")
  }
}