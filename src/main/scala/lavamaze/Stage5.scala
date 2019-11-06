package lavamaze

import com.wbillingsley.veautiful.{<, ^}
import lavamaze.Stage4.{editor, reachedGoal}
import org.scalajs.dom
import org.scalajs.dom.Event
import org.scalajs.dom.raw.HTMLInputElement

import scala.scalajs.js

object Stage5 extends Stage {
  import Headers._

  val editor = new CodeEditor(text =
    """
      |if (canGoRight()) {
      |  right(1)
      |} else down(1)
    """.stripMargin, rows=8, disabled=true)

  var reachedGoal = false
  val code = "invert!"
  val number = 5
  val name = "Downright annoying"

  val maze:Maze = new Maze("Stage 5",
    defaultAction = () => {
      //js.eval(editor.getText)
      Idle
    }
  )

  maze.makeSpoilerPath()

  val run: (Event) => Unit = { x =>
    maze.runCode(editor.getText)
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
        <.div(
          card(
            <.div(
              cardText(
                <.p("Every time you need a new action:"),
                editor
              ),
              cardText(<.p(),
                <.button(^.cls := "btn btn-primary", ^.onClick ==> run, "Run")
              ),
              <.h4("Password"),
              <("input")(^.cls := "form-control", ^.attr("type") := "text", ^.on("input") ==> checkPassword)
            )
          ),
          hgutter,
          if (reachedGoal) {
            <.div(
              <.p(^.cls := "congrats", s"Code: $code"),
              <.p("Poster posted, on to try it out...")
            )
          } else <.div(),
          Stage.pageControls(reachedGoal)
        )
      )(
        card("Spoiler alert!")(
          cardText(
            <.div(
              <.p("Just going down and right's all well and good until you hit a dead-end."),
              <.div(maze),
              <.p(
                "If we want a more foolproof algorithm, we're going to need to do a bit more work than that. Let's talk this one through on the poster."
              ),
              <.p(
                "So, first things first: CLAP YOUR HANDS so we know you're up to this stage and ready."
              )
            )
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
