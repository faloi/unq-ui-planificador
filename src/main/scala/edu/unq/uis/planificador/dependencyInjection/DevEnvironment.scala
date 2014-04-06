package edu.unq.uis.planificador.dependencyInjection

import org.uqbar.commons._
import edu.unq.uis.planificador.homes.EmpleadosCollectionBasedHome
import edu.unq.uis.planificador.domain.Empleado

trait DevEnvironment {
  val empleadoHome: model.Home[Empleado] = EmpleadosCollectionBasedHome
}
