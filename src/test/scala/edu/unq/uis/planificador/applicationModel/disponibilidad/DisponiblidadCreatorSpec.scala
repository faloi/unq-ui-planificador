package edu.unq.uis.planificador.applicationModel.disponibilidad

import edu.unq.uis.planificador.BaseSpec
import edu.unq.uis.planificador.domain.Empleado
import edu.unq.uis.planificador.domain.calendar.DiaDeSemana._
import edu.unq.uis.planificador.domain.builders.RecurrentCalendarSpaceBuilder._

class DisponiblidadCreatorSpec extends BaseSpec {
  "DisponibilidadCreator" should "permitir crear disponibilidades solo para los dias que no tengo aun" in {
    val pepe = new Empleado()
    pepe disponibleLos(Lunes de 14 a 19, Martes de 15 a 18)

    new DisponibilidadCreator(pepe).diasSeleccionables should be(Seq(Miercoles, Jueves, Viernes, Sabado, Domingo))
  }
}
