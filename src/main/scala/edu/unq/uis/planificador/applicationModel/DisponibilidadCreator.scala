package edu.unq.uis.planificador.applicationModel

import org.uqbar.commons.utils.Observable
import edu.unq.uis.planificador.applicationModel.Converters.EmpleadoModel
import edu.unq.uis.planificador.domain.calendar.{DiaDeSemana, RecurrentCalendarSpace}
import scala.collection.JavaConversions._

@Observable
class DisponibilidadCreator(empleado: EmpleadoModel) {
  var desde: Int = _
  var hasta: Int = _
  var diaDeSemana: DiaDeSemana = _

  def agregarDisponibilidad = {
    val interval = new RecurrentCalendarSpace(desde, hasta, diaDeSemana)
    interval.validar()

    empleado.self.disponibleLos(interval)
  }

  def diasSeleccionables: java.util.List[DiaDeSemana] = DiaDeSemana.todos
}
