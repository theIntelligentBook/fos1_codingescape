package lavamaze

import com.wbillingsley.veautiful.{<, DiffComponent, DiffNode, ^}

object TitleCard extends DiffComponent {

  override def render: DiffNode = <.div(^.cls := "title-card",
    <.h1("The Coding Escape"),
    <.img(^.cls := "stageninja", ^.src := "assets/ninja.png"),
    <.p("An hour of code and a little AI"),
    <.a(^.cls := "btn btn-outline-secondary pulse-link", ^.href := Routing.path(StageR(1)), "Click to begin!")
  )

}
