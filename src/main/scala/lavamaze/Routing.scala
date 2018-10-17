package lavamaze

import com.wbillingsley.veautiful.{<, DElement, ElementComponent, VNode}

object Routing extends ElementComponent(<.div()) {

  sealed trait Route
  case object HomeRoute extends Route
  case object Stage1R extends Route
  case object Stage2R extends Route
  case object Stage3R extends Route
  case object Stage4R extends Route
  case object Stage5R extends Route
  case object Stage6R extends Route
  case object Stage7R extends Route
  case object Stage8R extends Route

  var route:Route = Stage1R

  def routeTo(r:Route) = {
    route = r
    renderElements(render)
  }

  def render:VNode = route match {
    case HomeRoute => Headers.stageHeader(1, "Hello")
    case Stage1R => Stage1.node
    case Stage2R => Stage2.node
    case Stage3R => Stage3.node
    case Stage4R => Stage4.node
    case Stage5R => Stage5.node
    case Stage6R => Stage6.node
    case Stage7R => Stage7.node
    case Stage8R => Stage8.node
  }

  override def afterAttach() = {
    renderElements(render)
  }

}
