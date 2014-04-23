package edu.unq.uis.planificador.ui

import org.uqbar.lacar.ui.model.Action
import com.uqbar.commons.collections.Transformer

object ArenaScalaExtensions {
  implicit def closureToAction(closure: () => Any): Action = new ScalaClosureAction(closure)

  implicit def closureToTransformer[A, B](closure: (A) => B): Transformer[A, B] = new ScalaClosureTransformer(closure)
}

sealed class ScalaClosureTransformer[A, B](projection: (A) => B) extends Transformer[A, B] {
  override def transform(element: A): B = projection(element)
}

sealed class ScalaClosureAction(method: () => Any) extends Action {
  override def execute() = method()
}