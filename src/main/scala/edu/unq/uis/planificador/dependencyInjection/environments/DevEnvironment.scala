package edu.unq.uis.planificador.dependencyInjection.environments

import edu.unq.uis.planificador.dependencyInjection.EmpleadosHomeComponentImpl

trait DevEnvironment extends EmpleadosHomeComponentImpl {
  override val empleadoHome: EmpleadosHome = new EmpleadosHome
}