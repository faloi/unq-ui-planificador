package edu.unq.uis.planificador.domain

import org.joda.time.DateTime
import org.uqbar.commons.model
import scala.collection.JavaConverters._

case class Planificacion(dia: DateTime, empleadoHome: model.Home[Empleado]) {
  def empleados: Seq[Empleado] = empleadoHome.allInstances().asScala
    .filter( (e: Empleado) => !e.asignacionesPara(this.dia).isEmpty)

  def sePlanifico = !this.empleados.isEmpty
}
