package lavamaze

import com.wbillingsley.veautiful.logging.Logger
import com.wbillingsley.veautiful.{<, ^}
import example.ScalaJSExample

import scala.scalajs.js

object Stage1 extends Stage {
  import Headers._

  val logger:Logger = Logger.getLogger(Stage1.getClass)

  val editor = new CodeEditor(text = "", rows=8, disabled=true)

  var reachedGoal = false

  val code = "0000"

  override val number = 1

  override val name = "By example"

  val maze:Maze = new Maze("Stage 1", onGoal = () => {
    reachedGoal = true
    println("Complete")
    Routing.afterAttach()
  })

  maze.makePath()

  var instructions = List.empty[Instr]

  def run(): Unit = {
    Commands.activeMaze = Some(maze)

    maze.Ninja.x = 0
    maze.Ninja.y = 0
    maze.Ninja.action = Idle
    maze.actionQueue.dequeueAll(_ => true)
    js.eval(editor.getText)
  }

  def reset():Unit = {
    instructions = List.empty
    editor.setText("")
    maze.Ninja.x = 0
    maze.Ninja.y = 0
    maze.Ninja.action = Idle
  }

  def render = {

    logger.debug(s"Rendering stage 1")

    challengeLayout(1, "Show me an example")(<.div(
      hgutter,

      split(
        card("Programming by example")(
          cardText(
            <.p("Use the buttons to guide the ninja through the maze. As you do, I'll write the program for you!"),
            <.p("Click Run at any time to see it in action")
          ),
          cardText(
            <.div(^.cls := "btn-group",
              <.button(^.cls := "btn btn-secondary", ^.onClick --> tryDown, "Down"),
              <.button(^.cls := "btn btn-secondary", ^.onClick --> tryRight, "Right")
            )
          ),
          cardText(<.p(),
            editor
          ),
          cardText(<.p(),
            <("div", "stage1ctrl")(^.cls := "btn-group",
              <.button(^.cls := "btn btn-outline-secondary", ^.onClick --> reset, "Reset"),
              <.button(^.cls := "btn btn-outline-primary", ^.onClick --> run, "Run")
            )
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
          <.p("You've reached the goal. Click the run button to see it run your script. And before you move on, copy and paste the text.")
        )
      } else <.div(),
      Stage.pageControls(reachedGoal)
    ))

  }

  def next(): Unit = {
    Routing.routeTo(StageR(2))
  }

  def tryDown() = {
    if (maze.Ninja.canMove(Maze.SOUTH)) {
      instructions = instructions match {
        case Down(x) :: tail => Down(x + 1) :: tail
        case l => Down(1) :: l
      }
      editor.setText(instructions.reverse.map(_.stringify).mkString("\n"))
      maze.Ninja.moveNow(Maze.SOUTH)
    }
  }

  def tryRight() = {
    if (maze.Ninja.canMove(Maze.EAST)) {
      instructions = instructions match {
        case Right(x) :: tail => Right(x + 1) :: tail
        case l => Right(1) :: l
      }
      editor.setText(instructions.reverse.map(_.stringify).mkString("\n"))
      maze.Ninja.moveNow(Maze.EAST)
    }
  }

  sealed trait Instr {
    def stringify:String
  }
  case class Right(i:Int) extends Instr {
    def stringify = s"right($i)"
  }
  case class Down(i:Int) extends Instr {
    def stringify = s"down($i)"
  }

}
