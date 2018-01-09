package com.wbillingsley.veautiful.courseexplore

import com.wbillingsley.veautiful.courseexplore.Home.msg
import com.wbillingsley.veautiful._
import info.tweaked.model.unit.{AssessmentDescription, TeachingUnit}
import units.CBOK

object UnitViews {

  def longLink(u:TeachingUnit):VNode = <.a(
    ^.href := "#",
    ^.onClick --> Routing.routeTo(Routing.UnitRoute(u.code))
  )(
    s"${u.code} ${u.name.getOrElse("Unnamed")}"
  )

  def shortLink(u:TeachingUnit):VNode = <.a(
    ^.href := "#",
    ^.onClick --> Routing.routeTo(Routing.UnitRoute(u.code))
  )(u.code)

  def unitSummary(u:TeachingUnit):DElement = {
    Headers.wrap(unitCard(u))
  }

  def unitCard(u:TeachingUnit):DElement = <.div(
    <.div(^.cls := "row",
      <.div(^.cls := "col-md-10 col-md-offset-1 inset-space",
        <.div(^.cls := "inset-tab").children(
          <.p(
            Home.homeLink
          ),
          <.h1(s"${u.code} ${u.name.getOrElse("Unnamed")}"),
          prereq(u),
          description(u),
          learningOutcomes(u),
          assessment(u)
        )
      )
    ),
    <.div.style(InlineStyle("height", "30px")),
    <.div(^.cls := "row",
      <.div(^.cls := "inset-tab").children(
        cbok(u)
      )
    )
  )



  def prereq(u:TeachingUnit):VNode = {
    <.p(
    )
  }

  def description(u:TeachingUnit):VNode = {
    <.div(

    ).children(
      u.description.map({ p => <.p(p) }):_*
    )
  }

  def learningOutcomes(u:TeachingUnit):VNode = {
    <.div(
      <.h3("Learning outcomes:"),
      <.ol.children(
        (for { o <- u.outcomes } yield <.li(o)) : _*
      )
    )
  }

  def assessment1(u:TeachingUnit):VNode = {
    <.div(
      <.h3("Assessment:"),
      <.ol.children(
        (for { o <- u.assessment } yield <.li(o.title)) : _*
      )
    )
  }

  def assessment(u:TeachingUnit):VNode = {
    <.div(
      <.h3("Assessment:"),
      <.table(^.cls := "table",
        <.thead(
          <.tr.children(
            <.th("Item") +: <.th("Weight") +:
              (for { (o, oi) <- u.outcomes.zipWithIndex } yield <.th(s"LO${oi + 1}")) :_*
          )
        ),
        <.tbody.children(
          (for { a <- u.assessment } yield <.tr.children(
            <.td(a.title) +: <.td(s"${a.percentage}%") +:
              (for { (o, oi) <- u.outcomes.zipWithIndex } yield {
                if (a.lo.contains(oi + 1)) <.td("X") else <.td()
              }) : _*
          )): _*
        )
      )

    )
  }

  def cbok(u:TeachingUnit) = {
    <.div(
      <.h3("CBOK Mapping:"),
      <.table(^.cls := "table",
        cbokHeader,
        cbokBody(u)
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

  def cbokBody(u:TeachingUnit) = {
    <.tbody.children(
      (for { a <- u.taught ++ u.assessment } yield {
        <.tr.children(
          <.td(a.title) +:
            (for { c <- CBOK.categories; i <- c.is } yield {
              a.cbok.find(_.i == i).map({ at => levelToTD(at.level)}).getOrElse(<.td())
            }): _*
        )
      }) :+ <.tr.children(
        <.td("Overall") +:
        (for { c <- CBOK.categories; i <- c.is } yield {
          max(i, u.taught ++ u.assessment).map(levelToTD).getOrElse(<.td())
        }): _*
      ) :_*
    )
  }

}
