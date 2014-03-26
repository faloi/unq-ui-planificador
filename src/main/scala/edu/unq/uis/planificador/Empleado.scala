package edu.unq.uis.planificador
import com.github.nscala_time.time.Imports

class Empleado {
  var disponibilidades : Seq[RecurrentInterval] = Seq.empty[RecurrentInterval]

  var restricciones : Seq[Restriccion] = Seq.empty[Restriccion]


  def agregarRestriccion(restriccion: Restriccion) =
    this.restricciones = this.restricciones :+ restriccion

  def agregarDisponibilidad(interval: RecurrentInterval): Unit =
    this.disponibilidades = this.disponibilidades :+ interval


  def estaDisponiblePara(time: Imports.DateTime): Boolean =
    disponibilidades.exists { _.hayPara(time)} && !restricciones.exists { _.hayPara(time)}

}
