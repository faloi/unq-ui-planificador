package edu.unq.uis.planificador

import com.github.nscala_time.time.Imports
import org.joda.time.{DateTimeConstants, DateTime}

case class RecurrentInterval(inicio: Int, fin: Int, diaDeSemana: Int) {
  implicit def intToPartialInterval(d: Int) = new RecurrentInterval(d, 0, 0)
  def hayPara(time: Imports.DateTime): Boolean =
    time.getDayOfWeek == diaDeSemana && (inicio until fin contains time.getHourOfDay)

  def vacio : RecurrentInterval = new RecurrentInterval(0 ,0, 0)
}
