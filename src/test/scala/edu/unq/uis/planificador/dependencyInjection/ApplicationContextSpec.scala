package edu.unq.uis.planificador.dependencyInjection

import org.scalatest._
import org.scalatest.matchers.ShouldMatchers
import edu.unq.uis.planificador.Empleado

class ApplicationContextSpec extends FlatSpec with ShouldMatchers with BeforeAndAfter {
  
  "El application context " should "tener una Home de Empleados" in {
    ApplicationContext.empleadoHome.save(new Empleado)
    ApplicationContext.empleadoHome.save(new Empleado)

    ApplicationContext.empleadoHome.findAll().isEmpty should be (false)

  }

}
