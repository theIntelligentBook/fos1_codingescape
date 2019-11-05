package lavamaze

import com.wbillingsley.veautiful.templates.{HistoryRouter, VSlides}
import com.wbillingsley.veautiful.{<, DElement, ElementComponent, PathDSL, VNode}

sealed trait Route
case object HomeRoute extends Route
case class StageR(i:Int) extends Route

object Routing extends HistoryRouter[Route] {

  var route:Route = HomeRoute

  def parseInt(s:String, or:Int):Int = {
    try {
      s.toInt
    } catch {
      case n:NumberFormatException => or
    }
  }

  override def routeFromLocation(): Route = PathDSL.hashPathArray() match {
    case Array("") => HomeRoute
    case Array(i) => StageR(parseInt(i, 0))
    case x =>
      println(s"path was ${x}")
      HomeRoute
  }

  val deck = VSlides(width = 1920, height = 1080)(
    Stage.slideNodes
  )

  def deckPage(i:Int) = {
    if (i >= 0 && i < deck.content.size) {
      deck.index = i
      deck
    } else {
      deck.index = 0
      deck
    }
  }


  def render:VNode = route match {
    case HomeRoute => deck
    case StageR(i) => {
      if (i == 1) Countdown.start()
      deckPage(i)
    }
  }

  def rerender() = renderElements(render)

  override def path(route: Route): String = {
    import PathDSL._

    route match {
      case HomeRoute =>(/# / "").stringify
      case StageR(i) => (/# / i.toString).stringify
    }

  }

}
