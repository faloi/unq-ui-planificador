package edu.unq.uis.planificador.applicationModel.planificacion

import org.uqbar.commons.utils.Observable
import edu.unq.uis.planificador.dependencyInjection.DevEnvironment
import scala.collection.JavaConversions._
import edu.unq.uis.planificador.domain.{Planificacion, Empleado}

@Observable
class BuscadorPlanificacion extends DevEnvironment {
  var planificaciones: Seq[Planificacion] = _
  var planificacionSeleccionada: Planificacion = _

  def search {
    planificaciones = Seq()
    planificaciones = planificacionHome.allInstances()

    planificaciones.headOption match {
      case Some(planificacion) => planificacionSeleccionada = planificacion
      case None =>
    }
  }

}
