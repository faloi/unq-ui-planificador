package edu.unq.uis.planificador.dependencyInjection

import edu.unq.uis.planificador.{Turno, Empleado}
import edu.unq.uis.planificador.calendar.CalendarElement

trait EmpleadoHomeComponent {
  val empleadoHome: EmpleadoHome
  trait EmpleadoHome extends Home[Empleado]
}

trait CalendarElementHomeComponent {
  val calendarElementHome: CalendarElementHome

  trait CalendarElementHome extends Home[CalendarElement] {
    def findRestriccionesFor(empleado : Empleado) : Seq[CalendarElement]

  }
}