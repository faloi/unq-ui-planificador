package edu.unq.uis.planificador.dependencyInjection

import org.scalatest._
import org.scalatest.matchers.ShouldMatchers
import edu.unq.uis.planificador.disponibilidad._
import edu.unq.uis.planificador.calendar.{CalendarSpace, CalendarElement}
import edu.unq.uis.planificador.Empleado
import edu.unq.uis.planificador.timeHelpers.TimeInterval
import com.github.nscala_time.time.TypeImports.DateTime

class CalendarHomeSpec extends FlatSpec with ShouldMatchers with BeforeAndAfter with CalendarElementHomeComponentImpl{

  //TODO: poner esto en un test envoronment o algo así
  override val calendarElementHome: CalendarElementHome = new CalendarElementHomeImpl
  val juan : Empleado = new Empleado("Juan", "Sanchez", "0")

  before {
    this.calendarElementHome.clear()
    this.calendarElementHome.save(new CalendarElement(juan, Restriccion, new CalendarSpace(new DateTime("2014-03-24"),  TimeInterval.create(10, 12))))
    this.calendarElementHome.save(new CalendarElement(juan, Restriccion, new CalendarSpace(new DateTime("2014-03-25"),  TimeInterval.create(10, 12))))
    this.calendarElementHome.save(new CalendarElement(new Empleado("Sofia", "Li", "2"), Restriccion, new CalendarSpace(new DateTime("2014-03-24"),  TimeInterval.create(10, 12))))
  }

  "CalendarElementHome" should "encontrar las restricciones de un Empleado" in {
    this.calendarElementHome.findRestriccionesFor(juan).length should be (2)
  }
}
