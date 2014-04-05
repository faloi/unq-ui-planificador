package edu.unq.uis.planificador.calendar

import edu.unq.uis.planificador.disponibilidad.Disponibilidad
import edu.unq.uis.planificador.Empleado

case class CalendarElement(empleado: Empleado, disponibilidad: Disponibilidad, calendarSpace: CalendarSpace) {

}
