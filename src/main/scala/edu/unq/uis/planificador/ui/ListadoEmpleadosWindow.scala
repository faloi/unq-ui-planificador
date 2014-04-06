package edu.unq.uis.planificador.ui

import org.uqbar.arena.windows.{Dialog, WindowOwner, SimpleWindow}
import edu.unq.uis.planificador.Empleado
import org.uqbar.arena.widgets.{Button, Panel}
import org.uqbar.arena.aop.potm.Function
import org.uqbar.arena.widgets.tables.{Column, Table}
import org.uqbar.commons.utils.Observable
import edu.unq.uis.planificador.dependencyInjection.DevEnvironment

@Observable
class BuscadorEmpleados extends DevEnvironment {
  var empleados: java.util.List[Empleado] = _

  def search {
    empleados = empleadoHome.allInstances()
  }
}

class ListadoEmpleadosWindow(parent: WindowOwner) extends SimpleWindow[BuscadorEmpleados](parent, new BuscadorEmpleados) {
  getModelObject.search

  override def addActions(actionsPanel: Panel) = {
    new Button(actionsPanel)
      .setCaption("Nuevo")
      .onClick(new Function(() => this.openDialog(new CrearEmpleadoWindow(this))))
  }

  override def createFormPanel(mainPanel: Panel): Unit = {
    this.setTitle("Empleados")
    this.createResultsGrid(mainPanel)
  }

  def createResultsGrid(panel: Panel) {
    val table = new Table[Empleado](panel)
    table.bindItemsToProperty("empleados")

    this.describeResultsGrid(table)
  }

  def describeResultsGrid(table: Table[Empleado]) {
    new Column[Empleado](table)
      .setTitle("Nombre")
      .setFixedSize(150)
      .bindContentsToProperty("nombre")

    new Column[Empleado](table)
      .setTitle("Apellido")
      .setFixedSize(150)
      .bindContentsToProperty("apellido")

    new Column[Empleado](table)
      .setTitle("Legajo")
      .setFixedSize(100)
      .bindContentsToProperty("legajo")
  }

  private def openDialog(dialog: Dialog[_]) {
    dialog.onAccept(new Function(() => getModelObject.search))
    dialog.open
  }
}

