package edu.unq.uis.planificador.exceptions

import org.uqbar.commons.model.UserException

trait BusinessException extends UserException
case class UnexpectedBusinessException(msj: String) extends BusinessException
case class PlanificadorBusinessException(msj: String) extends BusinessException