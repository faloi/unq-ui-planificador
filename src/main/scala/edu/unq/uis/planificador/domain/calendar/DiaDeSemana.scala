package edu.unq.uis.planificador.domain.calendar

import org.joda.time.{DateTime, DateTimeConstants}
import org.uqbar.commons.utils.Observable

trait Enum[A] {

  trait Value {
    self: A =>
    _values :+= this
  }

  private var _values = List.empty[A]

  def values = _values
}

sealed trait DiaDeSemana extends DiaDeSemana.Value with Ordered[DiaDeSemana] {
  def value: Int

  def nombre = this.getClass.getSimpleName.replace("$", "")

  def nombreCorto = nombre.substring(0, 2)

  override def compare(that: DiaDeSemana): Int = value - that.value
}

@Observable
object DiaDeSemana extends Enum[DiaDeSemana] {
  def todos = Seq(Lunes, Martes, Miercoles, Jueves, Viernes, Sabado, Domingo)

  def fromFecha(fecha: DateTime) = todos.find(_.value == fecha.getDayOfWeek).get

  @Observable
  case object Lunes extends DiaDeSemana {
    val value = DateTimeConstants.MONDAY
  }

  @Observable
  case object Martes extends DiaDeSemana {
    val value = DateTimeConstants.TUESDAY
  }

  @Observable
  case object Miercoles extends DiaDeSemana {
    val value = DateTimeConstants.WEDNESDAY
  }

  @Observable
  case object Jueves extends DiaDeSemana {
    val value = DateTimeConstants.THURSDAY
  }

  @Observable
  case object Viernes extends DiaDeSemana {
    val value = DateTimeConstants.FRIDAY
  }

  @Observable
  case object Sabado extends DiaDeSemana {
    val value = DateTimeConstants.SATURDAY
  }

  @Observable
  case object Domingo extends DiaDeSemana {
    val value = DateTimeConstants.SUNDAY
  }
}