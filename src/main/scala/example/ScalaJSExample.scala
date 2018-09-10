package example

import com.wbillingsley.veautiful.courseexplore.{CourseLoader, Home, Routing, UnitViews}
import com.wbillingsley.veautiful.{<, Attacher, DElement}
import info.tweaked.model.hjson.RequirementParsing

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

object ScalaJSExample {

  var root = Attacher.render(<.div(), dom.document.getElementById("render-here"))

  def main(args:Array[String]):Unit = {
    import com.wbillingsley.veautiful._

    println("START NOW")

    /*
    for {
      loaded <- CourseLoader.termsMap
    } println(loaded)

    for {
      loaded <- CourseLoader.offeringsMap
    } println(loaded)

    for {
      loaded <- CourseLoader.loadUnit("COSC220")
    } println(loaded)


    */


    for {
      loaded <- CourseLoader.loadCourse("DipIT")
    } println(loaded)


    println(RequirementParsing.parseRequirement("30cp to 48cp from COSC110, COSC101, COSC120"))

    println(RequirementParsing.parseRequirement("COSC110, COSC101, COSC120"))

    root.render(Routing)

  }

  /** Computes the square of an integer.
   *  This demonstrates unit testing.
   */
  def square(x: Int): Int = x*x
}
