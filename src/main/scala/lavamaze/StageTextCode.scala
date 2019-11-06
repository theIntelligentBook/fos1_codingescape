package lavamaze

import com.wbillingsley.veautiful.logging.Logger
import com.wbillingsley.veautiful.{<, ^}
import org.scalajs.dom.Event

import scala.scalajs.js

object StageTextCode extends Stage {
  import Headers._

  val logger:Logger = Logger.getLogger(StageTextCode.getClass)


  val editor = new CodeEditor(text = "", rows=8, disabled=false, placeholder = "Write your code here")

  var reachedGoal = false
  val code = "83"
  val number = 3
  val name = "Don't typo!"

  val maze:Maze = new Maze("Stage 2", onGoal = () => {
    reachedGoal = true
    Routing.rerender()
  })

  maze.makePath()

  var error:Option[String] = None

  val run: (Event) => Unit = { x =>
    try {
      error = None
      rerender()
      maze.runCode(editor.getText)
    } catch {
      case x:Throwable =>
        error = Some(x.getMessage)
        rerender()
    }
  }

  def reset():Unit = {
    editor.setText("")
    maze.Ninja.x = 0
    maze.Ninja.y = 0
    maze.Ninja.action = Idle
  }

  def render = {

    logger.debug(s"Rendering stage 2")

    challengeLayout(number, name, reachedGoal)(<.div(
      hgutter,

      split(
        textColumn(
          <.h2("Raw JavaScript!"),
          <.p(
            """
              | Eventually, most programmers prefer typing text to dragging blocks. It's quicker.
            """.stripMargin
          ),
          <.p(
            """
              | But you have to be careful and precise - when computers read programs in text, they can be put off by
              | little things like spaces where they didn't expect them, spelling mistakes, and mismatched brackets
              |""".stripMargin
          ),
          <.p(
            """
              | Have a go at writing another maze program, this time just using text. To give you a hand with the formatting,
              | there's an example below. Watch for those spelling mistakes!
              |""".stripMargin
          ),
          <("pre")(
            """
              | down(2)
              | right(3)
              | down(1)
              | right(2)
              |""".stripMargin
          ),
          if (reachedGoal) {
            <.div(
              <.p(^.cls := "congrats", s"Code: $code"),
              <.p("Well done. But I wouldn't want to rewrite my code for every maze. Time to move on...")

            )
          } else <.div(),
          Stage.pageControls(reachedGoal)
        )
      )(
        textColumn(
          <.div(^.cls := "split2 split-top-right",
            <.div(), maze,
            hgutter, hgutter,
            <.div(^.cls := "btn-group-vertical align-top pr-1",
              <.button(^.cls := "btn btn-outline-primary", ^.onClick ==> run, "Run")
            ),
            <.div(
              editor,
              error.toSeq.map(s => <.p(^.cls := "error-message", s))
            )

          )
        )
      )

    ))

  }

  def prev(): Unit = {
    Routing.routeTo(StageR(1))
  }

  def next(): Unit = {
    Routing.routeTo(StageR(3))
  }
}
