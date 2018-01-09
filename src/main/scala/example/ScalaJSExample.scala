package example

import com.wbillingsley.veautiful.courseexplore.{Home, Routing, UnitViews}
import com.wbillingsley.veautiful.{<, Attacher, DElement}

import scala.scalajs.js
import org.scalajs.dom
import org.scalajs.dom.raw.Event
import units.COSC370

@js.native
trait EventName extends js.Object {
  type EventType <: dom.Event
}

object EventName {
  def apply[T <: dom.Event](name: String): EventName { type EventType = T } =
    name.asInstanceOf[EventName { type EventType = T }]

  val onmousedown = apply[dom.MouseEvent]("onmousedown")
}

@js.native
trait ElementExt extends js.Object {
  def addEventListener(name: EventName)(
      f: js.Function1[name.EventType, _]): Unit
}

object ScalaJSExample extends js.JSApp {
  def oldmain(): Unit = {
    val paragraph = dom.document.createElement("p")
    paragraph.innerHTML = "<strong>It works!</strong>"
    dom.document.getElementById("playground").appendChild(paragraph)

    val p = paragraph.asInstanceOf[ElementExt]
  }

  var root = Attacher.render(<.div(), dom.document.getElementById("render-here"))

  def main():Unit = {
    import com.wbillingsley.veautiful._

    root.render(Routing)

  }

  /** Computes the square of an integer.
   *  This demonstrates unit testing.
   */
  def square(x: Int): Int = x*x
}
