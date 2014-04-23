package edu.unq.uis.planificador.ui.widgets

import org.uqbar.arena.windows.{Dialog, Window, WindowOwner}
import org.uqbar.ui.view.ErrorViewer
import org.uqbar.arena.widgets._
import org.uqbar.arena.layout.{ColumnLayout, Layout, HorizontalLayout, VerticalLayout}
import org.uqbar.arena.aop.potm.Function
import org.uqbar.arena.widgets.tables.{Column, Table}
import com.uqbar.commons.collections.Transformer
import org.uqbar.arena.bindings.{PropertyAdapter, ObservableProperty}
import org.uqbar.lacar.ui.model.Action

abstract class NiceWindow[T](parent: WindowOwner, model: T) extends Dialog[T](parent, model) with ErrorViewer {
//  def showInfo(message: String) {
//    this.showMessageBox(MessageBox.Type.Information, message)
//  }
//
//  def showWarning(message: String) {
//    this.showMessageBox(MessageBox.Type.Warning, message)
//  }
//
//  def showError(message: String) {
//    this.showMessageBox(MessageBox.Type.Error, message)
//  }
//
//  protected def showMessageBox(tipo: MessageBox.Type, message: String) {
//    val messageBox: MessageBox = new MessageBox(this, tipo)
//    messageBox.setMessage(message)
//    messageBox.open()
//  }

//  override def createContents(mainPanel: Panel): Unit = {
//    this.windowDefinition.renderTo(mainPanel)
//  }

  override def createFormPanel(mainPanel: Panel): Unit = {
    this.windowDefinition.renderTo(mainPanel)
  }

  def windowDefinition: Renderizable

  trait Renderizable {
    def renderTo(panel: Panel): Renderizable
  }

  trait PanelLayout extends Renderizable {
    override def renderTo(parent: Panel): Renderizable = {
      val selfPanel: Panel = new Panel(parent)
      selfPanel.setLayout(this.orientation)
      this.children.foreach(_.renderTo(selfPanel))
      this
    }

    def children: Seq[Renderizable]

    def orientation: Layout
  }

  case class LayoutVertical(args: Renderizable*) extends PanelLayout {
    override def children: Seq[Renderizable] = this.args

    override def orientation: Layout = new VerticalLayout()
  }

  case class LayoutHorizontal(args: Renderizable*) extends PanelLayout {
    override def children: Seq[Renderizable] = this.args

    override def orientation: Layout = new HorizontalLayout()
  }

  case class LayoutColumn(count: Integer, args: Renderizable*) extends PanelLayout {
    override def orientation: Layout = new ColumnLayout(count)

    override def children: Seq[NiceWindow.this.type#Renderizable] = args
  }

  case class Boton(var label: String, var onClick: () => Unit) extends Renderizable {
    override def renderTo(panel: Panel) = {
      new Button(panel)
        .setCaption(label)
        .onClick(new Function(onClick))
        .disableOnError()
      this
    }
  }

  case class DropDown[Tipo](bindTo: String, property: String, adapter: PropertyAdapter, width: Int = 250, onSelection: () => Unit = ()=>false) extends Renderizable {
    override def renderTo(panel: Panel) = {
      val selector = new Selector[Tipo](panel)
      selector
        .allowNull(false)
        .setWidth(width)
        .bindValueToProperty(bindTo)

      selector
        .bindItems(new ObservableProperty(property))
        .setAdapter(adapter)
      selector.onSelection(new Action(){
        override def execute(): Unit = onSelection()
      })
      this
    }
  }

  case class Etiqueta(var texto: String, var width: Int = 100) extends Renderizable {
    override def renderTo(panel: Panel) ={
      new Label(panel).setText(texto).setWidth(width)
      this
    }
  }

  case class Input(bindTo: String, var width: Int = 100) extends Renderizable {
    override def renderTo(panel: Panel) = {
      new TextBox(panel).setWidth(width).bindValueToProperty(bindTo)
      this
    }
  }

  case class TableColumn[ColumnType](title: String = "", width: Integer = 150, bindTo: Either[String, Transformer[ColumnType, Any]]) {
    def renderTo(parent: Table[ColumnType]): TableColumn[ColumnType] = {
      val column = new Column[ColumnType](parent).setFixedSize(width)

      bindTo match {
        case Left(propertyName) =>
          column
            .setTitle(if (title == "") camelCaseToHumanReadable(propertyName) else title)
            .bindContentsToProperty(propertyName)

        case Right(transformer) =>
          column
            .setTitle(title)
            .bindContentsToTransformer(transformer)
      }

      this
    }
  }

  case class TableWidget[ModelType: Manifest](bindItemsTo: String, bindSelectionTo: String, height: Integer, children: TableColumn[ModelType]*) extends Renderizable {
    override def renderTo(panel: Panel): Renderizable = {
      val tabla: Table[ModelType] = renderWithType[ModelType](panel)
      tabla.setHeight(height)
      children.foreach(_.renderTo(tabla))
      this
    }

    /**
     * Esto es para luego poder poner clases por default tomando el T del window
     * @param panel Container
     * @tparam FinalType Tipo con el que va a bindearse
     * @return
     */
    def renderWithType[FinalType: Manifest](panel: Panel): Table[FinalType] = {
      new TypeInspector[FinalType]().instance()
      val table = new Table[FinalType](panel, new TypeInspector[FinalType]().instance())
      table.bindItemsToProperty(bindItemsTo)
      table.bindSelectionToProperty(bindSelectionTo)
      table
    }
  }

  protected def camelCaseToHumanReadable(s: String): String = s.replaceAll(
    String.format("%s|%s|%s",
      "(?<=[A-Z])(?=[A-Z][a-z])",
      "(?<=[^A-Z])(?=[A-Z])",
      "(?<=[A-Za-z])(?=[^A-Za-z])"
    ),
    " "
  )

  class TypeInspector[GenericType] {
    def instance()(implicit m: Manifest[GenericType]): Class[GenericType] =
      m.runtimeClass.asInstanceOf[Class[GenericType]]
  }

  def openNew(w: Window[_]) = w.open()
}



