package edu.unq.uis.planificador.domain.calendar

import edu.unq.uis.planificador.BaseSpec
import edu.unq.uis.planificador.domain.calendar.DiaDeSemana.Lunes
import edu.unq.uis.planificador.domain.builders.RecurrentCalendarSpaceBuilder._
import edu.unq.uis.planificador.domain.disponibilidad.Asignacion

class CalendarElementSpec extends BaseSpec {

  var calendarElement: CalendarElement = _

  before {
    this.calendarElement = new CalendarElement(Asignacion, Lunes de 9 a 18)
  }

  "CalendarElement" should "saber si incluye un bloque horario" in {
    calendarElement.incluye(10) should be(true)
  }

  it should "saber si no incluye un bloque horario" in {
    calendarElement.incluye(19) should be(false)
  }

}
