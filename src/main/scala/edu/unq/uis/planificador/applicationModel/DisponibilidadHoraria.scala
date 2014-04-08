package edu.unq.uis.planificador.applicationModel

import org.uqbar.commons.utils.Observable

@Observable
case class DisponibilidadHoraria(dia: String, inicio: Int, fin: Int)
