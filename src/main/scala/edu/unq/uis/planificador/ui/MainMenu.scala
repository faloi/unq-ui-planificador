package edu.unq.uis.planificador.ui

import org.uqbar.arena.windows.WindowOwner
import edu.unq.uis.planificador.ui.widgets.NiceWindow
import edu.unq.uis.planificador.applicationModel.DummyModel
import edu.unq.uis.planificador.ui.empleado.{ListadoRestriccionesDialog, ListadoEmpleadosWindow}
import edu.unq.uis.planificador.ui.planificacion.PlanificacionDeLaSemana

class MainMenu(parent: WindowOwner) extends NiceWindow[DummyModel](parent, new DummyModel) {

  override def windowDefinition: Renderizable =

      LayoutVertical(
        Boton(
          label = "Buscar Empleado",
          onClick = () => openNew(new ListadoEmpleadosWindow(this))
        ),
        Boton(
          label = "Planificación Semanal",
          onClick = () => openNew(new PlanificacionDeLaSemana(this))
        ),
        Boton(
          label = "Ver restricciones",
          onClick = () => openNew(new ListadoRestriccionesDialog(this))
        )
      )

}

