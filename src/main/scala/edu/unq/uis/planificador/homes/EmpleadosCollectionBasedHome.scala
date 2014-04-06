package edu.unq.uis.planificador.homes

import org.uqbar.commons.model.CollectionBasedHome
import edu.unq.uis.planificador.Empleado

object EmpleadosCollectionBasedHome extends CollectionBasedHome[Empleado] {
  override def getCriterio(example: Empleado) = null

  override def createExample = new Empleado

  override def getEntityType = classOf[Empleado]
}
