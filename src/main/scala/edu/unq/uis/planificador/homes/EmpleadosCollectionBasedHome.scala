package edu.unq.uis.planificador.homes

import org.uqbar.commons.model.CollectionBasedHome
import edu.unq.uis.planificador.domain.Empleado

object EmpleadosCollectionBasedHome extends AbstractCollectionBasedHomeEmpleado {
  override def getCriterio(example: Empleado) = null

  override def createExample = new Empleado

  override def getEntityType = classOf[Empleado]
}

trait AbstractCollectionBasedHomeEmpleado extends CollectionBasedHome[Empleado] with Serializable