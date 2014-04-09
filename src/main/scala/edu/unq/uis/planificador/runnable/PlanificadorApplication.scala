package edu.unq.uis.planificador.runnable

import org.uqbar.arena.Application
import edu.unq.uis.planificador.dependencyInjection.DevEnvironment
import edu.unq.uis.planificador.ui.empleado.ListadoEmpleadosWindow
import edu.unq.uis.planificador.domain.Empleado
import edu.unq.uis.planificador.domain.builders.RecurrentCalendarSpaceBuilder._

object PlanificadorApplication extends Application with App with DevEnvironment {
  override def createMainWindow() = new ListadoEmpleadosWindow(this)

  createEmpleadosFixture
  start()

  def createEmpleadosFixture {
    val pedro = new Empleado("Pedro", "Picapiedras", "123134")
    pedro disponibleLos (Lunes de 14 a 19)
    pedro disponibleLos (Martes de 16 a 20)
    empleadoHome.create(pedro)
  }
}