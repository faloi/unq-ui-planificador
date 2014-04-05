package edu.unq.uis.planificador.dependencyInjection

trait Home[T] {
  def findAll(): Seq[T]
  def save(objeto : T)
}
