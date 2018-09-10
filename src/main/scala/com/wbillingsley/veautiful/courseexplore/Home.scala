package com.wbillingsley.veautiful.courseexplore

import com.wbillingsley.veautiful._
import courses.{BCompSc, MastersIT}
import units.AllUnits

object Home {

  import Headers._

  def homeLink:VNode = <.a(
    ^.href := "#",
    ^.onClick --> Routing.routeTo(Routing.HomeRoute), Text("Home")
  )

  def mainView = <.div(
    Headers.picHeader("assets/images/wheatfields.jpeg",
      <.div(
        Headers.banner("ACS Accreditation 2017"),
        <.div(^.cls := "container", ^.minHeight := "100vh",
          msg
        )
      ),
      <.div()
    )
  )

  def msg:VNode = <.div(^.cls := "row",
    <.div(^.cls := "col-md-8 col-md-offset-2 inset-space",
      <.div(^.cls := "inset-tab",
        <.h1("Planned courses"),
        <.ul(
          <.li(
            <.a(
              ^.href := "#",
              ^.onClick --> Routing.routeTo(Routing.CourseRoute("DipIT"))
            )("Diploma in Information Technology")
          ),
          <.li(
            <.a(
              ^.href := "#",
              ^.onClick --> Routing.routeTo(Routing.CourseRoute("BInfTech"))
            )("Bachelor of Information Technology")
          )
        ),

        <.h1("Course explorer"),
        <.p(
          """
            |This is an experimental way of exploring the contents of our courses, and how they map. It is driven from
            |a simple model of each unit. The UI uses a home-grown Scala declarative UI framework, tested in Chrome
            |and Safari.
          """.stripMargin),
        <.p(
          """
            |Units and courses are mapped against CBOK -- please scoll down.
          """.stripMargin),
        <.h3("Unit details"),
        <.p("Mathematics requirement (not all required)")(
          <.ul.children(
            AllUnits.maths.map({ u =>
              <.li(UnitViews.longLink(u))
            }):_*
          )
        ),
        <.p("Statistics (highlighted in Data Science major)")(
          <.ul.children(
            AllUnits.stat.map({ u =>
              <.li(UnitViews.longLink(u))
            }):_*
          )
        ),
        <.p("Computer Science")(
          <.ul.children(
            AllUnits.cosc.map({ u =>
              <.li(UnitViews.longLink(u))
            }):_*
          )
        ),
        <.p("Masters level versions (for entry-level masters degrees)")(
          <.ul.children(
            AllUnits.postgrad.map({ u =>
              <.li(UnitViews.longLink(u))
            }):_*
          )
        ),
        <.h3("Plans"),
        <.p(
          """
            | Course plans and fragments, showing how CBOK scaffolds across them
          """.stripMargin),
        <.p("Bachelor of Computer Science:"),
        <.ul.children(
          BCompSc.plans.map({ p =>
            <.li(
              <.a(
                ^.href := "#",
                ^.onClick --> Routing.routeTo(Routing.PlanRoute(p))
              )(
                p.name
              )
            )
          }): _*
        ),
        <.p("Master courses:"),
        <.ul.children(
          MastersIT.plans.map({ p =>
            <.li(
              <.a(
                ^.href := "#",
                ^.onClick --> Routing.routeTo(Routing.PlanRoute(p))
              )(
                p.name
              )
            )
          }): _*
        )
      )
    )
  )

}
