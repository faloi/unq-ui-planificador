package edu.unq.uis.planificador

import edu.unq.uis.planificador.calendar.CalendarElement
import edu.unq.uis.planificador.disponibilidad.{NoDisponible, Disponibilidad, Disponible}
import edu.unq.uis.planificador.dependencyInjection.ApplicationContext


case class Empleado(nombre: String = "Natalia", apellido : String = "Natalia", legajo : String = "0") {
  var disponibilidades : Map[Int, RecurrentInterval] = Map()
  var restricciones : Seq[Restriccion] = Seq.empty[Restriccion]

  def findRestricciones() : Seq[CalendarElement] = ApplicationContext.calendarElementHome.findRestriccionesFor(this)

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
    val restriccion = restricciones.find _ == turno

//    if (restriccion.isDefined) return ConRestriccion(restriccion.get)
//
    if (disponibilidades.values.exists { _.hayPara(turno)})
      Disponible
    else
      NoDisponible
  }
}