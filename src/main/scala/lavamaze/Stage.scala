package lavamaze

import com.wbillingsley.veautiful.{<, DiffComponent, VNode, ^}
import com.wbillingsley.veautiful.templates.SequenceItem

trait Stage extends DiffComponent {

}

object Stage {

  val all = Seq(
    Stage0, Stage1, Stage2, Stage3, Stage4, Stage5, Stage6, Stage7, Stage8
  )

  val slideNodes:Seq[SequenceItem] = {
    new SequenceItem(TitleCard, showFootBox = () => false) +: all.map { n =>
      new SequenceItem(n, showFootBox = () => false)
    }

  }


  def pageControls(ready:Boolean) = {
    <.div(^.cls := "btn-group",
      prevLink,
      if (ready) readyNextLink else hiddenNextLink
    )
  }

  def prevLink = {
    Routing.route match {
      case StageR(i) =>
        <.a(^.cls := "btn btn-outline-secondary", ^.href := Routing.path(StageR(i - 1)), s"Page ${i - 1}")
      case _ =>
        <.a(^.cls := "btn btn-outline-light", ^.href := Routing.path(StageR(0)), "Page 0")
    }
  }

  def hiddenNextLink = {
    Routing.route match {
      case StageR(i) =>
        <.a(^.cls := "btn btn-outline-light", ^.href := Routing.path(StageR(i + 1)), s"Page ${i + 1}")
      case _ =>
        <.a(^.cls := "btn btn-outline-light", ^.href := Routing.path(StageR(0)), "Page 0")
    }
  }

  def readyNextLink = {
    Routing.route match {
      case StageR(i) =>
        <.a(^.cls := "btn btn-outline-secondary pulse-link", ^.href := Routing.path(StageR(i + 1)), s"Page ${i + 1}")
      case _ =>
        <.a(^.cls := "btn btn-outline-light", ^.href := Routing.path(StageR(0)), "Page 0")
    }
  }



}
