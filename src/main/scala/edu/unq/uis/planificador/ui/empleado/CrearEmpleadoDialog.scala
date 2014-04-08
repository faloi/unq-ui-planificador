package edu.unq.uis.planificador.ui.empleado

import org.uqbar.arena.windows.WindowOwner
import edu.unq.uis.planificador.domain.Empleado
import edu.unq.uis.planificador.dependencyInjection.DevEnvironment

class CrearEmpleadoDialog(owner: WindowOwner) extends EditarEmpleadoDialog(owner, new Empleado) with DevEnvironment {
  override val title = "Nuevo empleado"

  override def executeTask() {
    empleadoHome.create(this.getModelObject.self)
    super.executeTask()
  }
}

