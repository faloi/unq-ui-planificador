package edu.unq.uis.planificador.domain

import edu.unq.uis.planificador.dependencyInjection.DevEnvironment
import org.joda.time.DateTime
import org.uqbar.commons.model.{Entity, ObservableUtils}
import org.uqbar.commons.utils.Observable

import scala.collection.JavaConverters._

case class TurnoEmpleado(nombre: String, inicio: Int, fin: Int) extends Serializable {
  def this() = this("", 0, 0)
}

@Observable
case class Planificacion(fecha: DateTime) extends Entity with DevEnvironment {
  def asignacionDe(unEmpleado: Empleado) = {
    unEmpleado.asignacionPara(fecha).get
  }

  def turnos = empleados.map(it => {
    val asignacion = asignacionDe(it).calendarSpace
    TurnoEmpleado(it.nombreCompleto, asignacion.entrada, asignacion.salida)
  })

  def empleados: Seq[Empleado] = empleadoHome
    .allInstances()
    .asScala
    .filter((e: Empleado) => e.asignacionPara(this.fecha).isDefined)

  def sePlanifico = !this.empleados.isEmpty

  def estado: String = if (this.sePlanifico) "Planificada" else "Sin Planificar"

  def quitarAsignacionA(unEmpleado: Empleado) = {
    unEmpleado.borrarAsignacionConFecha(this.fecha)
    ObservableUtils.firePropertyChanged(this, "empleados", empleados)
  }
}
