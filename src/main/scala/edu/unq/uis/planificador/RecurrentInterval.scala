package edu.unq.uis.planificador

import edu.unq.uis.planificador.timeHelpers.TimeInterval

case class RecurrentInterval(inicio: Int, fin: Int, diaDeSemana: Int) {
  val horario = TimeInterval.create(inicio, fin)

  def hayPara(unTurno : Turno): Boolean = 
    unTurno.fecha.getDayOfWeek == diaDeSemana && horario.contains(unTurno.horario)


  def vacio: RecurrentInterval = new RecurrentInterval(0, 0, 0)
}