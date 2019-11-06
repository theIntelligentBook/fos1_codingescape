package lavamaze

import com.wbillingsley.veautiful.{<, ^}
import org.scalajs.dom
import org.scalajs.dom.Event
import org.scalajs.dom.raw.HTMLInputElement

import scala.scalajs.js

object StageSpoilerPaths extends Stage {
  import Headers._

  val editor = new CodeEditor(text =
    """
      |if (canGoRight()) {
      |  right(1)
      |} else if (canGoDown()) {
      |  down(1)
      |}
    """.stripMargin, rows=8, disabled=true)

  var reachedGoal = false
  val code = "invert!"
  val number = 6
  val name = "Downright annoying"

  val maze:Maze = new Maze("Stage 5")

  maze.makeSpoilerPath()

  var moveCount = 0

  val run: (Event) => Unit = { x =>
    maze.setActionAlgorithm(editor.getText, before = () => {
      if (moveCount < 100) {
        moveCount = moveCount + 1
      } else {
        if (!reachedGoal) {
          reachedGoal = true
          rerender()
        }
      }
    })
  }


  def checkPassword(e:dom.Event): Unit = {
    val s = e.target match {
      case x:HTMLInputElement => x.value
    }
    if (s == "treacle") {
      reachedGoal = true
      Routing.afterAttach()
    }
  }

  def render = {

    challengeLayout(number, name, reachedGoal)(<.div(
      hgutter,

      split(
        textColumn(
          <.h2("Spoilers!"),
          <.p("Just going down and right's all well and good until you hit a dead-end."),
          <.p("Run this algorithm and see that it gets stuck."),

          card(
            <.div(
              cardText(
                <.p("Every time you need a new action:"),
                editor
              ),
              cardText(<.p(),
                <.button(^.cls := "btn btn-primary", ^.onClick ==> run, "Run")
              )
            )
          ),
          hgutter,
          if (reachedGoal) {
            <.div(
              <.p(^.cls := "congrats", s"Code: $code"),
              <.p("Yes, the ninja's got stuck in a dead-end. If we want a more foolproof algorithm than that, we'll need to introduce some AI!")
            )
          } else <.div(),
          Stage.pageControls(reachedGoal)
        )
      )(
        textColumn(
          <.div(^.cls := "split2 split-top-right",
            <.div(),
            <.div(maze)
          )
        )
      )

    ))

  }

  def prev(): Unit = {
    Routing.routeTo(StageR(4))
  }

  def next(): Unit = {
    Routing.routeTo(StageR(6))
  }
}
