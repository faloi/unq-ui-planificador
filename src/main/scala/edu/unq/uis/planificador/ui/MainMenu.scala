package edu.unq.uis.planificador.ui

import org.uqbar.arena.windows.WindowOwner
import edu.unq.uis.planificador.ui.widgets.NiceWindow
import edu.unq.uis.planificador.applicationModel.DummyModel
import edu.unq.uis.planificador.ui.empleado.ListadoEmpleadosWindow
import edu.unq.uis.planificador.domain.Planificacion
import edu.unq.uis.planificador.dependencyInjection.DevEnvironment
import org.joda.time.LocalDate

class MainMenu(parent: WindowOwner) extends NiceWindow[DummyModel](parent, new DummyModel) with DevEnvironment {

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
          onClick = () => openNew(new VerPlanificacionWindow(this, new Planificacion(new LocalDate().toDateTimeAtStartOfDay, empleadoHome)))
        )
      )

    )

}

