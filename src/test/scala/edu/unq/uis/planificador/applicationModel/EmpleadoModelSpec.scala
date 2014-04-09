package edu.unq.uis.planificador.applicationModel

import edu.unq.uis.planificador.BaseSpec
import edu.unq.uis.planificador.domain.builders.RecurrentCalendarSpaceBuilder._
import collection.JavaConversions._
import edu.unq.uis.planificador.domain.Empleado
import edu.unq.uis.planificador.applicationModel.Converters._

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

  it should "saber su nombre completo" in {
    empleado.nombre = "Juan Carlos"
    empleado.apellido = "Jimenez"

    empleado.nombreCompleto should be("Juan Carlos Jimenez")
  }

  it should "saber sus dias para los cuales esta disponible normalmente, en orden semanal" in {
    empleado disponibleLos (Jueves de 16 a 21)
    empleado disponibleLos (Lunes de 15 a 19)
    empleado disponibleLos (Viernes de 16 a 21)

    empleado.diasDisponible should be("Lu, Ju, Vi")
  }

  it should "devolver un string vacio para los dias cuando no tiene disponibilidades" in {
    empleado.diasDisponible should be("")
  }
}
