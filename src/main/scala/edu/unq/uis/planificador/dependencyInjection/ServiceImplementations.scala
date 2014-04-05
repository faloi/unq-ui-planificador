package edu.unq.uis.planificador.dependencyInjection

import edu.unq.uis.planificador.Empleado

trait EmpleadosHomeComponentImpl extends EmpleadoHomeComponent {
  class EmpleadosHome extends EmpleadoHome with HomeImpl[Empleado]
}