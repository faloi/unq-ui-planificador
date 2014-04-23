package edu.unq.uis.planificador.ui

import org.uqbar.arena.windows.WindowOwner
import edu.unq.uis.planificador.ui.widgets.NiceWindow
import edu.unq.uis.planificador.applicationModel.DummyModel
import edu.unq.uis.planificador.ui.empleado.ListadoEmpleadosWindow
import edu.unq.uis.planificador.ui.planificacion.PlanificacionDeLaSemana
import edu.unq.uis.planificador.domain.Planificacion
import org.joda.time.DateTime

class MainMenu(parent: WindowOwner) extends NiceWindow[DummyModel](parent, new DummyModel) {

  override def windowDefinition: Renderizable =

    LayoutVertical(

      LayoutVertical(
        Boton(
          label = "Buscar Empleado",
          onClick = () => openNew(new ListadoEmpleadosWindow(this))
        ),
        Boton(
          label = "Planificar Día",
          onClick = () => openNew(new ListadoEmpleadosWindow(this))
        ),
        Boton(
          label = "Ver planificacion",
          onClick = () => openNew(new VerPlanificacionWindow(this, new Planificacion(new DateTime("2014-04-21"))))
        ),
        Boton(
          label = "Buscar Planificación",
          onClick = () => openNew(new PlanificacionDeLaSemana(this))
        )
      )

    )

}

