package lavamaze

import com.wbillingsley.veautiful.logging.Logger
import com.wbillingsley.veautiful.{<, ^}

import scala.scalajs.js

object Stage2 extends Stage {
  import Headers._

  val logger:Logger = Logger.getLogger(Stage2.getClass)


  val editor = new CodeEditor(text = "", rows=8, disabled=false)

  var reachedGoal = false
  val code = "+0010"
  val number = 2
  val name = "Blocks"

  val maze:Maze = new Maze("Stage 2", onGoal = () => {
    reachedGoal = true
    println("Complete")
    Routing.afterAttach()
  })

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

    logger.debug(s"Rendering stage 2")

    challengeLayout(number, name, reachedGoal)(<.div(
      hgutter,

      split(
        card("Your turn")(
          cardText(<.p(
            """
              | This time, you get to write the script. Start by pasting the one you made earlier, but
              | you'll notice the maze is different! Fix it up...
            """.stripMargin
          )),
          cardText(<.p(),
            editor
          ),
          cardText(<.p(),
            <.button(^.cls := "btn btn-primary", ^.onClick --> run, "Run")
          )
        )
      )(
        card(
          maze
        )
      ),
      hgutter,
      if (reachedGoal) {
        <.div(
          <.p(^.cls := "congrats", s"Code: $code"),
          <.p("Well done. But I wouldn't want to rewrite my code for every maze. Time to move on...")

        )
      } else <.div(),
      Stage.pageControls(reachedGoal)
    ))

  }

  def prev(): Unit = {
    Routing.routeTo(StageR(1))
  }

  def next(): Unit = {
    Routing.routeTo(StageR(3))
  }
}
