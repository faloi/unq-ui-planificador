package edu.unq.uis.planificador.ui.empleado

import edu.unq.uis.planificador.ui.widgets.NiceWindow
import org.uqbar.arena.windows.{Dialog, WindowOwner}
import edu.unq.uis.planificador.domain.Empleado
import edu.unq.uis.planificador.applicationModel.empleado.BuscadorEmpleados
import edu.unq.uis.planificador.ui.ArenaScalaExtensions._

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
      ),

      LayoutHorizontal(
        Boton(label = "Agregar", onClick = () => openDialog(new AgregarRestriccionDialog(this, getModelObject.empleadoSeleccionado))),
        Boton(label = "Eliminar", onClick = () => openDialog(new EliminarRestriccionDialog(this, getModelObject.empleadoSeleccionado)))
      )
    )

  def openDialog(dialog: Dialog[_]) = {
    dialog.onAccept(() => getModelObject.search)
    dialog.open()
  }
}
