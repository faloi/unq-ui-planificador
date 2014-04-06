package edu.unq.uis.planificador.dependencyInjection

import org.uqbar.commons._
import edu.unq.uis.planificador.Empleado
import edu.unq.uis.planificador.homes.EmpleadosCollectionBasedHome

trait DevEnvironment {
  val empleadoHome: model.Home[Empleado] = EmpleadosCollectionBasedHome
}
