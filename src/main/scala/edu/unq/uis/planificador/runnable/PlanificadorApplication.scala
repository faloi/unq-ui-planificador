package edu.unq.uis.planificador.runnable

import org.uqbar.arena.Application
import edu.unq.uis.planificador.dependencyInjection.DevEnvironment
import edu.unq.uis.planificador.ui.empleado.ListadoEmpleadosWindow

object PlanificadorApplication extends Application with App with DevEnvironment {
  override def createMainWindow() = new ListadoEmpleadosWindow(this)

  start()
}