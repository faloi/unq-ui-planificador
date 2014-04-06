package edu.unq.uis.planificador.runnable

import org.uqbar.arena.Application
import edu.unq.uis.planificador.ui.ListadoEmpleadosWindow
import edu.unq.uis.planificador.dependencyInjection.DevEnvironment

object PlanificadorApplication extends Application with App with DevEnvironment {
  override def createMainWindow() = new ListadoEmpleadosWindow(this)

  start()
}