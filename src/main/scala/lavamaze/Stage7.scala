package lavamaze

import com.wbillingsley.veautiful.{<, ^}
import lavamaze.Stage5.reachedGoal
import org.scalajs.dom
import org.scalajs.dom.raw.HTMLInputElement

import scala.scalajs.js

object Stage7 extends Stage {
  import Headers._

  val editor = new CodeEditor(text =
    """let d = ownDistance()
      |if (look(0) < d) {
      |  right(1)
      |} else if (look(1) < d) {
      |  down(1)
      |} else if (look(2) < d) {
      |  left(1)
      |} else if (look(3) < d) {
      |  up(1)
      |}
    """.stripMargin, rows=8, disabled=true)

  var reachedGoal = false
  val code = "+0001"
  val number = 7
  val name = "Enter the Blobs"

  val maze:Maze = new Maze("Stage 7",
    w = 12, h = 8,
    defaultAction = () => {
      js.eval(editor.getText)
      Idle
    }
  )

  maze.makePath()
  maze.makeSpoilerPath()
  maze.showRoutes = true
  maze.routesConsiderMonsters = true
  maze.createBlobs(2)

  def run(): Unit = {
    Commands.activeMaze = Some(maze)

    maze.Ninja.alive = true
    maze.Ninja.x = 0
    maze.Ninja.y = 0
    maze.Ninja.action = Idle
    maze.actionQueue.dequeueAll(_ => true)
    js.eval(editor.getText)
  }

  def checkPassword(e:dom.Event): Unit = {
    val s = e.target match {
      case x:HTMLInputElement => x.value
    }
    if (s == "gloop") {
      reachedGoal = true
      Routing.afterAttach()
    }
  }

  def render = {

    challengeLayout(number, name)(<.div(
      hgutter,

      split(
        card("Escapology")(
          cardText(
            <.p("The Blob Guards have arrived... how can we escape without running into a blob?"),
            <.p("Here's our little attempt, but let's chat about alternatives. CLAP to let us know you're up to this stage.")
          ),
          cardText(<.p(),
            editor
          ),
          cardText(<.p(),
            <.button(^.cls := "btn btn-primary", ^.onClick --> run, "Run")
          ),
          <.h4("Password"),
          <("input")(^.cls := "form-control", ^.attr("type") := "text", ^.on("input") ==> checkPassword)
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
              <.p("But now it is time for our own escape with the final code...")
            )
          } else <.div(),
          Stage.pageControls(reachedGoal)
        )

      )
    ))

  }

  def prev(): Unit = {
    Routing.routeTo(StageR(6))
  }

  def next(): Unit = {
    Routing.routeTo(StageR(8))
  }
}
