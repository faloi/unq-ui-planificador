package edu.unq.uis.planificador.ui

import org.uqbar.arena.windows.{WindowOwner, Dialog}
import org.uqbar.arena.widgets.{Selector, Button, Panel}
import edu.unq.uis.planificador.ui.widgets.FormBuilder
import edu.unq.uis.planificador.applicationModel.DisponibilidadCreator
import edu.unq.uis.planificador.applicationModel.Converters.EmpleadoModel
import ArenaScalaExtensions._
import org.uqbar.arena.bindings.{PropertyAdapter, ObservableProperty}
import edu.unq.uis.planificador.domain.calendar.DiaDeSemana

class AgregarDisponibilidadDialog(owner: WindowOwner, empleado: EmpleadoModel)
  extends Dialog[DisponibilidadCreator](owner, new DisponibilidadCreator(empleado)) with FormBuilder {

  val title = "Agregar disponibilidad"

  val fields = Map(
    "Desde" -> "entity.inicio",
    "Hasta" -> "entity.fin"
  )

  onAccept(() => this.getModelObject.agregarDisponibilidad)

  override def addAdditionalContent(mainPanel: Panel) {
    val selector = new Selector[DiaDeSemana](mainPanel)
    selector
      .allowNull(false)
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
