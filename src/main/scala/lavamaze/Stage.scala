package lavamaze

import com.wbillingsley.veautiful.{<, DiffComponent, VNode, ^}
import com.wbillingsley.veautiful.templates.SequenceItem

trait Stage extends DiffComponent {

  def reachedGoal:Boolean

  def code:String

  def number:Int

  def name:String

}

object Stage {

  val all = Seq(
    Stage0, Stage1, Stage2, Stage3, Stage4, Stage5, Stage6, Stage7, Stage8
  )

  val sequence = Seq(
    TitleCard, Stage0, Stage1, Stage2, Stage3, Stage4, Stage5, Stage6, Stage7, Stage8
  )

  val slideNodes:Seq[SequenceItem] = {
    sequence.map { n =>
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

  def stageActive(s:Stage):Boolean = {
    Routing.deck.content(Routing.deck.index).content == s
  }


  def progressLine(s:Stage) = {
    <.div(^.cls := (if (stageActive(s)) "stage stage-active" else "stage"),
      <.div(^.cls := "stage-progress-label",
        <.span(^.cls := "stagenumber", s.number.toString),
        <.span(<.span(^.cls := "stagename", s.name))
      ),
      if (s.reachedGoal) {
        <.div(^.cls := "stage-code", s.code)
      } else {
        <.div(^.cls := "stage-locked", "UNSOLVED")
      }

    )
  }

  def progressBlock = {
    import Headers._

    <.div(
      for { s <- all } yield {
        progressLine(s)
      }
    )
  }


}
