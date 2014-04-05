package edu.unq.uis.planificador

import com.github.nscala_time.time.Imports._
import edu.unq.uis.planificador.disponibilidad.UbicableEnDia

case class Restriccion(fecha: DateTime ) extends UbicableEnDia {
}
