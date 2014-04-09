package edu.unq.uis.planificador.applicationModel

import org.uqbar.commons.utils.Observable
import edu.unq.uis.planificador.applicationModel.Converters.EmpleadoModel
import edu.unq.uis.planificador.domain.calendar.{DiaDeSemana, RecurrentCalendarSpace}
import scala.collection.JavaConversions._

@Observable
class DisponibilidadCreator(empleado: EmpleadoModel) {
  var entity = new RecurrentCalendarSpace()

  def agregarDisponibilidad = {
    entity.validar()
    empleado.self.disponibleLos(entity)
  }

  def diasSeleccionables: java.util.List[DiaDeSemana] = DiaDeSemana.todos
}
