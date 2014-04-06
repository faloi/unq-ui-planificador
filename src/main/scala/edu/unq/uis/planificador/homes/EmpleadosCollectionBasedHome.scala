package edu.unq.uis.planificador.homes

import org.uqbar.commons.model.CollectionBasedHome
import edu.unq.uis.planificador.domain.Empleado

object EmpleadosCollectionBasedHome extends CollectionBasedHome[Empleado] {
  create(new Empleado("Pedro", "Picapiedras", "123134"))

  override def getCriterio(example: Empleado) = null

  override def createExample = new Empleado

  override def getEntityType = classOf[Empleado]
}
