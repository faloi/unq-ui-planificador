package edu.unq.uis.planificador.homes

import org.uqbar.commons.model.CollectionBasedHome
import edu.unq.uis.planificador.domain.{Planificacion}
import org.apache.commons.collections15.Predicate

object PlanificacionesCollectionBasedHome  extends CollectionBasedHome[Planificacion]{
  override def getCriterio(example: Planificacion): Predicate[_] = null

  override def createExample(): Planificacion = null

  override def getEntityType: Class[Planificacion] = classOf[Planificacion]
}
