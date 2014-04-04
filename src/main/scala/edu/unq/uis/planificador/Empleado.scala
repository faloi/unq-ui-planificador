package edu.unq.uis.planificador

import edu.unq.uis.planificador.disponibilidad.{NoDisponible, ConRestriccion, Disponible, Disponibilidad}

class Empleado {
  var disponibilidades : Map[Int, RecurrentInterval] = Map()
  var restricciones : Seq[Restriccion] = Seq.empty[Restriccion]

  def agregarRestriccion(restriccion: Restriccion) =
    this.restricciones = this.restricciones :+ restriccion

  def disponibleLos(interval: RecurrentInterval) =
    this.disponibilidades = this.disponibilidades + (interval.diaDeSemana -> interval)

  def puedeTrabajar(turno: Turno): Boolean = {
    disponibilidadPara(turno) match {
      case Disponible => true
      case _ => false
    }
  }

  def disponibilidadPara(turno : Turno) : Disponibilidad = {
    if (restricciones.exists { _.hayPara(turno)})
      return ConRestriccion

    if (disponibilidades.values.exists { _.hayPara(turno)})
      Disponible
    else
      NoDisponible
  }
}