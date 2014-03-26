package edu.unq.uis.planificador

import org.scalatest._
import org.scalatest.matchers.ShouldMatchers
import com.github.nscala_time.time.Imports._
import edu.unq.uis.planificador.RecurrentIntervalBuilder._

class EmpleadoSpec extends FlatSpec with ShouldMatchers with BeforeAndAfter {

  var empleado = new Empleado
  
  before {
    this.empleado = new Empleado
  }
  
  "Un empleado " should "tener disponibilidad" in {
    empleado.estaDisponiblePara(new DateTime("2004-12-13")) should not be true
  }

  it should "aceptar un dia de disponibilidad" in {
    empleado estaDisponibleLos (Lunes de 10 a 18)
    empleado.estaDisponiblePara(new DateTime("2014-03-24T09:00")) should not be true
    empleado.estaDisponiblePara(new DateTime("2014-03-24T15:00")) should not be false
  }

  it should "aceptar varias disponiblilidades" in {
    empleado estaDisponibleLos (Jueves de 10 a 18)
    empleado estaDisponibleLos (Lunes de 10 a 18)

    empleado.estaDisponiblePara(new DateTime("2014-03-24T11:00")) should not be false
    empleado.estaDisponiblePara(new DateTime("2014-03-27T15:00")) should not be false
  }

  it should "no estar disponible si hay una restriccion especifica para un dia normalmente habilitado" in {
    empleado estaDisponibleLos (Lunes de 10 a 18)
    empleado.agregarRestriccion( new Restriccion(new DateTime("2014-03-24T11:00")))
    empleado.estaDisponiblePara(new DateTime("2014-03-24T11:00")) should not be true
  }

  it should "pisar la disponibilidad anterior cuando se agrega otra para el mismo dia" in {
    empleado estaDisponibleLos (Lunes de 10 a 18)
    empleado estaDisponibleLos (Lunes de 10 a 13)
    empleado.estaDisponiblePara(new DateTime("2014-03-24T14:00")) should not be true
  }
}
