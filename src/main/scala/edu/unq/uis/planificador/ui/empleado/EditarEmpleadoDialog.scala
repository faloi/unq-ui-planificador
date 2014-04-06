package edu.unq.uis.planificador.ui.empleado

import org.uqbar.arena.windows.{Dialog, WindowOwner}
import org.uqbar.arena.widgets._
import org.uqbar.arena.layout.VerticalLayout
import org.uqbar.arena.aop.potm.Function
import edu.unq.uis.planificador.domain.Empleado
import edu.unq.uis.planificador.ui.widgets.FieldCreator

class EditarEmpleadoDialog(owner: WindowOwner, model: Empleado) extends Dialog[Empleado](owner, model) with FieldCreator {
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

  override def createFormPanel(mainPanel: Panel) {
    setTitle(title)
    mainPanel.setLayout(new VerticalLayout)

    createFields(mainPanel, Map(
      "Nombre" -> "nombre",
      "Apellido" -> "apellido",
      "Legajo" -> "legajo")
    )
  }
}

