package edu.unq.uis.planificador.ui.empleado

import org.uqbar.arena.windows.WindowOwner
import edu.unq.uis.planificador.domain.Empleado

class CrearEmpleadoDialog(owner: WindowOwner) extends EditarEmpleadoDialog(owner, new Empleado) {
  override val title = "Nuevo empleado"

  override def executeTask() {
    empleadoHome.create(this.getModelObject)
    super.executeTask()
  }
}

