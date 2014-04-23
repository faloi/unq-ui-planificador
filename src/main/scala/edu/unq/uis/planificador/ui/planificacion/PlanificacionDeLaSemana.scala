package edu.unq.uis.planificador.ui.planificacion

import org.uqbar.arena.windows.{Dialog, WindowOwner}
import edu.unq.uis.planificador.ui.widgets.NiceWindow
import edu.unq.uis.planificador.domain.Planificacion
import org.uqbar.arena.aop.potm.Function
import edu.unq.uis.planificador.applicationModel.planificacion.BuscadorPlanificacion
import org.joda.time.format.DateTimeFormat
import edu.unq.uis.planificador.ui.{VerPlanificacionWindow, ArenaScalaExtensions}
import ArenaScalaExtensions._

class PlanificacionDeLaSemana(parent: WindowOwner) extends NiceWindow[BuscadorPlanificacion](parent, new BuscadorPlanificacion) {
  getModelObject.search

  override def windowDefinition: Renderizable =

    LayoutVertical(
      TableWidget[Planificacion](
        bindItemsTo = "planificaciones",
        bindSelectionTo = "planificacionSeleccionada",
        height = 600,
        TableColumn(bindTo = Right((p: Planificacion) => DateTimeFormat.forPattern("dd/MM/yyyy").print(p.fecha))),
        TableColumn(bindTo = Left("estado"))
      ),
      LayoutHorizontal(
        Boton(
          label = "Planificar",
          onClick = () => new VerPlanificacionWindow(this, getModelObject.planificacionSeleccionada).open()
        ),
        Boton(
          label = "Ver Horarios",
          onClick = () => openDialog(new VerHorariosDialog(this, getModelObject.planificacionSeleccionada))
        )
      )

    )

  def openDialog(dialog: Dialog[_]) {
    dialog.onAccept(new Function(() => getModelObject.search))
    dialog.open()
  }

}

