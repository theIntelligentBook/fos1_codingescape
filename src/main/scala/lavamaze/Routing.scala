package lavamaze

import com.wbillingsley.veautiful.{<, ElementComponent}

object Routing extends ElementComponent(<.div()) {

  sealed trait Route
  case object HomeRoute extends Route
  case object Stage1R extends Route

  var route:Route = Stage1R

  def routeTo(r:Route) = {
    route = r
    renderElements(render)
  }

  def render = route match {
    case HomeRoute => Headers.stageHeader(1, "Hello")
    case Stage1R => Stage1.node
  }

  override def afterAttach() = {
    renderElements(render)
  }

}
