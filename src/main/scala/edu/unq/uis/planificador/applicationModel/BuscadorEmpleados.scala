package edu.unq.uis.planificador.applicationModel

import org.uqbar.commons.utils.Observable
import edu.unq.uis.planificador.dependencyInjection.DevEnvironment
import java.util
import scala.collection.JavaConversions._
import edu.unq.uis.planificador.applicationModel.Converters.EmpleadoModel

@Observable
class BuscadorEmpleados extends DevEnvironment {
  var empleados: java.util.List[EmpleadoModel] = _
  var empleadoSeleccionado: EmpleadoModel = _

  def search {
    empleados = new util.ArrayList[EmpleadoModel]
    empleados = empleadoHome.allInstances().map(new EmpleadoModel(_))

    empleados.headOption match {
      case Some(empleado) => empleadoSeleccionado = empleado
      case None =>
    }
  }
}
