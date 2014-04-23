package edu.unq.uis.planificador.ui.empleado

import edu.unq.uis.planificador.ui.widgets.NiceWindow
import org.uqbar.commons.utils.Observable
import org.uqbar.arena.windows.WindowOwner
import edu.unq.uis.planificador.domain.Empleado

@Observable
class RestriccionCreator(empleado: Empleado) {

}

class AgregarRestriccionDialog(owner: WindowOwner, empleado: Empleado) extends NiceWindow[RestriccionCreator](owner, new RestriccionCreator(empleado)) {
  override def windowDefinition: Renderizable =
    LayoutHorizontal(
      Boton(label = "Aceptar", onClick = () => {})
    )
}
