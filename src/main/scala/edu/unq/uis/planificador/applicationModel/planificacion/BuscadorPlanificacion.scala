package edu.unq.uis.planificador.applicationModel.planificacion

import org.uqbar.commons.utils.Observable
import edu.unq.uis.planificador.dependencyInjection.DevEnvironment
import scala.collection.JavaConversions._
import edu.unq.uis.planificador.domain.{Planificacion, Empleado}
import org.joda.time.{DateTimeConstants, LocalDate}

@Observable
class BuscadorPlanificacion extends DevEnvironment {
  var planificaciones: Seq[Planificacion] = _
  var planificacionSeleccionada: Planificacion = _

  def search {
    planificaciones = Seq()
    planificaciones = findPlanificacionesForThisWeek()

    planificaciones.headOption match {
      case Some(planificacion) => planificacionSeleccionada = planificacion
      case None =>
    }
  }

  def findPlanificacionesForThisWeek(): Seq[Planificacion] = {
    val now:LocalDate  = new LocalDate()
    List(
      Planificacion(now.withDayOfWeek(DateTimeConstants.MONDAY).toDateTimeAtStartOfDay),
      Planificacion(now.withDayOfWeek(DateTimeConstants.TUESDAY).toDateTimeAtStartOfDay),
      Planificacion(now.withDayOfWeek(DateTimeConstants.WEDNESDAY).toDateTimeAtStartOfDay),
      Planificacion(now.withDayOfWeek(DateTimeConstants.THURSDAY).toDateTimeAtStartOfDay),
      Planificacion(now.withDayOfWeek(DateTimeConstants.FRIDAY).toDateTimeAtStartOfDay),
      Planificacion(now.withDayOfWeek(DateTimeConstants.SATURDAY).toDateTimeAtStartOfDay),
      Planificacion(now.withDayOfWeek(DateTimeConstants.SUNDAY).toDateTimeAtStartOfDay)
    )
  }

}
