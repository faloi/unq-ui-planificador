package edu.unq.uis.planificador.applicationModel.disponibilidad

import org.uqbar.commons.utils.Observable
import edu.unq.uis.planificador.domain.calendar.{DiaDeSemana, RecurrentCalendarSpace}
import edu.unq.uis.planificador.domain.Empleado

@Observable
class DisponibilidadCreator(empleado: Empleado) {
  var entity = new RecurrentCalendarSpace()

  def agregarDisponibilidad = {
    entity.validar()
    empleado.disponibleLos(entity)
  }

  def diasSeleccionables = DiaDeSemana.todos.diff(empleado.disponibilidades.map(_.diaDeSemana))
}
