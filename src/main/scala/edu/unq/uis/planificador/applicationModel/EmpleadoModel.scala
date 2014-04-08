package edu.unq.uis.planificador.applicationModel

import edu.unq.uis.planificador.domain.Empleado
import edu.unq.uis.planificador.domain.disponibilidad.Disponible
import edu.unq.uis.planificador.domain.calendar.RecurrentCalendarSpace
import org.joda.time.DateTimeConstants
import collection.JavaConversions._

object Converters {

  implicit class EmpleadoModel(val self: Empleado) {
    def disponibilidades: java.util.List[DisponibilidadHoraria] = ((self estados) de Disponible).map {
      it => it.calendarSpace match {
        case RecurrentCalendarSpace(inicio, fin, diaDeSemana) => new DisponibilidadHoraria(toDayOfWeek(diaDeSemana), inicio, fin)
      }
    }

    def toDayOfWeek(it: Int) = {
      it match {
        case DateTimeConstants.MONDAY => "Lunes"
        case DateTimeConstants.TUESDAY => "Martes"
        case DateTimeConstants.WEDNESDAY => "Miércoles"
        case DateTimeConstants.THURSDAY => "Jueves"
        case DateTimeConstants.FRIDAY => "Viernes"
        case DateTimeConstants.SATURDAY => "Sábado"
        case DateTimeConstants.SUNDAY => "Domingo"
      }
    }
  }

}

