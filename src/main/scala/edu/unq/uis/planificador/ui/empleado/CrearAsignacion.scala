package edu.unq.uis.planificador.ui.empleado

import org.uqbar.arena.windows.WindowOwner
import edu.unq.uis.planificador.ui.widgets.NiceWindow
import edu.unq.uis.planificador.applicationModel.empleado.BuscadorEmpleados
import edu.unq.uis.planificador.domain.{Empleado, Planificacion}
import edu.unq.uis.planificador.ui.ArenaScalaExtensions
import ArenaScalaExtensions._
import edu.unq.uis.planificador.domain.disponibilidad.Turno
import org.uqbar.commons.utils.Observable
import org.uqbar.arena.bindings.PropertyAdapter
import org.joda.time.format.DateTimeFormat

@Observable
class NuevaAsignacionModel{
  val buscador: BuscadorEmpleados = new BuscadorEmpleados
  val turnos: Seq[Hora] = (0 until 24).map( (i:Int) => new Hora(i))
  var ini: Hora = new Hora(0)
  var fin: Hora = new Hora(0)
}

@Observable
case class Hora(num: Int){
  def readable = num + ":00"
}

class CrearAsignacion (parent: WindowOwner,planificacion: Planificacion ) extends NiceWindow[NuevaAsignacionModel](parent, new NuevaAsignacionModel) {
  getModelObject.buscador.search

  override def windowDefinition: Renderizable =

    LayoutVertical(
      Etiqueta("Agregar una asignación para el " + DateTimeFormat.forPattern("dd/MM/yyyy").print(planificacion.fecha)),
      LayoutHorizontal(
        Etiqueta("Desde:"),
        DropDown[NuevaAsignacionModel]("ini", "turnos", new PropertyAdapter(classOf[Hora], "readable")),
        Etiqueta("Hasta:"),
        DropDown[NuevaAsignacionModel]("fin", "turnos", new PropertyAdapter(classOf[Hora], "readable")),
        Boton(
          label = "Buscar", () => getModelObject.buscador.search
        )
      ),
      TableWidget[Empleado](
        bindItemsTo = "buscador.empleados",
        bindSelectionTo = "buscador.empleadoSeleccionado",
        height = 800,
        TableColumn(width = 80, bindTo = Left("nombre") ),
        TableColumn(width = 80, bindTo = Right((e:Empleado) =>
          e.disponibilidadPara ( Turno.el(planificacion.fecha)
            .de(getModelObject().ini.num).a(getModelObject().ini.num) ).disponibilidad.razon)
        )
      ),
      Boton(
        label = "Asignar",
        onClick = () => {getModelObject.buscador.empleadoSeleccionado
          .asignar( Turno el planificacion.fecha de getModelObject.ini.num a getModelObject.fin.num); this.accept();}
      )
    )
}
