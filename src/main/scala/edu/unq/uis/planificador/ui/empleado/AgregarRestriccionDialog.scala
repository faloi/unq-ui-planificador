package edu.unq.uis.planificador.ui.empleado

import edu.unq.uis.planificador.ui.widgets.NiceWindow
import org.uqbar.commons.utils.Observable
import org.uqbar.arena.windows.WindowOwner
import edu.unq.uis.planificador.domain.Empleado

@Observable
class RestriccionCreator(empleado: Empleado) {
  var fecha: String = _

  def crear() = {
    empleado.restriccionEl(fecha)
  }
}

class AgregarRestriccionDialog(owner: WindowOwner, empleado: Empleado) extends NiceWindow[RestriccionCreator](owner, new RestriccionCreator(empleado)) {
  override def windowDefinition: Renderizable =
    LayoutVertical(
      LayoutColumn(
        count = 2,

        Etiqueta(texto = "Fecha"),
        Input(bindTo = "fecha")
      ),

      Boton(label = "Aceptar", onClick = () => {
        getModelObject.crear(); accept()
      })
    )
}
