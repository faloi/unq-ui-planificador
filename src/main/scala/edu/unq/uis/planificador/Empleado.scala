package edu.unq.uis.planificador
import com.github.nscala_time.time.Imports

class Empleado {
  var disponibilidades : Map[Int, RecurrentInterval] = Map()
  var restricciones : Seq[Restriccion] = Seq.empty[Restriccion]

  def agregarRestriccion(restriccion: Restriccion) =
    this.restricciones = this.restricciones :+ restriccion

  def estaDisponibleLos(interval: RecurrentInterval): Unit =
    this.disponibilidades = this.disponibilidades + (interval.diaDeSemana -> interval)

  def estaDisponiblePara(time: Imports.DateTime): Boolean =
    disponibilidades.values.exists { _.hayPara(time)} && !restricciones.exists { _.hayPara(time)}

}
