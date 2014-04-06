package edu.unq.uis.planificador.ui

import edu.unq.uis.planificador.Empleado
import org.uqbar.arena.windows.WindowOwner

class CrearEmpleadoWindow(owner: WindowOwner) extends EditarEmpleadoWindow(owner, new Empleado) {
  override def executeTask() {
    empleadoHome.create(this.getModelObject)
    super.executeTask()
  }
}

