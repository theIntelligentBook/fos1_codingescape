package lavamaze

import com.wbillingsley.veautiful.{<, ^}

import scala.scalajs.js

object Stage2 {
  import Headers._

  val editor = new CodeEditor(text = "", rows=8, disabled=false)

  var reachedGoal = false

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

  def node() = {

    println(s"Rendering stage 2")

    <.div(
      stageHeader(2, "Now you write it"),
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
          <.p(^.cls := "congrats", "Code: 001"),
          <.p("Well done. But I wouldn't want to rewrite my code for every maze. Time to move on..."),
          <("div", "stage2")(^.cls := "btn-group",
            <.button(^.cls := "btn btn-outline-secondary", ^.onClick --> prev, "Stage 1"),
            <.button(^.cls := "btn btn-outline-primary", ^.onClick --> next, "Stage 3")
          )
        )
      } else <("div", "stage2")(^.cls := "btn-group",
        <.button(^.cls := "btn btn-outline-secondary", ^.onClick --> prev, "Stage 1"),
        <.button(^.cls := "btn btn-outline-light", ^.onClick --> next, "Stage 3")
      )

    )

  }

  def prev(): Unit = {
    Routing.routeTo(Routing.Stage1R)
  }

  def next(): Unit = {
    Routing.routeTo(Routing.Stage3R)
  }
}
