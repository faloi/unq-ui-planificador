package edu.unq.uis.planificador

import com.github.nscala_time.time.Imports
import org.joda.time.{DateTimeConstants, DateTime}

case class RecurrentInterval(inicio: Int, fin: Int, diaDeSemana: Int) {
  implicit def intToPartialInterval(d: Int) = new RecurrentInterval(d, 0, 0)
  def hayPara(time: Imports.DateTime): Boolean =
    time.getDayOfWeek == diaDeSemana && (inicio until fin contains time.getHourOfDay)

  def vacio : RecurrentInterval = new RecurrentInterval(0 ,0, 0)

  def a( unFin : Int) : RecurrentInterval = new RecurrentInterval(this.inicio ,unFin, this.diaDeSemana)

  def de(unInicio : Int) : RecurrentInterval = new RecurrentInterval(unInicio ,this.fin, this.diaDeSemana)
}

object RecurrentInterval{
  implicit def intToPartialInterval(d: Int) = new RecurrentInterval(d, 0, 0)
  val Lunes : RecurrentInterval = new RecurrentInterval(0,0, DateTimeConstants.MONDAY)
  val Martes : RecurrentInterval = new RecurrentInterval(0,0, DateTimeConstants.TUESDAY)
  val Miercoles : RecurrentInterval = new RecurrentInterval(0,0, DateTimeConstants.WEDNESDAY)
  val Jueves : RecurrentInterval = new RecurrentInterval(0,0, DateTimeConstants.THURSDAY)
  val Viernes : RecurrentInterval = new RecurrentInterval(0,0, DateTimeConstants.FRIDAY)
  val Sabado : RecurrentInterval = new RecurrentInterval(0,0, DateTimeConstants.SATURDAY)
  val Domingo : RecurrentInterval = new RecurrentInterval(0,0, DateTimeConstants.SUNDAY)

}
