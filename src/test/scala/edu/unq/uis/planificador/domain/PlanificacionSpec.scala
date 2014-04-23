package edu.unq.uis.planificador.domain

import edu.unq.uis.planificador.domain.builders.RecurrentCalendarSpaceBuilder
import RecurrentCalendarSpaceBuilder._
import edu.unq.uis.planificador.domain.disponibilidad._
import edu.unq.uis.planificador.BaseSpec
import edu.unq.uis.planificador.domain.calendar.DiaDeSemana._
import edu.unq.uis.planificador.dependencyInjection.DevEnvironment
import org.joda.time.DateTime

class PlanificacionSpec extends BaseSpec with DevEnvironment {
  var planificacionVacia: Planificacion = _
  var planificacionConUnempleado: Planificacion = _
  var juan: Empleado = _

  before {
    juan = new Empleado
    //TODO: agregar un clear a la home
//    this.empleadoHome
    juan disponibleLos (Jueves de 10 a 18)
    juan disponibleLos (Lunes de 10 a 18)
    juan asignar (Turno el "2014-03-07" de 14 a 16)
    this.empleadoHome.create(juan)
    this.planificacionVacia = Planificacion(new DateTime("2014-03-08"))
    this.planificacionConUnempleado = Planificacion(new DateTime("2014-03-07"))
  }

  "Una planificacion" should "saber si se planificó para ese día" in {
    planificacionVacia.sePlanifico should be (false)
    planificacionConUnempleado.sePlanifico should be (true)
  }

  it should "poder darme la disponibilidad de un empleado" in {
    planificacionConUnempleado.asignacionDe(juan) should be(Turno el "2014-03-07" de 14 a 16)
  }
}
