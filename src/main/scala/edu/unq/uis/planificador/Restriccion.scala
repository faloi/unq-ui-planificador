package edu.unq.uis.planificador

import com.github.nscala_time.time.Imports._
import edu.unq.uis.planificador.timeHelpers.DateTimeHelper.dateToDate

class Restriccion(fecha: DateTime, val razon: String = "No puede trabajar este dia") {
  def hayPara(unTurno: Turno): Boolean = unTurno.dia.equalsDate(fecha)
}
