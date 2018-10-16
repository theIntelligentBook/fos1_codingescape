package com.wbillingsley.veautiful.courseexplore

import com.wbillingsley.veautiful._
import courses.{BCompSc, MastersIT}
import lavamaze.Routing
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
        Headers.banner(""),
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
        <.h1("Planned courses")
      )

    )
  )

}
