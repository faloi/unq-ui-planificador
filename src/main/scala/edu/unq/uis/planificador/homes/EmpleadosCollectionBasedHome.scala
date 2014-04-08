package edu.unq.uis.planificador.homes

import org.uqbar.commons.model.CollectionBasedHome
import edu.unq.uis.planificador.domain.Empleado
import edu.unq.uis.planificador.domain.builders.RecurrentCalendarSpaceBuilder
import RecurrentCalendarSpaceBuilder._

object EmpleadosCollectionBasedHome extends CollectionBasedHome[Empleado] {
  val pedro = new Empleado("Pedro", "Picapiedras", "123134")
  pedro disponibleLos (Lunes de 14 a 19)
  pedro disponibleLos (Martes de 16 a 20)
  create(pedro)

  override def getCriterio(example: Empleado) = null

  override def createExample = new Empleado

  override def getEntityType = classOf[Empleado]
}
