package edu.unq.uis.planificador

import org.joda.time.DateTimeConstants
import org.scalatest._
import org.scalatest.matchers.ShouldMatchers
import com.github.nscala_time.time.Imports._
import edu.unq.uis.planificador.RecurrentIntervalBuilder._

class EmpleadoSpec extends FlatSpec with ShouldMatchers {
  "Test1" should "lala" in {
    val jorge = new Empleado
    jorge.estaDisponiblePara(new DateTime("2004-12-13")) should be (false)
  }

  it should "aceptar un dia de disponibilidad" in {
    val jorge = new Empleado
    jorge.agregarDisponibilidad (Lunes de 10 a 18)
    jorge.estaDisponiblePara(new DateTime("2014-03-24T09:00")) should be (false)
    jorge.estaDisponiblePara(new DateTime("2014-03-24T15:00")) should be (true)
  }

  it should "aceptar varias disponiblilidades" in {
    val jorge = new Empleado
    jorge.agregarDisponibilidad (Jueves de 10 a 18)
    jorge.agregarDisponibilidad (Lunes de 10 a 18)

    jorge.estaDisponiblePara(new DateTime("2014-03-24T11:00")) should be (true)
    jorge.estaDisponiblePara(new DateTime("2014-03-27T15:00")) should be (true)
  }

  it should "no estar disponible si hay una restriccion especifica para un dia normalmente habilitado" in {
    var jorge = new Empleado
    jorge.agregarDisponibilidad (Lunes de 10 a 18)
    jorge.agregarRestriccion( new Restriccion(new DateTime("2014-03-24T11:00")))

    jorge.estaDisponiblePara(new DateTime("2014-03-24T11:00")) should be (false)
  }

  it should "pisar la disponibilidad anterior cuando se agrega otra para el mismo dia" in {
    val jorge = new Empleado
    jorge.agregarDisponibilidad (Lunes de 10 a 18)
    jorge.agregarDisponibilidad (Lunes de 10 a 13)

    jorge.estaDisponiblePara(new DateTime("2014-03-24T14:00")) should be (false)
  }
}
