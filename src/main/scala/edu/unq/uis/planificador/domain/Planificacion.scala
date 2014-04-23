package edu.unq.uis.planificador.domain

import org.joda.time.DateTime
import scala.collection.JavaConverters._
import org.uqbar.commons.model.{ObservableUtils, Entity}
import edu.unq.uis.planificador.dependencyInjection.DevEnvironment
import org.uqbar.commons.utils.Observable

@Observable
case class Planificacion(fecha: DateTime) extends Entity with DevEnvironment {
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
