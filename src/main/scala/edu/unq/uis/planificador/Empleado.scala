package edu.unq.uis.planificador

import org.uqbar.commons.utils.Observable

@Observable
class Empleado {
  var nombre: String = null
  var apellido: String = null
  var legajo: String = null

  var disponibilidades : Map[Int, RecurrentInterval] = Map()
  var restricciones : Seq[Restriccion] = Seq.empty[Restriccion]

  def agregarRestriccion(restriccion: Restriccion) =
    this.restricciones = this.restricciones :+ restriccion

  def disponibleLos(interval: RecurrentInterval) =
    this.disponibilidades = this.disponibilidades + (interval.diaDeSemana -> interval)

  def puedeTrabajar(turno: Turno): Boolean = {
    disponibilidades.values.exists { _.hayPara(turno)} && !restricciones.exists { _.hayPara(turno)}
  }
}

