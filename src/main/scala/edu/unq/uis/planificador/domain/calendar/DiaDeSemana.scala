package edu.unq.uis.planificador.domain.calendar

import org.joda.time.DateTimeConstants

trait Enum[A] {

  trait Value {
    self: A =>
    _values :+= this
  }

  private var _values = List.empty[A]

  def values = _values
}

sealed trait DiaDeSemana extends DiaDeSemana.Value {
  def value: Int

  def nombre = this.getClass.getSimpleName.replace("$", "")
}

object DiaDeSemana extends Enum[DiaDeSemana] {

  case object Lunes extends DiaDeSemana {
    val value = DateTimeConstants.MONDAY
  }

  case object Martes extends DiaDeSemana {
    val value = DateTimeConstants.TUESDAY
  }

  case object Miercoles extends DiaDeSemana {
    val value = DateTimeConstants.WEDNESDAY
  }

  case object Jueves extends DiaDeSemana {
    val value = DateTimeConstants.THURSDAY
  }

  case object Viernes extends DiaDeSemana {
    val value = DateTimeConstants.FRIDAY
  }

  case object Sabado extends DiaDeSemana {
    val value = DateTimeConstants.SATURDAY
  }

  case object Domingo extends DiaDeSemana {
    val value = DateTimeConstants.SUNDAY
  }

  def todos = Seq(Lunes, Martes, Miercoles, Jueves, Viernes, Sabado, Domingo)
}