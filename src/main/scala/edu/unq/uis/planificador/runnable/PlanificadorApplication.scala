package edu.unq.uis.planificador.runnable

import org.uqbar.arena.Application
import edu.unq.uis.planificador.ui.ListadoEmpleadosWindow
import edu.unq.uis.planificador.dependencyInjection.environments.DevEnvironment
import edu.unq.uis.planificador.Empleado

object PlanificadorApplication extends Application with App with DevEnvironment {
  override def createMainWindow() = new ListadoEmpleadosWindow(this)

  val empleado: Empleado = new Empleado
  empleado.nombre = "Pedro"
  empleado.apellido = "Picapiedras"
  empleado.legajo = "123134"
  empleadoHome.create(empleado)

  start()
}