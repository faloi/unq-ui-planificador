package edu.unq.uis.planificador.applicationModel

import edu.unq.uis.planificador.BaseSpec
import edu.unq.uis.planificador.domain.Empleado
import edu.unq.uis.planificador.domain.builders.RecurrentCalendarSpaceBuilder._
import edu.unq.uis.planificador.applicationModel.Converters._
import collection.JavaConversions._

class EmpleadoModelSpec extends BaseSpec {
  var empleado: Empleado = null

  before {
    empleado = new Empleado
  }

  "An empleado model" should "return una coleccion de disponiblidades" in {
    empleado disponibleLos (Lunes de 15 a 19)
    empleado disponibleLos (Jueves de 16 a 21)

    val expected = Seq(new DisponibilidadHoraria("Lunes", 15, 19), new DisponibilidadHoraria("Jueves", 16, 21))
    empleado.disponibilidades should be(expected: java.util.List[DisponibilidadHoraria])
  }
}
