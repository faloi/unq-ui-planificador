package edu.unq.uis.planificador

import com.github.nscala_time.time.Imports
import org.joda.time.{Instant, LocalTime, Interval, DateTime}

case class RecurrentInterval(inicio: Int, fin: Int, diaDeSemana: Int) {
  val horario = new Interval(new LocalTime(inicio, 0).toDateTime(new Instant(0)), new LocalTime(fin, 0).toDateTime(new Instant(0)))

  def hayPara(unTurno : Turno): Boolean = 
    unTurno.dia.getDayOfWeek == diaDeSemana && horario.contains(unTurno.horario)

  def vacio : RecurrentInterval = new RecurrentInterval(0 ,0, 0)
}