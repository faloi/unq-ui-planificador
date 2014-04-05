package edu.unq.uis.planificador

case class Empleado(nombre: String = "Natalia", apellido : String = "Natalia") {
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

