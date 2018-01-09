package com.wbillingsley.veautiful.courseexplore

import com.wbillingsley.veautiful.{<, ElementComponent, VNode}
import info.tweaked.model.plan.Plan
import units.AllUnits

import scala.scalajs.js.annotation.JSExportTopLevel

object Routing extends ElementComponent(<.div()) {

  import org.scalajs.dom.window



  sealed trait Route
  case object HomeRoute extends Route
  case class UnitRoute(name:String) extends Route
  case class PlanRoute(plan:Plan) extends Route

  var route:Route = HomeRoute

  def routeTo(r:Route) = {
    route = r
    renderElements(render)
  }

  def render = route match {
    case HomeRoute => Home.mainView
    case UnitRoute(n:String) => UnitViews.unitSummary(AllUnits.unitMap(n))
    case PlanRoute(p:Plan) => CourseViews.planSummary(p)
  }

  override def afterAttach() = {
    renderElements(render)
  }

}
