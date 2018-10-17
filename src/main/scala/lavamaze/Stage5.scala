package lavamaze

import com.wbillingsley.veautiful.{<, ^}
import lavamaze.Stage4.{editor, reachedGoal}
import org.scalajs.dom
import org.scalajs.dom.raw.HTMLInputElement

import scala.scalajs.js

object Stage5 {
  import Headers._

  val editor = new CodeEditor(text =
    """
      |if (canGoRight()) {
      |  right(1)
      |} else down(1)
    """.stripMargin, rows=8, disabled=true)

  var reachedGoal = false

  val maze:Maze = new Maze("Stage 5",
    defaultAction = () => {
      js.eval(editor.getText)
      Idle
    }
  )

  maze.makeSpoilerPath()

  def run(): Unit = {
    Commands.activeMaze = Some(maze)

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
    if (s == "treacle") {
      reachedGoal = true
      Routing.afterAttach()
    }
  }

  def node() = {

    println(s"Rendering stage 3")

    <.div(
      stageHeader(5, "Down-right annoying"),
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
                <.button(^.cls := "btn btn-primary", ^.onClick --> run, "Run")
              ),
              <.h4("Password"),
              <("input")(^.cls := "form-control", ^("type") := "text", ^.on("input") ==> checkPassword)
            )
          ),
          hgutter,
          if (reachedGoal) {
            <.div(
              <.p(^.cls := "congrats", "Code: 001"),
              <.p("Poster posted, on to try it out..."),
              <("div", "stage5")(^.cls := "btn-group",
                <.button(^.cls := "btn btn-outline-secondary", ^.onClick --> prev, "Stage 4"),
                <.button(^.cls := "btn btn-outline-primary", ^.onClick --> next, "Stage 6")
              )
            )
          } else <("div", "stage5")(^.cls := "btn-group",
            <.button(^.cls := "btn btn-outline-secondary", ^.onClick --> prev, "Stage 4"),
            <.button(^.cls := "btn btn-outline-light", ^.onClick --> next, "Stage 6")
          )
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

    )

  }

  def prev(): Unit = {
    Routing.routeTo(Routing.Stage4R)
  }

  def next(): Unit = {
    Routing.routeTo(Routing.Stage6R)
  }
}
