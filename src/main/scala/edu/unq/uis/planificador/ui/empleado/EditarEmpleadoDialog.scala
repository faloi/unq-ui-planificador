package edu.unq.uis.planificador.ui.empleado

import org.uqbar.arena.windows.{Dialog, WindowOwner}
import org.uqbar.arena.widgets._
import org.uqbar.arena.aop.potm.Function
import edu.unq.uis.planificador.ui.widgets.FormBuilder
import org.uqbar.arena.widgets.tables.{Column, Table}
import org.uqbar.arena.layout.{VerticalLayout, ColumnLayout}
import edu.unq.uis.planificador.ui.{ArenaScalaExtensions, AgregarDisponibilidadDialog}
import ArenaScalaExtensions._
import edu.unq.uis.planificador.domain.calendar.RecurrentCalendarSpace
import edu.unq.uis.planificador.domain.Empleado

class EditarEmpleadoDialog(owner: WindowOwner, model: Empleado) extends Dialog[Empleado](owner, model) with FormBuilder {
  override def addActions(actionsPanel: Panel) {
    new Button(actionsPanel)
      .setCaption("Guardar")
      .onClick(() => this.accept)
      .setAsDefault

    new Button(actionsPanel)
      .setCaption("Cancelar")
      .onClick(() => this.cancel)
  }

  def title = s"Editando ${getModelObject.nombreCompleto}"

  val fields = Map(
    "Nombre" -> "nombre",
    "Apellido" -> "apellido",
    "Legajo" -> "legajo"
  )

  override def addAdditionalContent(mainPanel: Panel) = {
    val disponibilidadesPanel = new Panel(mainPanel).setLayout(new ColumnLayout(3))

    new Label(disponibilidadesPanel).setText("Disponibilidad")

    createDisponibilidadTable(disponibilidadesPanel)

    val accionesPanel = new Panel(disponibilidadesPanel).setLayout(new VerticalLayout)
    new Button(accionesPanel)
      .setCaption("Agregar")
      .onClick(new Function(() => new AgregarDisponibilidadDialog(this, this.getModelObject).open()))
    new Button(accionesPanel).setCaption("Eliminar")
  }

  def createDisponibilidadTable(disponibilidadesPanel: Panel) {
    val SMALL_COLUMN = 55
    val LARGE_COLUMN = 80

    val table = new Table[RecurrentCalendarSpace](disponibilidadesPanel, classOf[RecurrentCalendarSpace])
    table.bindItemsToProperty("disponibilidades")
    table.setWidth(LARGE_COLUMN + SMALL_COLUMN + SMALL_COLUMN)
    table.setHeigth(200)

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

