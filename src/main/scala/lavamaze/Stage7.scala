package lavamaze

import com.wbillingsley.veautiful.{<, ^}

import scala.scalajs.js

object Stage7 {
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

  val maze:Maze = new Maze("Stage 7",
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
      stageHeader(7, "Enter the Blobs!"),
      hgutter,

      split(
        card(
          cardText(
            <.p("The Blob Guards have arrived... only usually our ninja still escapes."),
            <.p("The question is - what did we do?")
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
              <.p(^.cls := "congrats", "Code: 001"),
              <.p("Well that was down-right trivial! But you know we're going to spoil the party in the next stage..."),
              <("div", "stage7")(^.cls := "btn-group",
                <.button(^.cls := "btn btn-outline-secondary", ^.onClick --> prev, "Stage 6"),
                <.button(^.cls := "btn btn-outline-primary", ^.onClick --> next, "Stage 8")
              )
            )
          } else <("div", "stage7")(^.cls := "btn-group",
            <.button(^.cls := "btn btn-outline-secondary", ^.onClick --> prev, "Stage 6"),
            <.button(^.cls := "btn btn-outline-light", ^.onClick --> next, "Stage 8")
          )
        )

      )
    )

  }

  def prev(): Unit = {
    Routing.routeTo(Routing.Stage6R)
  }

  def next(): Unit = {
    Routing.routeTo(Routing.Stage8R)
  }
}
