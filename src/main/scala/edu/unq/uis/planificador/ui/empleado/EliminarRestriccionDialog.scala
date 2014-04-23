package edu.unq.uis.planificador.ui.empleado

import edu.unq.uis.planificador.ui.widgets.NiceWindow
import org.uqbar.commons.utils.Observable
import org.uqbar.arena.windows.WindowOwner
import edu.unq.uis.planificador.domain.Empleado
import org.uqbar.arena.bindings.PropertyAdapter
import edu.unq.uis.planificador.domain.calendar.CalendarElement

@Observable
class EliminarRestriccion(empleado: Empleado) {
  var restricciones = empleado.restricciones
  var restriccionSeleccionada: CalendarElement = _

  def eliminar() = {
    empleado.borrarRestriccion(restriccionSeleccionada)
  }
}

class EliminarRestriccionDialog(owner: WindowOwner, empleado: Empleado) extends NiceWindow[EliminarRestriccion](owner, new EliminarRestriccion(empleado)) {
  override def windowDefinition: Renderizable =
    LayoutVertical(
      DropDown(
        bindTo = "restriccionSeleccionada",
        property = "restricciones",
        adapter = new PropertyAdapter(classOf[CalendarElement], "fechaUserFriendly")
      ),

      Boton(label = "Eliminar", onClick = () => {
        getModelObject.eliminar(); accept()
      })
    )
}
