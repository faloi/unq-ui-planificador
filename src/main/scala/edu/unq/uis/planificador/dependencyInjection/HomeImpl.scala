package edu.unq.uis.planificador.dependencyInjection

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

trait HomeImpl[T] extends Home[T]{
  val all : mutable.Buffer[T] = new ListBuffer[T]
  def findAll() : scala.collection.mutable.Seq[T] = all.clone()
  def save(objeto : T)  = this.all += objeto
  def clear() = this.all.clear()
}
