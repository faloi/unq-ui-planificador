package edu.unq.uis.planificador.dependencyInjection

import org.uqbar.commons.model.Home
import edu.unq.uis.planificador.Empleado
import edu.unq.uis.planificador.homes.EmpleadosCollectionBasedHome

trait DevEnvironment {
  val empleadoHome: Home[Empleado] = EmpleadosCollectionBasedHome
}
