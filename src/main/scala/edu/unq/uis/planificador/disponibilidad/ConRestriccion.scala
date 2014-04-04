package edu.unq.uis.planificador.disponibilidad

import edu.unq.uis.planificador.Restriccion

/**
 * Created by faloi on 4/4/14.
 */
case class ConRestriccion(restriccion: Restriccion) extends Disponibilidad {
  override val razon = restriccion.razon
}
