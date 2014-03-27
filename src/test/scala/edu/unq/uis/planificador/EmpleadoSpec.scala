package edu.unq.uis.planificador

import org.scalatest._
import org.scalatest.matchers.ShouldMatchers
import com.github.nscala_time.time.Imports._
import edu.unq.uis.planificador.builders.RecurrentIntervalBuilder
import RecurrentIntervalBuilder._

class EmpleadoSpec extends FlatSpec with ShouldMatchers with BeforeAndAfter {

  var empleado = new Empleado
  
  before {
    this.empleado = new Empleado
  }
  
  "Un empleado " should "tener disponibilidad" in {
    empleado.puedeTrabajar(Turno el "2004-12-13" de 9 a 18) should be (false)
  }

  it should "aceptar un dia de disponibilidad" in {
    empleado disponibleLos (Lunes de 10 a 18)

    empleado.puedeTrabajar(Turno el "2014-03-24" de 14 a 19) should be (false)
    empleado.puedeTrabajar(Turno el "2014-03-24" de 14 a 18) should be (true)
  }

  it should "aceptar varias disponiblilidades" in {
    empleado disponibleLos (Jueves de 10 a 18)
    empleado disponibleLos (Lunes de 10 a 18)

    empleado.puedeTrabajar(Turno el "2014-03-24" de 11 a 14) should be (true)
    empleado.puedeTrabajar(Turno el "2014-03-27" de 15 a 17) should be (true)
  }

  it should "no estar disponible si hay una restriccion especifica para un dia normalmente habilitado" in {
    empleado disponibleLos (Lunes de 10 a 18)
    empleado.agregarRestriccion(new Restriccion(new DateTime("2014-03-24T11:00")))
    
    empleado.puedeTrabajar(Turno el "2014-03-24" de 11 a 13) should be (false)
  }

  it should "pisar la disponibilidad anterior cuando se agrega otra para el mismo dia" in {
    empleado disponibleLos (Lunes de 10 a 18)
    empleado disponibleLos (Lunes de 10 a 13)
    
    empleado.puedeTrabajar(Turno el "2014-03-24" de 14 a 16) should be (false)
  }
}
