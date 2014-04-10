package edu.unq.uis.planificador.applicationModel.disponibilidad

import edu.unq.uis.planificador.BaseSpec
import edu.unq.uis.planificador.domain.Empleado
import edu.unq.uis.planificador.domain.calendar.DiaDeSemana._
import edu.unq.uis.planificador.domain.builders.RecurrentCalendarSpaceBuilder._
import scala.collection.JavaConversions._
import edu.unq.uis.planificador.domain.calendar.DiaDeSemana

class DisponiblidadCreatorSpec extends BaseSpec {
  "DisponibilidadCreator" should "permitir crear disponibilidades solo para los dias que no tengo aun" in {
    val pepe = new Empleado()
    pepe disponibleLos(Lunes de 14 a 19, Martes de 15 a 18)

    val expected = Seq(Miercoles, Jueves, Viernes, Sabado, Domingo)
    new DisponibilidadCreator(pepe).diasSeleccionables should be(expected: java.util.List[DiaDeSemana])
  }
}
