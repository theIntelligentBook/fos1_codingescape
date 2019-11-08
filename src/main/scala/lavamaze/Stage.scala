package lavamaze

import com.wbillingsley.veautiful.logging.Logger
import com.wbillingsley.veautiful.{<, DiffComponent, VNode, ^}
import com.wbillingsley.veautiful.templates.SequenceItem

trait Stage extends DiffComponent {

  def reachedGoal:Boolean

  final def code:String = Stage.codes(number)

  def number:Int

  def name:String

}

object Stage {

  val codes = Seq(
    "33",
    "73", "32", "69" , "83", "67", "65", "80", "69", "68",
//    "33",
    "ESCAPED"

  )

  val logger:Logger = Logger.getLogger(Stage.getClass)

  val all = Seq(
    Stage1, StageBlocks, StageTextCode, StageCardsOfDoom, StageIf, StageSpoilerPaths, StageJellyFlood, StageCountdown, StageBlobs, Stage8
  )

  val sequence = Seq(
    TitleCard, Stage1, StageBlocks, StageTextCode, StageCardsOfDoom, StageIf, StageSpoilerPaths, StageJellyFlood, StageCountdown, StageBlobs, Stage8
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
    if (s.reachedGoal) {
      <.div(^.cls := (if (stageActive(s)) "stage stage-active" else "stage"),
        <.div(^.cls := "stage-progress-label",
          <.span(^.cls := "stagenumber", s.number.toString),
          <.span(<.span(^.cls := "stagename", s.name))
        ),
        <.div(^.cls := "stage-code", s"CODE: ${s.code}")
      )
    } else {
      <.div(^.cls := (if (stageActive(s)) "stage stage-active" else "stage"),
        <.div(^.cls := "stage-progress-label",
          <.span(^.cls := "stagenumber", s.number.toString),
          <.span(<.span(^.cls := "stagename", s.name))
        ),
        <.div(^.cls := "stage-locked", "UNSOLVED")
      )
    }
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
