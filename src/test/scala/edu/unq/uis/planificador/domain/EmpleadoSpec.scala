package edu.unq.uis.planificador.domain

import edu.unq.uis.planificador.domain.builders.RecurrentCalendarSpaceBuilder
import RecurrentCalendarSpaceBuilder._
import edu.unq.uis.planificador.domain.disponibilidad._
import edu.unq.uis.planificador.BaseSpec
import edu.unq.uis.planificador.domain.calendar.DiaDeSemana._

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


  //TODO: hay que volver a ponerle las razones especiales, pero por ahora lo matamos
  //  it should "devolver la razon cuando no esta disponible por restriccion" in {
  //    empleado restriccionEl new DateTime("2014-03-24T11:00")
  //
  //    empleado tieneDisponibilidad (Turno el "2014-03-24" de 11 a 13). should be("Operacion de cadera")
  //  }

  //  it should "devolver la razon cuando no esta disponible por horarios" in {
  //    empleado tieneDisponibilidad (Turno el "2014-03-24" de 11 a 13).razon should be("No trabaja en ese horario")
  //  }
}
