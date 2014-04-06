package edu.unq.uis.planificador.disponibilidad


sealed trait Disponibilidad {def razon: String}

case object Restriccion extends Disponibilidad  {val razon = "No trabaja este día"}
case object Asignacion extends Disponibilidad   {val razon = "Ya tiene un turno asignado"}
case object Disponible extends Disponibilidad   {val razon = "Disponible"}
case object NoDisponible extends Disponibilidad {val razon = "No se encuentra disponible para este día y rango"}
