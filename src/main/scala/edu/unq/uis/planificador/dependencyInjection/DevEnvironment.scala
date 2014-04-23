package edu.unq.uis.planificador.dependencyInjection

import org.uqbar.commons._
import edu.unq.uis.planificador.homes.{PlanificacionesCollectionBasedHome, EmpleadosCollectionBasedHome}
import edu.unq.uis.planificador.domain.{Planificacion, Empleado}

trait DevEnvironment {
  val empleadoHome: model.Home[Empleado] = EmpleadosCollectionBasedHome
  val planificacionHome: model.Home[Planificacion] = PlanificacionesCollectionBasedHome
}
