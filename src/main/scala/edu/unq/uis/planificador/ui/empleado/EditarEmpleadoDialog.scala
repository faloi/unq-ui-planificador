package edu.unq.uis.planificador.ui.empleado

import org.uqbar.arena.windows.{Dialog, WindowOwner}
import org.uqbar.arena.widgets._
import org.uqbar.arena.aop.potm.Function
import edu.unq.uis.planificador.ui.widgets.FormBuilder
import org.uqbar.arena.widgets.tables.{Column, Table}
import edu.unq.uis.planificador.domain.calendar.RecurrentCalendarSpace
import edu.unq.uis.planificador.applicationModel.DisponibilidadHoraria
import edu.unq.uis.planificador.applicationModel.Converters.EmpleadoModel
import org.uqbar.arena.layout.{VerticalLayout, ColumnLayout}

class EditarEmpleadoDialog(owner: WindowOwner, model: EmpleadoModel) extends Dialog[EmpleadoModel](owner, model) with FormBuilder {
  override def addActions(actionsPanel: Panel) {
    new Button(actionsPanel)
      .setCaption("Guardar")
      .onClick(new Function(() => this.accept))
      .setAsDefault

    new Button(actionsPanel)
      .setCaption("Cancelar")
      .onClick(new Function(() => this.cancel))
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
    new Button(accionesPanel).setCaption("Agregar")
    new Button(accionesPanel).setCaption("Eliminar")
  }

  def createDisponibilidadTable(disponibilidadesPanel: Panel) {
    val SMALL_COLUMN = 55
    val LARGE_COLUMN = 70

    val table = new Table[DisponibilidadHoraria](disponibilidadesPanel, classOf[DisponibilidadHoraria])
    table.bindItemsToProperty("disponibilidades")
    table.setWidth(LARGE_COLUMN + SMALL_COLUMN + SMALL_COLUMN)

    new Column[DisponibilidadHoraria](table)
      .setTitle("Dia")
      .setFixedSize(LARGE_COLUMN)
      .bindContentsToProperty("dia")

    new Column[DisponibilidadHoraria](table)
      .setTitle("Desde")
      .setFixedSize(SMALL_COLUMN)
      .bindContentsToProperty("inicio")

    new Column[DisponibilidadHoraria](table)
      .setTitle("Hasta")
      .setFixedSize(SMALL_COLUMN)
      .bindContentsToProperty("fin")
  }
}

