package edu.unq.uis.planificador.domain.calendar

import edu.unq.uis.planificador.BaseSpec
import edu.unq.uis.planificador.domain.builders.RecurrentCalendarSpaceBuilder._
import org.uqbar.commons.model.UserException

class RecurrentCalendarSpaceSpec extends BaseSpec {

  "RecurrentCalendarSpace" should "not aceptar horas de inicio menores a 0" in {
    intercept[UserException] {
      Martes de -2 a 18
    }
  }

  it should "not aceptar horas de finalizacion mayores a 23" in {
    intercept[UserException] {
      Martes de 14 a 24
    }
  }

  it should "not aceptar hora de fin anterior a la de inicio" in {
    intercept[UserException] {
      Martes de 14 a 12
    }
  }
}
