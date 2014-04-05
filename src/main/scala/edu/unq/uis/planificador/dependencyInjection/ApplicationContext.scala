package edu.unq.uis.planificador.dependencyInjection

object ApplicationContext extends EmpleadosHomeComponentImpl{
  override val empleadoHome: ApplicationContext.EmpleadoHome = new EmpleadosHome
}
