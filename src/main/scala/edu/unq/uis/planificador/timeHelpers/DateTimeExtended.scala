package edu.unq.uis.planificador.timeHelpers

import com.github.nscala_time.time.Imports._

/**
 * Created by faloi on 3/26/14.
 */
object DateTimeHelper {
  implicit def dateToDate(date : DateTime) : DateTimeExtended = new DateTimeExtended(date)
}

class DateTimeExtended(value : DateTime) {
  def equalsDate(another : DateTime) = another.toLocalDate.equals(value.toLocalDate)
}
