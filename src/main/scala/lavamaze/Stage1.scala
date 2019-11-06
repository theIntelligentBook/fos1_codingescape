package lavamaze

import com.wbillingsley.veautiful.logging.Logger
import com.wbillingsley.veautiful.{<, ^}
import example.ScalaJSExample
import org.scalajs.dom.Event

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("Stage1")
object Stage1 extends Stage {
  import Headers._

  val logger:Logger = Logger.getLogger(Stage1.getClass)

  val editor = new CodeEditor(text = "", rows=8, disabled=true)

  var stage = 0

  var runAuto = false

  @JSExport
  var reachedGoal = false

  val code = "32"

  override val number = 1

  override val name = "Show me the way"

  val maze:Maze = new Maze("Stage 1", onGoal = () => {
    if (runAuto) {
      stage = 2
      reachedGoal = true
    } else if (stage < 1) stage = 1
    Routing.rerender()
  })

  maze.makePath()

  var instructions = List.empty[Instr]

  val run: (Event) => Unit = { x =>
    runAuto = true
    maze.runCode(editor.getText)
  }

  def reset():Unit = {
    instructions = List.empty
    editor.setText("")
    maze.Ninja.reset()
  }

  def render = {

    logger.debug(s"Rendering stage 1")

    challengeLayout(1, "Show me the way to go home", reachedGoal)(<.div(
      hgutter,

      split(
        textColumn(
          <.h2("Programming by example"),
          <.p(^.cls := "",
            "Programs are instructions for computers to follow."
          ),
          <.p("Use the buttons next to the maze to guide the ninja through the maze. As you do, I'll write down what you did as a JavaScript program."),
          <.p("Click Run at any time to see the program you've written in action. "),
          <.p("Click Reset if you want to clear the program and put the ninja back at the start."),
          hgutter,
          if (stage == 1) {
            <.div(^.cls := "pulse-link",
              <.p("Your ninja's reached the goal controlling him by hand. Next, click the run button in the program box to see it run the program you wrote to guide him there."),
            )
          } else if (reachedGoal) {
            <.p(^.cls := "congrats", s"Stage complete. Code: $code")
          } else <.div(),
          Stage.pageControls(reachedGoal)
        )
      )(
        textColumn(
          <.div(^.cls := "split2 split-top-right",
            <.div(^.cls := "btn-group-vertical pr-1",
              <.button(^.cls := "btn btn-outline-secondary", ^.onClick --> { runAuto = false; tryRight() }, "Right"),
                <.button(^.cls := "btn btn-outline-secondary", ^.onClick --> { runAuto = false; tryDown() }, "Down")
            ),
            maze,
            hgutter, hgutter,
            <.div(),
            <.h3("I'll write your program here"),
            <("div", "stage1ctrl")(^.cls := "btn-group-vertical pr-1",
              <.button(^.cls := "btn btn-outline-secondary", ^.onClick --> reset, "Reset"),
              <.button(^.cls := (if (stage == 1) "btn btn-outline-primary pulse-link" else "btn btn-outline-primary"), ^.onClick ==> run, "Run")
            ),
            editor
          )

        )
      )
    ))

  }

  def next(): Unit = {
    Routing.routeTo(StageR(2))
  }

  def tryDown() = {
    if (maze.Ninja.action.done) {
      instructions = instructions match {
        case Down(x) :: tail => Down(x + 1) :: tail
        case l => Down(1) :: l
      }
      editor.setText(instructions.reverse.map(_.stringify).mkString("\n"))
      maze.Ninja.moveNow(Maze.SOUTH)
    }
  }

  def tryRight() = {
    if (maze.Ninja.action.done) {
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
