package edu.unq.uis.planificador.applicationModel.empleado

import edu.unq.uis.planificador.domain.Empleado
import edu.unq.uis.planificador.domain.calendar.RecurrentCalendarSpace
import org.uqbar.commons.utils.Observable

@Observable
class EmpleadoEditor(val entity: Empleado) {
  var disponibilidadSeleccionada: RecurrentCalendarSpace = _

  def eliminarDisponibilidad = entity.borrarDisponibilidad(disponibilidadSeleccionada)
}
