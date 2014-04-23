package edu.unq.uis.planificador.ui

import org.uqbar.arena.windows.WindowOwner
import edu.unq.uis.planificador.ui.widgets.NiceWindow
import edu.unq.uis.planificador.domain.{Empleado, Planificacion}
import org.uqbar.commons.utils.Observable
import ArenaScalaExtensions._

@Observable
class BuscadorPlanificacion(planificacion: Planificacion) {
  var empleados: Seq[Empleado] = planificacion.empleados
  var empleadoSeleccionado: Empleado = _

  def disponibilidadEmpleadoEnBloque(empleado: Empleado, bloque: Int) = {
    if (empleado.asignacionPara(planificacion.fecha).get.incluye(bloque)) "X" else ""
  }
}

class VerPlanificacionWindow(parent: WindowOwner, planificacion: Planificacion)
  extends NiceWindow[BuscadorPlanificacion](parent, new BuscadorPlanificacion(planificacion)) {
  override def windowDefinition: Renderizable =
    LayoutVertical(
      TableWidget[Empleado](
        bindItemsTo = "empleados",
        bindSelectionTo = "empleadoSeleccionado",
        height = 800,

        columnas: _*
      )
    )

  def columnas = TableColumn[Empleado](title = "Empleado", bindTo = Left("nombre")) :: columnasDeBloque.toList

  def columnasDeBloque = (0 until 24).map(columnaParaBloque(_))

  def columnaParaBloque(bloque: Int) = TableColumn[Empleado](
    width = 30,
    title = bloque.toString,
    bindTo = Right(
      (empleado: Empleado) => getModelObject.disponibilidadEmpleadoEnBloque(empleado, bloque)
    )
  )
}