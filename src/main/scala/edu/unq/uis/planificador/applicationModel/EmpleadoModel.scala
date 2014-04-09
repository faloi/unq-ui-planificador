package edu.unq.uis.planificador.applicationModel

import edu.unq.uis.planificador.domain.Empleado
import edu.unq.uis.planificador.domain.disponibilidad.Disponible
import edu.unq.uis.planificador.domain.calendar.RecurrentCalendarSpace
import collection.JavaConversions._
import org.uqbar.commons.utils.Observable

object Converters {

  @Observable
  implicit class EmpleadoModel(val self: Empleado) {
    def disponibilidades: java.util.List[RecurrentCalendarSpace] = ((self estados) de Disponible)
      .map {
      element => element.calendarSpace match {
        case calendar@RecurrentCalendarSpace(_, _, _) => calendar
      }
    }
      .sortBy(_.diaDeSemana)

    def nombreCompleto = s"${self.nombre} ${self.apellido}"

    def diasDisponible = disponibilidades.map(_.diaDeSemana.nombreCorto).mkString(", ")
  }

}

