package lavamaze

import com.wbillingsley.veautiful.{<, ^}

import scala.scalajs.js

object Stage6 {
  import Headers._

  val editor = new CodeEditor(text = "", rows=8, disabled=false)

  var reachedGoal = false
  val code = "+1000"

  val maze:Maze = new Maze("Stage 6",
    defaultAction = () => {
      js.eval(editor.getText)
      Idle
    },
    onGoal = () => {
      reachedGoal = true
      Routing.afterAttach()
    }
  )

  maze.makeSpoilerPath()
  maze.showRoutes = true

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

  def node() = {

    println(s"Rendering stage 4")

    <.div(
      stageHeader(6, "Cwonnutod"),
      hgutter,

      split(
        card(
          cardText(
            <.p("We've done the numbering for you. But you've still got some fiddly code to write. (We're here to help!) Let's start by putting our own distance in a variable."),
            <("pre")("let d = ownDistance()"),
            <.p(
              "Next, look in each direction. The directions are numbered 0, 1, 2, and 3. If we call look with a direction, it'll give us the distance of that square, or 99 if it's impassable"
            ),
            <("pre")("let r = look(0)"),
            <.p("And what we're looking for is a direction whose value is smaller than ours")
          ),
          cardText(<.p(),
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
              <.p("This maze's days are numbered! (Or it's tiles at least). Just as well there's no guards around yet..."),
              <("div", "stage2")(^.cls := "btn-group",
                <.button(^.cls := "btn btn-outline-secondary", ^.onClick --> prev, "Stage 5"),
                <.button(^.cls := "btn btn-outline-primary", ^.onClick --> next, "Stage 7")
              )
            )
          } else <("div", "stage2")(^.cls := "btn-group",
            <.button(^.cls := "btn btn-outline-secondary", ^.onClick --> prev, "Stage 5"),
            <.button(^.cls := "btn btn-outline-light", ^.onClick --> next, "Stage 7")
          )
        )

      )
    )

  }

  def prev(): Unit = {
    Routing.routeTo(Routing.Stage5R)
  }

  def next(): Unit = {
    Routing.routeTo(Routing.Stage7R)
  }
}
