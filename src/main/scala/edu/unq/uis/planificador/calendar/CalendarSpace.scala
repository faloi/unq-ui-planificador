package edu.unq.uis.planificador.calendar

import com.github.nscala_time.time.Imports._
import edu.unq.uis.planificador.disponibilidad.UbicableEnDia

case class CalendarSpace(fecha : DateTime, rango : Interval) extends UbicableEnDia{

}
