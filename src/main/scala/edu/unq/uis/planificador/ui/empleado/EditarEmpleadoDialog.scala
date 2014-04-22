package edu.unq.uis.planificador.ui.empleado

import org.uqbar.arena.windows.{Dialog, WindowOwner}
import org.uqbar.arena.widgets._
import edu.unq.uis.planificador.ui.widgets.FormBuilder
import org.uqbar.arena.widgets.tables.{Column, Table}
import org.uqbar.arena.layout.{VerticalLayout, ColumnLayout}
import edu.unq.uis.planificador.ui.{ArenaScalaExtensions, AgregarDisponibilidadDialog}
import ArenaScalaExtensions._
import edu.unq.uis.planificador.domain.calendar.RecurrentCalendarSpace
import edu.unq.uis.planificador.domain.Empleado
import edu.unq.uis.planificador.applicationModel.empleado.EmpleadoEditor

class EditarEmpleadoDialog(owner: WindowOwner, empleado: Empleado) extends Dialog[EmpleadoEditor](owner, new EmpleadoEditor(empleado))
with FormBuilder {

  override def addActions(actionsPanel: Panel) {
    new Button(actionsPanel)
      .setCaption("Guardar")
      .onClick(() => this.accept)
      .setAsDefault

    new Button(actionsPanel)
      .setCaption("Cancelar")
      .onClick(() => this.cancel)
  }

  def title = s"Editando ${getModelObject.entity.nombreCompleto}"

  val fields = Map(
    "Nombre" -> "entity.nombre",
    "Apellido" -> "entity.apellido",
    "Legajo" -> "entity.legajo"
  )

  override def addAdditionalContent(mainPanel: Panel) = {
    val disponibilidadesPanel = new Panel(mainPanel).setLayout(new ColumnLayout(3))

    new Label(disponibilidadesPanel).setText("Disponibilidad")

    createDisponibilidadTable(disponibilidadesPanel)

    val accionesPanel = new Panel(disponibilidadesPanel).setLayout(new VerticalLayout)

    new Button(accionesPanel)
      .setCaption("Agregar")
      .onClick(() => new AgregarDisponibilidadDialog(this, this.getModelObject.entity).open())

    new Button(accionesPanel)
      .setCaption("Eliminar")
      .onClick(() => this.getModelObject.eliminarDisponibilidad)
  }

  def createDisponibilidadTable(disponibilidadesPanel: Panel) {
    val SMALL_COLUMN = 55
    val LARGE_COLUMN = 80

    val table = new Table[RecurrentCalendarSpace](disponibilidadesPanel, classOf[RecurrentCalendarSpace])
    table.bindItemsToProperty("entity.disponibilidades")
    table.bindSelectionToProperty("disponibilidadSeleccionada")
    table.setWidth(LARGE_COLUMN + SMALL_COLUMN + SMALL_COLUMN)

    new Column[RecurrentCalendarSpace](table)
      .setTitle("Dia")
      .setFixedSize(LARGE_COLUMN)
      .bindContentsToProperty("diaDeSemana")

    new Column[RecurrentCalendarSpace](table)
      .setTitle("Desde")
      .setFixedSize(SMALL_COLUMN)
      .bindContentsToProperty("inicio")

    new Column[RecurrentCalendarSpace](table)
      .setTitle("Hasta")
      .setFixedSize(SMALL_COLUMN)
      .bindContentsToProperty("fin")
  }
}