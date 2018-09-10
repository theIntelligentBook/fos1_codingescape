package com.wbillingsley.veautiful.courseexplore

import com.wbillingsley.veautiful.{<, ElementComponent, VNode}
import info.tweaked.model.plan.{Course, Plan}
import units.AllUnits

import scala.scalajs.js.annotation.JSExportTopLevel
import com.wbillingsley.handy.Ref._

object Routing extends ElementComponent(<.div()) {

  import org.scalajs.dom.window



  sealed trait Route
  case object HomeRoute extends Route
  case class UnitRoute(name:String) extends Route
  case class PlanRoute(plan:Plan) extends Route

  case class CourseRoute(code:String) extends Route

  var route:Route = HomeRoute

  def routeTo(r:Route) = {
    route = r
    renderElements(render)
  }

  def render = route match {
    case HomeRoute => Home.mainView
    case UnitRoute(n:String) => Headers.R(CourseLoader.loadUnit(n).map(UnitViews.unitSummary) recoverWith { case x:Throwable => <.div(x.getMessage).itself })
    case PlanRoute(p:Plan) => CourseViews.planSummary(p)

    case CourseRoute(s:String) => CourseViews.courseSummary(s)
  }

  override def afterAttach() = {
    renderElements(render)
  }

}
