package edu.unq.uis.planificador.domain

import edu.unq.uis.planificador.domain.builders.RecurrentCalendarSpaceBuilder
import RecurrentCalendarSpaceBuilder._
import edu.unq.uis.planificador.domain.disponibilidad._
import edu.unq.uis.planificador.BaseSpec
import edu.unq.uis.planificador.domain.calendar.DiaDeSemana._
import edu.unq.uis.planificador.dependencyInjection.TestEnvironment
import org.joda.time.DateTime

class PlanificacionSpec extends BaseSpec with TestEnvironment {
  var planificacionVacia: Planificacion = _
  var planificacionConUnempleado: Planificacion = _

  before {
    val e:Empleado = new Empleado
    //TODO: agregar un clear a la home
//    this.empleadoHome
    e disponibleLos (Jueves de 10 a 18)
    e disponibleLos (Lunes de 10 a 18)
    e asignar (Turno el "2014-03-07" de 14 a 16)
    this.empleadoHome.create(e)
    this.planificacionVacia = Planificacion(new DateTime("2014-03-08"), empleadoHome)
    this.planificacionConUnempleado = Planificacion(new DateTime("2014-03-07"), empleadoHome)
  }

  "Una planificacion" should "saber si se planificó para ese día" in {
    planificacionVacia.sePlanifico should be (false)
    planificacionConUnempleado.sePlanifico should be (true)
  }
}
