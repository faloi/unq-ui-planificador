package edu.unq.uis.planificador.ui.planificacion

import org.uqbar.arena.windows.{Dialog, WindowOwner}
import edu.unq.uis.planificador.domain.{Planificacion, Empleado}
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.tables.{Column, Table}
import edu.unq.uis.planificador.ui.ArenaScalaExtensions._

class VerHorariosDialog(owner: WindowOwner, planificacion: Planificacion) extends Dialog[Planificacion](owner, planificacion) {
  override def createFormPanel(mainPanel: Panel) = {
    val table = new Table[Empleado](this, classOf[Empleado])
    table.bindItemsToProperty("empleados")

    new Column[Empleado](table)
      .bindContentsToProperty("nombre")

    new Column[Empleado](table)
      .bindContentsToTransformer((empleado: Empleado) => getModelObject.asignacionDe(empleado).calendarSpace.entrada)
      .setTitle("Entrada")

    new Column[Empleado](table)
      .bindContentsToTransformer((empleado: Empleado) => getModelObject.asignacionDe(empleado).calendarSpace.salida)
      .setTitle("Salida")
  }
}
