package com.wbillingsley.veautiful.courseexplore

import com.wbillingsley.veautiful._

object Headers {

  def wrap(msg:VNode) = <.div(
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

  def uneLogo(heightClass:String = "") = {
    <.img(
      ^.src := "http://www.une.edu.au/__data/assets/image/0011/344/une-logo.png?v=0.1.5",
      ^.`class` := "corp-logo " + heightClass)
  }

  def picHeader(img:String, within:VNode, bottomLine:VNode):VNode = {
    <.div(^.cls := "pic-header",
      <.div(^.cls := "pic-header-img", ^.backgroundImage := "url(" + img + ")", within),
      <.div(^.`class` := "up-from-bottom", bottomLine)
    )
  }

  def banner(title:String):VNode = <.div(
    <.div(^.cls := "header", ^.role := "banner",
      <.div(^.cls := "container",
        <.a(^.href := "#", ^.cls := "logo", ^.onClick --> Routing.routeTo(Routing.HomeRoute),
          <.img(^.src := "http://www.une.edu.au/__data/assets/image/0011/344/une-logo.png?v=0.1.5", ^.alt := "University of New England Home"),
          <.span(^.cls := "site-title lightitalic", title)
        )
      )
    )
  )

  def sepStrip(title:String):VNode = {
    <.div(^.cls := "separator-strip",
      <.div(^.cls := "container",
        <.h1(title)
      )
    )
  }

  def wideTitledCard(title:String, onHeaderClick: () => Unit, t:VNode):VNode = {
    <.div(^.cls := "col-md-12",
      <.div(^.cls := "card",
        <.a(^.onClick --> onHeaderClick(),
          <.div(^.cls := "card-header text-xs-center",
            "Resources"
          )
        ), t
      )
    )
  }

  case class Expander(closed: () => VNode, open: () => VNode) extends VNode {

    val node = <("button").apply(^.onClick --> toggle)

    def domNode = node.domNode

    def attach() = {
      if (isAttached) {
        throw new IllegalStateException("Attached twice")
      }
      node.attach()
    }

    override def afterAttach() = update()

    def detach() = node.detach()

    var expanded = false

    def update() = {
      node.updateChildren(
        if (expanded) Seq(open()) else Seq(closed())
      )
    }

    def toggle:Unit = {
      expanded = !expanded
      update()
    }

  }



}
