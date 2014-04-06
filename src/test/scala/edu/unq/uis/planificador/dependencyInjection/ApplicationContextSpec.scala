package edu.unq.uis.planificador.dependencyInjection

import org.scalatest._
import org.scalatest.matchers.ShouldMatchers
import com.github.nscala_time.time.Imports._
import edu.unq.uis.planificador.builders.RecurrentIntervalBuilder
import RecurrentIntervalBuilder._
import edu.unq.uis.planificador.Empleado

class ApplicationContextSpec extends FlatSpec with ShouldMatchers with BeforeAndAfter {
  
  "El application context " should "tener una Home de Empleados" in {
    ApplicationContext.empleadoHome.save(new Empleado("Juan", "perez"))
    ApplicationContext.empleadoHome.save(new Empleado("Pepe", "pompin"))

    ApplicationContext.empleadoHome.findAll().isEmpty should be (false)

  }

}
