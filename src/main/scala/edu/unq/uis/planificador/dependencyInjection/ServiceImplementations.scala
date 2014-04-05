package edu.unq.uis.planificador.dependencyInjection

import edu.unq.uis.planificador.Empleado
import edu.unq.uis.planificador.calendar.CalendarElement

trait EmpleadosHomeComponentImpl extends EmpleadoHomeComponent {
  class EmpleadoHomeImpl extends EmpleadoHome with HomeImpl[Empleado]
}

trait CalendarElementHomeComponentImpl extends CalendarElementHomeComponent{
  class CalendarElementHomeImpl extends CalendarElementHome with HomeImpl[CalendarElement]{
    override def findRestriccionesFor(empleado: Empleado): Seq[CalendarElement] =
      this.findAll().filter {
        case CalendarElement(Empleado(_, _, empleado.legajo), _, _) => true
        case _ => false
      }
  }
}