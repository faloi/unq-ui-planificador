package edu.unq.uis.planificador.patterns

class Chain[A, B](f: A => Either[A, B]) {
  def +>(next: => (A => Either[A, B])): A => Either[A, B] =
    f(_).left.flatMap(next)
}

object Chain {
  implicit def func2Chain[A, B](f: A => Either[A, B]) = new Chain[A, B](f)
}