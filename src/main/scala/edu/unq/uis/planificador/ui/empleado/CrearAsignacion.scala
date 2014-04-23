package edu.unq.uis.planificador.ui.empleado

import org.uqbar.arena.windows.WindowOwner
import edu.unq.uis.planificador.ui.widgets.NiceWindow
import edu.unq.uis.planificador.applicationModel.empleado.BuscadorEmpleados
import edu.unq.uis.planificador.domain.{Empleado, Planificacion}
import edu.unq.uis.planificador.ui.ArenaScalaExtensions
import ArenaScalaExtensions._
import edu.unq.uis.planificador.domain.disponibilidad._
import org.uqbar.commons.utils.Observable
import org.uqbar.arena.bindings.PropertyAdapter
import org.joda.time.format.DateTimeFormat
import org.joda.time.DateTime
import edu.unq.uis.planificador.domain.calendar.CalendarElement
import edu.unq.uis.planificador.ui.empleado.Hora
import edu.unq.uis.planificador.domain.Planificacion

@Observable
class NuevaAsignacionModel{
  val buscador: BuscadorEmpleados = new BuscadorEmpleados
  val turnos: Seq[Hora] = (0 until 24).map( (i:Int) => new Hora(i))
  var ini: Hora = new Hora(0)
  var fin: Hora = new Hora(0)
  var fecha : DateTime = _

  def setFecha(f: DateTime){
    this.fecha = f
  }

  def orderByEstado(){
    this.buscador.search
    this.buscador.empleados = buscador.empleados.sortWith(
      (e1: Empleado, e2: Empleado) => (
        e1.disponibilidadPara(Turno el fecha de ini.num a fin.num).disponibilidad,
        e2.disponibilidadPara(Turno el fecha de ini.num a fin.num).disponibilidad
      ) match {
        case (Disponible, _) => true
        case (_, Disponible) => false

        case (Asignacion, NoDisponible) => false
        case (NoDisponible, Asignacion) => true

        case (Restriccion, _) => false
        case (_, Restriccion) => true

        case _ => true

      }
    )
  }

}

@Observable
case class Hora(num: Int){
  def readable = num + ":00"
}

class CrearAsignacion (parent: WindowOwner,planificacion: Planificacion ) extends NiceWindow[NuevaAsignacionModel](parent, new NuevaAsignacionModel) {
  getModelObject.buscador.search
  getModelObject.setFecha(planificacion.fecha)
  override def windowDefinition: Renderizable =

    LayoutVertical(
      Etiqueta("Agregar una asignación para el " + DateTimeFormat.forPattern("dd/MM/yyyy").print(planificacion.fecha)),
      LayoutHorizontal(
        Etiqueta("Desde:"),
        DropDown[NuevaAsignacionModel]("ini", "turnos", new PropertyAdapter(classOf[Hora], "readable")),
        Etiqueta("Hasta:"),
        DropDown[NuevaAsignacionModel]("fin", "turnos", new PropertyAdapter(classOf[Hora], "readable")),
        Boton(
          label = "Buscar", () => getModelObject.orderByEstado()
        )
      ),
      TableWidget[Empleado](
        bindItemsTo = "buscador.empleados",
        bindSelectionTo = "buscador.empleadoSeleccionado",
        height = 400,
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
