package edu.unq.uis.planificador.applicationModel

import edu.unq.uis.planificador.BaseSpec
import scala.collection.JavaConversions.asScalaBuffer

class BuscadorEmpleadosSpec extends BaseSpec {
  var buscador : BuscadorEmpleados = _

  before {
    buscador = new BuscadorEmpleados()
  }

  "El buscador de empleados" should "seleccionar el primer elemento luego de buscar" in {
    buscador.empleadoSeleccionado should be (null)

    buscador.search
    buscador.empleadoSeleccionado should be (buscador.empleados.head)
  }

  //TODO para poder hacer este test, hay que cambiar el Environment actual y crear los empleados aca
  ignore should "no hacer nada cuando luego de buscar no hay elementos" in {
    buscador.search
    buscador.empleadoSeleccionado should be (buscador.empleados.head)
    buscador.empleadoSeleccionado should be (null)
  }
}
