package edu.unq.uis.planificador

import org.joda.time.DateTime
import com.github.nscala_time.time.Imports

class Restriccion(fecha : DateTime, razon : String = "Se me murio mi perrito") {
  def hayPara(time: Imports.DateTime): Boolean =
    time.toLocalDate.equals(fecha.toLocalDate)

}
