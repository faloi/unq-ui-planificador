package edu.unq.uis.planificador.ui

import edu.unq.uis.planificador.dependencyInjection.environments.DevEnvironment
import edu.unq.uis.planificador.Empleado
import org.uqbar.arena.layout._
import org.uqbar.arena.widgets.{Button, TextBox, Label, Panel}
import org.uqbar.arena.windows.{Dialog, WindowOwner}
import org.uqbar.arena.aop.potm.Function

class CrearEmpleadoWindow(owner: WindowOwner) extends Dialog[Empleado](owner, new Empleado()) with DevEnvironment {
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

  override def executeTask() {
    empleadoHome.create(this.getModelObject)
    super.executeTask()
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