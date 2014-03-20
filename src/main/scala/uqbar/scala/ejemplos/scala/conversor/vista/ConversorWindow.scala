package uqbar.scala.ejemplos.scala.conversor.vista

import java.awt.Color

import org.uqbar.arena.actions.MessageSend
import org.uqbar.arena.layout.VerticalLayout
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.Window
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.arena.Application

import uqbar.scala.ejemplos.scala.conversor.modelo.Conversor

/**
 * 
 * @author uqbar
 */
class ConversorSimpleWindow(owner:WindowOwner) extends SimpleWindow[Conversor](owner, new Conversor()) {

	override def addActions(actionsPanel:Panel) = {
		new Button(actionsPanel) //
			.setCaption("Convertir a kilómetros")
			.onClick(new MessageSend(this.getModelObject(), "convertir"))
	}

	override def createFormPanel(mainPanel:Panel ) {
		this.setTitle("Conversor de millas a kilómetros")
		mainPanel.setLayout(new VerticalLayout())

		new Label(mainPanel).setText("Ingrese la longitud en millas")

		new TextBox(mainPanel).bindValueToProperty("millas")

		new Label(mainPanel) //
			.setBackground(Color.ORANGE)
			.bindValueToProperty("kilometros")

		new Label(mainPanel).setText(" kilómetros")
		new Label(mainPanel).bindValueToProperty("country")
	}
}

object ConversorRunner extends Application with App {
	def createMainWindow():Window[_] = new ConversorSimpleWindow(this)
	start()
}