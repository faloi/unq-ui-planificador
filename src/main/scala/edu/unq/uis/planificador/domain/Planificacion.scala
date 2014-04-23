package edu.unq.uis.planificador.domain

import org.joda.time.DateTime
import org.uqbar.commons.model
import scala.collection.JavaConverters._
import org.uqbar.commons.model.Entity
import edu.unq.uis.planificador.dependencyInjection.DevEnvironment
import org.uqbar.commons.utils.Observable

@Observable
case class Planificacion(fecha: DateTime) extends Entity with DevEnvironment {
  def empleados: Seq[Empleado] = empleadoHome.allInstances().asScala
    .filter( (e: Empleado) => !e.asignacionesPara(this.fecha).isEmpty)

  def sePlanifico = !this.empleados.isEmpty

  def estado : String = if (this.sePlanifico) "Planificada" else "Sin Planificar"
}
