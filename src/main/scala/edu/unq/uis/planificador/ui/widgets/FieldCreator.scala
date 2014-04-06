package edu.unq.uis.planificador.ui.widgets

import org.uqbar.arena.widgets.Panel

trait FieldCreator {
  def createField(panel: Panel, labelAndProperty: (String, String)) = {
    new Field(panel)
      .setText(labelAndProperty._1)
      .bindValueToProperty(labelAndProperty._2)
  }

  def createFields(panel: Panel, fields: Map[String, String]) = {
    fields.foreach(createField(panel, _))
  }
}
