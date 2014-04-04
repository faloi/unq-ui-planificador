package edu.unq.uis.planificador

import org.scalatest._
import org.scalatest.matchers.ShouldMatchers
import com.github.nscala_time.time.Imports._
import edu.unq.uis.planificador.builders.RecurrentIntervalBuilder
import RecurrentIntervalBuilder._
import edu.unq.uis.planificador.disponibilidad._

class EmpleadoSpec extends FlatSpec with ShouldMatchers with BeforeAndAfter {

  var empleado = new Empleado
  
  before {
    this.empleado = new Empleado
  }

  "Un empleado" should "no tener disponibilidad por default" in {
    empleado.disponibilidadPara(Turno el "2004-12-13" de 9 a 18) should be (NoDisponible)
  }

  it should "responder si esta disponible para un turno cuando tiene disponibilidades configuradas" in {
    empleado disponibleLos (Jueves de 10 a 18)
    empleado disponibleLos (Lunes de 10 a 18)

    empleado.disponibilidadPara(Turno el "2014-03-24" de 11 a 14) should be (Disponible)
    empleado.disponibilidadPara(Turno el "2014-03-27" de 15 a 17) should be (Disponible)
  }

  it should "no estar disponible si hay una restriccion especifica para un dia normalmente habilitado" in {
    empleado disponibleLos (Lunes de 10 a 18)

    val restriccion = new Restriccion(new DateTime("2014-03-24T11:00"))
    empleado.agregarRestriccion(restriccion)

    empleado.disponibilidadPara(Turno el "2014-03-24" de 11 a 13) should be(ConRestriccion(restriccion))
  }

  it should "pisar la disponibilidad anterior cuando se agrega otra para el mismo dia" in {
    empleado disponibleLos (Lunes de 10 a 18)
    empleado disponibleLos (Lunes de 10 a 13)
    
    empleado.disponibilidadPara(Turno el "2014-03-24" de 14 a 16) should be (NoDisponible)
  }

  it should "devolver la razon cuando no esta disponible por restriccion" in {
    empleado.agregarRestriccion(new Restriccion(new DateTime("2014-03-24T11:00"), "Operacion de cadera"))

    empleado.disponibilidadPara(Turno el "2014-03-24" de 11 a 13).razon should be("Operacion de cadera")
  }

  it should "devolver la razon cuando no esta disponible por horarios" in {
    empleado.disponibilidadPara(Turno el "2014-03-24" de 11 a 13).razon should be("No trabaja en ese horario")
  }
}
