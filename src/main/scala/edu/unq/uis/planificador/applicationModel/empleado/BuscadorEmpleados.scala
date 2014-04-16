package edu.unq.uis.planificador.applicationModel.empleado

import org.uqbar.commons.utils.Observable
import edu.unq.uis.planificador.dependencyInjection.DevEnvironment
import scala.collection.JavaConversions._
import edu.unq.uis.planificador.domain.Empleado

@Observable
class BuscadorEmpleados extends DevEnvironment {
  var empleados: Seq[Empleado] = _
  var empleadoSeleccionado: Empleado = _

  def search {
    empleados = Seq()
    empleados = empleadoHome.allInstances()

    empleados.headOption match {
      case Some(empleado) => empleadoSeleccionado = empleado
      case None =>
    }
  }
}
