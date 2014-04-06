package edu.unq.uis.planificador.ui

import org.uqbar.arena.windows.WindowOwner
import edu.unq.uis.planificador.domain.Empleado

class CrearEmpleadoWindow(owner: WindowOwner) extends EditarEmpleadoWindow(owner, new Empleado) {
  override def executeTask() {
    empleadoHome.create(this.getModelObject)
    super.executeTask()
  }
}

