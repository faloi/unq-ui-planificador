package edu.unq.uis.planificador.ui

import org.uqbar.arena.windows.WindowOwner
import edu.unq.uis.planificador.ui.widgets.NiceWindow
import edu.unq.uis.planificador.applicationModel.DummyModel
import edu.unq.uis.planificador.ui.empleado.ListadoEmpleadosNiceWindow
import edu.unq.uis.planificador.ui.planificacion.PlanificacionDeLaSemana

class MainMenu (parent: WindowOwner) extends NiceWindow[DummyModel](parent, new DummyModel){

  override def windowDefinition: Renderizable =

    LayoutVertical(

      LayoutVertical(
        Boton(
          label = "Buscar Empleado",
          onClick = () => openNew(new ListadoEmpleadosNiceWindow(this))
        ),
        Boton(
          label = "Buscar Planificación",
          onClick = () => openNew(new PlanificacionDeLaSemana(this))
        )
      )

    )

}

