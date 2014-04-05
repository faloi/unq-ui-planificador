package edu.unq.uis.planificador.dependencyInjection

import edu.unq.uis.planificador.{Turno, Empleado}

trait EmpleadoHomeComponent {
  val empleadoHome: EmpleadoHome
  trait EmpleadoHome extends Home[Empleado]
}

trait TurnoHomeComponent {
  val empleadoHome: TurnoHome
  trait TurnoHome extends Home[Turno]
}