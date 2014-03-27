package edu.unq.uis.planificador.timeHelpers

import com.github.nscala_time.time.Imports._
import org.joda.time.Instant
import com.github.nscala_time.time.TypeImports.Interval

/**
 * Created by faloi on 3/26/14.
 */
object TimeInterval {
  def create(inicio: Int, fin: Int): Interval =
    new Interval(toLocalDateTime(inicio), toLocalDateTime(fin))

  def create(inicio : LocalTime, fin : LocalTime) : Interval =
    new Interval(toLocalDateTime(inicio), toLocalDateTime(fin))

  def create(inicio : DateTime, fin : DateTime) : Interval =
    new Interval(toLocalDateTime(inicio), toLocalDateTime(fin))

  private def toLocalDateTime(time : LocalTime) : DateTime = time.toDateTime(new Instant(0))
  private def toLocalDateTime(dateTime : DateTime) : DateTime = toLocalDateTime(dateTime.toLocalTime)
  private def toLocalDateTime(hora : Int) : DateTime = toLocalDateTime(new LocalTime(hora, 0))
}
