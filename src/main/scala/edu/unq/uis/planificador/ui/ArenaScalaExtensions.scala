package edu.unq.uis.planificador.ui

import org.uqbar.lacar.ui.model.Action

object ArenaScalaExtensions {
  implicit def closureToAction(closure: () => Any): Action = new ScalaClosureAction(closure)
}

sealed class ScalaClosureAction(method: () => Any) extends Action {
  override def execute() = method()
}