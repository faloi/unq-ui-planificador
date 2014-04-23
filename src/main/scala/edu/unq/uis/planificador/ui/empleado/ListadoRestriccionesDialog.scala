package edu.unq.uis.planificador.ui.empleado

import edu.unq.uis.planificador.ui.widgets.NiceWindow
import org.uqbar.arena.windows.WindowOwner
import edu.unq.uis.planificador.domain.Empleado
import edu.unq.uis.planificador.applicationModel.empleado.BuscadorEmpleados

class ListadoRestriccionesDialog(owner: WindowOwner) extends NiceWindow[BuscadorEmpleados](owner, new BuscadorEmpleados) {
  getModelObject.search

  override def windowDefinition: Renderizable =
    LayoutVertical(
      TableWidget[Empleado](
        bindItemsTo = "empleados",
        bindSelectionTo = "empleadoSeleccionado",
        height = 250,
        TableColumn(bindTo = Left("nombre")),
        TableColumn(title = "Fechas", width = 400, bindTo = Left("restriccionesUserFriendly"))
      )
    )
}
