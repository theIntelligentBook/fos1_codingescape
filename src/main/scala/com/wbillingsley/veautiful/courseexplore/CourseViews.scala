package com.wbillingsley.veautiful.courseexplore

import com.wbillingsley.veautiful._
import com.wbillingsley.veautiful.courseexplore.Routing.UnitRoute
import info.tweaked.model.plan.Plan
import info.tweaked.model.unit.{AssessmentDescription, TeachingUnit}
import units.CBOK

object CourseViews {

  def planSummary(p:Plan):DElement = {
    Headers.wrap(planCard(p))
  }

  def planCard(p:Plan):DElement = <.div(
    <.div(^.cls := "row",
      <.div(^.cls := "col-md-10 col-md-offset-1 inset-space",
        <.div(^.cls := "inset-tab").children(
          <.p(
            <.a(
              ^.href := "#",
              ^.onClick --> Routing.routeTo(Routing.HomeRoute), Text("Home")
            )
          ),
          <.h1(p.name),
          <.div.children(p.description.map(x => <.p(x)):_*),
          <.div.children(
            (for { (t, units) <- p.selection } yield {
              <.div(
                <.p(t.name),
                <.ul.children(
                  units.map(u => <.li(
                    <.a(
                      ^.href := "#",
                      ^.onClick --> Routing.routeTo(UnitRoute(u.code)),
                      s"${u.code} ${u.name.getOrElse("Unnamed")}"
                    )
                  )): _*
                )
              )
            }):_*
          )
        )
      )
    ),
    <.div.style(InlineStyle("height", "30px")),
    <.div(^.cls := "row",
      <.div(^.cls := "inset-tab").children(
        cbok(p)
      )
    )
  )


  def cbok(p:Plan) = {
    <.div(
      <.h3("CBOK Mapping:"),
      <.table(^.cls := "table",
        cbokHeader,
        cbokBody(p)
      )
    )
  }

  def cbokHeader = {
    <.thead(
      <.tr.children(
        <.th() +: CBOK.categories.map({ c => <.th.attrs(AttrVal("colspan", c.is.length.toString))(c.n)}):_*
      ),
      <.tr.children(
        <.th() +: CBOK.categories.flatMap(_.is).map({
          i => <.th(i.short)
        }):_*
      )
    )
  }

  def levelToTD(i:Int) = {
    if (i == 0) <.td("X") else <.td(i.toString)
  }

  def max(i:CBOK.I, as:Seq[AssessmentDescription]):Option[Int] = {
    val relevant = for { a <- as; at <- a.cbok if at.i == i } yield at.level
    if (relevant.isEmpty) {
      None
    } else Some(relevant.max)
  }

  def cbokBody(p:Plan) = {
    <.tbody.children(
      (for { u <- p.allUnits } yield {
        <.tr.children(
          <.td(UnitViews.shortLink(u)) +:
            (for { c <- CBOK.categories; i <- c.is } yield {
              max(i, u.taught ++ u.assessment).map(levelToTD).getOrElse(<.td())
            }): _*
        )
      })  :_*
    )
  }

}
