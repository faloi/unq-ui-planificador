package edu.unq.uis.planificador.ui.widgets

import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.layout.VerticalLayout

trait FormBuilder {
  def title: String

  def fields: Map[String, String]

  def setTitle(title: String): Any

  def addAdditionalContent(mainPanel: Panel) = {}

  def createFormPanel(mainPanel: Panel) {
    setTitle(title)
    mainPanel.setLayout(new VerticalLayout)

    fields.foreach(createField(mainPanel, _))

    addAdditionalContent(mainPanel)
  }

  def createField(panel: Panel, labelAndProperty: (String, String)) = {
    new Field(panel)
      .setText(labelAndProperty._1)
      .bindValueToProperty(labelAndProperty._2)
  }
}
