package edu.unq.uis.planificador.ui.widgets

import org.uqbar.arena.widgets.{TextBox, Label, Panel, Container}
import org.uqbar.arena.layout.ColumnLayout

class Field(container: Container) extends Panel(container) {
  setLayout(new ColumnLayout(2))

  val label = new Label(this)
  label.setWidth(100)

  val textBox = new TextBox(this).setWidth(250)

  def setText(text: String): Field = {
    label.setText(text)
    this
  }

  def bindValueToProperty(propertyName: String): Field = {
    textBox.bindValueToProperty(propertyName)
    this
  }
}
