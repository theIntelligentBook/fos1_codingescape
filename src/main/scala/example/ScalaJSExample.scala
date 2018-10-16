package example

import com.wbillingsley.veautiful.{<, Attacher, DElement}
import lavamaze.Routing

import scala.scalajs.js
import org.scalajs.dom
import org.scalajs.dom.raw.Event

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

object ScalaJSExample {

  var root = Attacher.render(<.div(), dom.document.getElementById("render-here"))

  def main(args:Array[String]):Unit = {
    import com.wbillingsley.veautiful._

    println("START NOW")

    root.render(Routing)

  }

  def rerender() = root.render(Routing)

  /** Computes the square of an integer.
   *  This demonstrates unit testing.
   */
  def square(x: Int): Int = x*x
}
