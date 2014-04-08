package edu.unq.uis.planificador.applicationModel

import edu.unq.uis.planificador.domain.Empleado
import edu.unq.uis.planificador.domain.disponibilidad.Disponible
import edu.unq.uis.planificador.domain.calendar.RecurrentCalendarSpace
import org.joda.time.DateTimeConstants
import collection.JavaConversions._
import org.uqbar.commons.utils.Observable

object Converters {

  @Observable
  implicit class EmpleadoModel(val self: Empleado) {
    def disponibilidades: java.util.List[DisponibilidadHoraria] = ((self estados) de Disponible).map {
      it => it.calendarSpace match {
        case RecurrentCalendarSpace(inicio, fin, diaDeSemana) => new DisponibilidadHoraria(toDayOfWeek(diaDeSemana), inicio, fin)
      }
    }

    def nombre = self.nombre

    def apellido = self.apellido

    def legajo = self.legajo

    def nombreCompleto = s"${self.nombre} ${self.apellido}"

    private def toDayOfWeek(it: Int) = {
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

