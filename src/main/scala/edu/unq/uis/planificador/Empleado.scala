package edu.unq.uis.planificador
import com.github.nscala_time.time.Imports
import com.github.nscala_time.time.Imports._
import org.joda.time.Instant

class Empleado {
  var disponibilidades : Map[Int, RecurrentInterval] = Map()
  var restricciones : Seq[Restriccion] = Seq.empty[Restriccion]

  def agregarRestriccion(restriccion: Restriccion) =
    this.restricciones = this.restricciones :+ restriccion

  def estaDisponibleLos(interval: RecurrentInterval): Unit =
    this.disponibilidades = this.disponibilidades + (interval.diaDeSemana -> interval)

  def estaDisponiblePara(time: Imports.DateTime): Boolean = {
    val horaSinFecha = time.toLocalTime.toDateTime(new Instant(0))
    val turno = new Turno(time, new Interval(horaSinFecha, horaSinFecha + 1.hours))
    disponibilidades.values.exists { _.hayPara(turno)} && !restricciones.exists { _.hayPara(turno)}
  }
}
