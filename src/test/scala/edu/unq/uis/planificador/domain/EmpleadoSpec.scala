package edu.unq.uis.planificador.domain

import edu.unq.uis.planificador.domain.builders.RecurrentCalendarSpaceBuilder
import RecurrentCalendarSpaceBuilder._
import edu.unq.uis.planificador.domain.disponibilidad._
import edu.unq.uis.planificador.BaseSpec
import edu.unq.uis.planificador.domain.calendar.DiaDeSemana._
import edu.unq.uis.planificador.domain.calendar.RecurrentCalendarSpace
import scala.collection.JavaConversions._

class EmpleadoSpec extends BaseSpec {

  var empleado = new Empleado

  before {
    this.empleado = new Empleado
  }

  "Un empleado" should "no tener disponibilidad por default" in {
    empleado.isDisponibleLos(Turno el "2004-12-13" de 9 a 18) should be(NoDisponible)
  }

  it should "responder si esta disponible para un turno cuando tiene disponibilidades configuradas" in {
    empleado disponibleLos (Jueves de 10 a 18)
    empleado disponibleLos (Lunes de 10 a 18)

    empleado isDisponibleLos (Turno el "2014-03-24" de 11 a 14) should be(Disponible)
    empleado isDisponibleLos (Turno el "2014-03-27" de 15 a 17) should be(Disponible)
  }

  it should "no estar disponible si hay una restriccion especifica para un dia normalmente habilitado" in {
    empleado disponibleLos (Lunes de 10 a 18)
    empleado restriccionEl "2014-03-24"

    empleado isDisponibleLos (Turno el "2014-03-24" de 11 a 13) should be(Restriccion)
  }

  it should "pisar la disponibilidad anterior cuando se agrega otra para el mismo dia" in {
    empleado disponibleLos (Lunes de 10 a 18)
    empleado disponibleLos (Lunes de 10 a 13)

    empleado isDisponibleLos (Turno el "2014-03-24" de 14 a 16) should be(NoDisponible)
  }

  it should "return una coleccion de disponibilidades" in {
    empleado disponibleLos (Lunes de 15 a 19)
    empleado disponibleLos (Jueves de 16 a 21)

    val expected = Seq(Lunes de 15 a 19, Jueves de 16 a 21)
    empleado.disponibilidades should be(expected: java.util.List[RecurrentCalendarSpace])
  }

  it should "saber su nombre completo" in {
    empleado.nombre = "Juan Carlos"
    empleado.apellido = "Jimenez"

    empleado.nombreCompleto should be("Juan Carlos Jimenez")
  }

  it should "saber sus dias para los cuales esta disponible normalmente, en orden semanal" in {
    empleado disponibleLos (Jueves de 16 a 21)
    empleado disponibleLos (Lunes de 15 a 19)
    empleado disponibleLos (Viernes de 16 a 21)

    empleado.diasDisponible should be("Lu, Ju, Vi")
  }

  it should "devolver un string vacio para los dias cuando no tiene disponibilidades" in {
    empleado.diasDisponible should be("")
  }

  it should "ser capaz de borrar una disponibilidad" in {
    val disponibilidadLunes = Lunes de 9 a 18
    val disponibilidadMartes = Martes de 14 a 16

    empleado disponibleLos(disponibilidadLunes, disponibilidadMartes)
    empleado borrarDisponibilidad (disponibilidadLunes)

    empleado.disponibilidades should be(Seq(disponibilidadMartes): java.util.List[RecurrentCalendarSpace])
  }
}
