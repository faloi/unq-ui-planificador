package edu.unq.uis.planificador

import com.github.nscala_time.time.Imports

case class RecurrentInterval(inicio: Int, fin: Int, diaDeSemana: Int) {
  def hayPara(time: Imports.DateTime): Boolean =
    time.getDayOfWeek == diaDeSemana && (inicio until fin contains time.getHourOfDay)

  def vacio : RecurrentInterval = new RecurrentInterval(0 ,0, 0)
}
