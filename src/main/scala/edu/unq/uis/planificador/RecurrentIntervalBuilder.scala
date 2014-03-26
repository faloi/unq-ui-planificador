package edu.unq.uis.planificador

import org.joda.time.DateTimeConstants

class RecurrentIntervalBuilder(day : Int) {
  var inicio : Int = 0
  var fin : Int = 0


  def a( unFin : Int) : RecurrentInterval = {
    this.fin = unFin
    new RecurrentInterval(this.inicio, this.fin, this.day)
  }

  def de(unInicio : Int) : RecurrentIntervalBuilder = {
    this.inicio = unInicio
    this
  }


}

object RecurrentIntervalBuilder{
//  implicit def intToPartialInterval(d: Int) = new RecurrentInterval(d, 0, 0)
  val Lunes : RecurrentIntervalBuilder = new RecurrentIntervalBuilder(DateTimeConstants.MONDAY)
  val Martes : RecurrentIntervalBuilder = new RecurrentIntervalBuilder(DateTimeConstants.TUESDAY)
  val Miercoles : RecurrentIntervalBuilder = new RecurrentIntervalBuilder(DateTimeConstants.WEDNESDAY)
  val Jueves : RecurrentIntervalBuilder = new RecurrentIntervalBuilder(DateTimeConstants.THURSDAY)
  val Viernes : RecurrentIntervalBuilder = new RecurrentIntervalBuilder(DateTimeConstants.FRIDAY)
  val Sabado : RecurrentIntervalBuilder = new RecurrentIntervalBuilder(DateTimeConstants.SATURDAY)
  val Domingo : RecurrentIntervalBuilder = new RecurrentIntervalBuilder(DateTimeConstants.SUNDAY)
}