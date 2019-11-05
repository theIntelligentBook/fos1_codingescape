package lavamaze

import com.wbillingsley.veautiful.{<, ^}

import scala.scalajs.js

object Stage4 extends Stage {
  import Headers._

  val editor = new CodeEditor(text = "", rows=8, disabled=false)

  var reachedGoal = false
  val code = "+0001"
  val number = 4
  val name = "If only..."

  val maze:Maze = new Maze("Stage 4",
    defaultAction = () => {
      js.eval(editor.getText)
      Idle
    },
    onGoal = () => {
      reachedGoal = true
      println("Complete")
      Routing.afterAttach()
    }
  )

  maze.makePath()

  def run(): Unit = {
    Commands.activeMaze = Some(maze)

    maze.Ninja.x = 0
    maze.Ninja.y = 0
    maze.Ninja.action = Idle
    maze.actionQueue.dequeueAll(_ => true)
    js.eval(editor.getText)
  }

  def reset():Unit = {
    editor.setText("")
    maze.Ninja.x = 0
    maze.Ninja.y = 0
    maze.Ninja.action = Idle
  }

  def render = {

    challengeLayout(number, name, reachedGoal)(<.div(
      hgutter,

      split(
        card(
          cardText(<.p(
            """
              | The code block now gets called every time the ninja finishes its action, so you can decide what it
              | should do next. The code example below is rubbish, but it gives you the idea of how if statements
              | work, and introduces the canGoDown() and canGoRight() commands. && is "and" and || is "or"
            """.stripMargin
          ),
          <("pre")(
            """
              | if (2 > 1 && canGoRight()) {
              |   down(4)
              | } else if (1 > 2 || canGoDown()) {
              |   right(7)
              | }
            """.stripMargin)
          ),
          cardText(<.p("Every time you need a new action:"),
            editor
          ),
          cardText(<.p(),
            <.button(^.cls := "btn btn-primary", ^.onClick --> run, "Run")
          )
        )
      )(
        <.div(
          card(
            maze
          ),
          hgutter,
          if (reachedGoal) {
            <.div(
              <.p(^.cls := "congrats", s"Code: $code"),
              <.p("Well that was down-right trivial! But you know we're going to spoil the party in the next stage...")
            )
          } else <.div(),
          Stage.pageControls(reachedGoal)
        )

      )
    ))

  }

  def prev(): Unit = {
    Routing.routeTo(StageR(3))
  }

  def next(): Unit = {
    Routing.routeTo(StageR(5))
  }
}
