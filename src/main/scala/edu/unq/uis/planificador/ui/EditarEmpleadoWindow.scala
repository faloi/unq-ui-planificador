package edu.unq.uis.planificador.ui

import org.uqbar.arena.windows.{Dialog, WindowOwner}
import edu.unq.uis.planificador.Empleado
import edu.unq.uis.planificador.dependencyInjection.DevEnvironment
import org.uqbar.arena.widgets.{TextBox, Label, Button, Panel}
import org.uqbar.arena.layout.{ColumnLayout, VerticalLayout}
import org.uqbar.arena.aop.potm.Function

class EditarEmpleadoWindow(owner: WindowOwner, model: Empleado) extends Dialog[Empleado](owner, model) with DevEnvironment {
  override def addActions(actionsPanel: Panel) {
    new Button(actionsPanel)
      .setCaption("Guardar")
      .onClick(new Function(() => this.accept))
      .setAsDefault

    new Button(actionsPanel)
      .setCaption("Cancelar")
      .onClick(new Function(() => this.cancel))
  }

  override def createFormPanel(mainPanel: Panel) {
    this.setTitle("Nuevo empleado")
    mainPanel.setLayout(new VerticalLayout)

    createTextBoxWithLabel(mainPanel, "Nombre", "nombre")
    createTextBoxWithLabel(mainPanel, "Apellido", "apellido")
    createTextBoxWithLabel(mainPanel, "Legajo", "legajo")
  }

  private def createTextBoxWithLabel(mainPanel: Panel, text: String, propertyName: String) {
    val fieldPanel = new Panel(mainPanel)
      .setLayout(new ColumnLayout(2))

    new Label(fieldPanel)
      .setText(text)
      .setWidth(100)

    new TextBox(fieldPanel)
      .setWidth(250)
      .bindValueToProperty(propertyName)
  }
}
