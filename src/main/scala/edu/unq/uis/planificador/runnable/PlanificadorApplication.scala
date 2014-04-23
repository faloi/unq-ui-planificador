package edu.unq.uis.planificador.runnable

import org.uqbar.arena.Application
import edu.unq.uis.planificador.dependencyInjection.DevEnvironment
import edu.unq.uis.planificador.domain.Empleado
import edu.unq.uis.planificador.domain.builders.RecurrentCalendarSpaceBuilder._
import edu.unq.uis.planificador.domain.calendar.DiaDeSemana.{Martes, Lunes}
import edu.unq.uis.planificador.ui.MainMenu
import edu.unq.uis.planificador.domain.disponibilidad.Turno

object PlanificadorApplication extends Application with App with DevEnvironment {
  override def createMainWindow() = new MainMenu(this)

  createEmpleadosFixture
  start()

  def createEmpleadosFixture {
    val pedro = new Empleado("Pedro", "Picapiedras", "123134")
    pedro disponibleLos (Lunes de 14 a 19)
    pedro disponibleLos (Martes de 16 a 20)
    pedro asignar (Turno el "2014-04-21" de 14 a 18)
    empleadoHome.create(pedro)

    val juana = new Empleado("juana", "Picapiedras", "8795468")
    juana disponibleLos (Lunes de 9 a 22)
    juana disponibleLos (Martes de 16 a 20)
    empleadoHome.create(juana)
  }
}