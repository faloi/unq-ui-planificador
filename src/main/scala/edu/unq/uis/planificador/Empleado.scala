package edu.unq.uis.planificador
import com.github.nscala_time.time.Imports
import com.github.nscala_time.time.Imports._
import org.joda.time.Instant
import edu.unq.uis.planificador.timeHelpers.TimeInterval

class Empleado {
  var disponibilidades : Map[Int, RecurrentInterval] = Map()
  var restricciones : Seq[Restriccion] = Seq.empty[Restriccion]

  def agregarRestriccion(restriccion: Restriccion) =
    this.restricciones = this.restricciones :+ restriccion

  def disponibleLos(interval: RecurrentInterval) =
    this.disponibilidades = this.disponibilidades + (interval.diaDeSemana -> interval)

  def puedeTrabajar(time: Imports.DateTime): Boolean = {
    val turno = new Turno(time, TimeInterval.create(time, time + 1.hours))
    disponibilidades.values.exists { _.hayPara(turno)} && !restricciones.exists { _.hayPara(turno)}
  }
}

