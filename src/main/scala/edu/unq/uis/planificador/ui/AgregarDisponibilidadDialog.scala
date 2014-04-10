package edu.unq.uis.planificador.ui

import org.uqbar.arena.windows.{WindowOwner, Dialog}
import org.uqbar.arena.widgets.{Label, Selector, Button, Panel}
import edu.unq.uis.planificador.ui.widgets.FormBuilder
import ArenaScalaExtensions._
import org.uqbar.arena.bindings.{PropertyAdapter, ObservableProperty}
import edu.unq.uis.planificador.domain.calendar.DiaDeSemana
import edu.unq.uis.planificador.domain.Empleado
import edu.unq.uis.planificador.applicationModel.disponibilidad.DisponibilidadCreator
import org.uqbar.arena.layout.ColumnLayout

class AgregarDisponibilidadDialog(owner: WindowOwner, empleado: Empleado)
  extends Dialog[DisponibilidadCreator](owner, new DisponibilidadCreator(empleado)) with FormBuilder {

  val title = "Agregar disponibilidad"

  val fields = Map(
    "Desde" -> "entity.inicio",
    "Hasta" -> "entity.fin"
  )

  onAccept(() => this.getModelObject.agregarDisponibilidad)

  override def addAdditionalContent(mainPanel: Panel) {
    val container = new Panel(mainPanel)
    container.setLayout(new ColumnLayout(2))

    new Label(container).setText("Dia").setWidth(100)

    val selector = new Selector[DiaDeSemana](container)
    selector
      .allowNull(false)
      .setWidth(250)
      .bindValueToProperty("entity.diaDeSemana")

    selector
      .bindItems(new ObservableProperty("diasSeleccionables"))
      .setAdapter(new PropertyAdapter(classOf[DiaDeSemana], "nombre"))
  }

  override def addActions(actionsPanel: Panel) {
    new Button(actionsPanel)
      .setCaption("Guardar")
      .onClick(() => this.accept)
      .setAsDefault

    new Button(actionsPanel)
      .setCaption("Cancelar")
      .onClick(() => this.cancel)
  }
}
