package edu.unq.uis.planificador.domain.calendar

import edu.unq.uis.planificador.BaseSpec
import edu.unq.uis.planificador.domain.calendar.DiaDeSemana._

class DiaDeSemanaSpec extends BaseSpec {
  "Dia de semana" should "conocer todos los dias de la semana" in {
    DiaDeSemana.todos should be(Seq(Lunes, Martes, Miercoles, Jueves, Viernes, Sabado, Domingo))
  }
}
