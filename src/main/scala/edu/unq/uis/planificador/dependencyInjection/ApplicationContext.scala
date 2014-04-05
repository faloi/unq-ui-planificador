package edu.unq.uis.planificador.dependencyInjection

object ApplicationContext
  extends EmpleadosHomeComponentImpl
  with CalendarElementHomeComponentImpl {

  override val empleadoHome: ApplicationContext.EmpleadoHome = new EmpleadoHomeImpl
  override val calendarElementHome: ApplicationContext.CalendarElementHome= new CalendarElementHomeImpl
}
