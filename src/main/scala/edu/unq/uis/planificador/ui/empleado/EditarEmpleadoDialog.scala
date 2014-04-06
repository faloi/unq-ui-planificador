package edu.unq.uis.planificador.ui.empleado

import org.uqbar.arena.windows.{Dialog, WindowOwner}
import org.uqbar.arena.widgets._
import org.uqbar.arena.aop.potm.Function
import edu.unq.uis.planificador.domain.Empleado
import edu.unq.uis.planificador.ui.widgets.FormBuilder

class EditarEmpleadoDialog(owner: WindowOwner, model: Empleado) extends Dialog[Empleado](owner, model) with FormBuilder {
  override def addActions(actionsPanel: Panel) {
    new Button(actionsPanel)
      .setCaption("Guardar")
      .onClick(new Function(() => this.accept))
      .setAsDefault

    new Button(actionsPanel)
      .setCaption("Cancelar")
      .onClick(new Function(() => this.cancel))
  }

  def title = "Editando " + getModelObject.nombreCompleto

  val fields = Map(
    "Nombre" -> "nombre",
    "Apellido" -> "apellido",
    "Legajo" -> "legajo"
  )
}

