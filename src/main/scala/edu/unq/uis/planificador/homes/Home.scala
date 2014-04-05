package edu.unq.uis.planificador.homes

trait Home[T] {
  def findAll() : Seq[T]
}
