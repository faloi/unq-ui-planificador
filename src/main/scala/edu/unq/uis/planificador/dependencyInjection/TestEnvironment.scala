package edu.unq.uis.planificador.dependencyInjection

import org.uqbar.commons.model
import edu.unq.uis.planificador.domain.Empleado
import edu.unq.uis.planificador.homes.EmpleadosCollectionBasedHome

trait TestEnvironment {
  val empleadoHome: model.Home[Empleado] = EmpleadosCollectionBasedHome
}
