package lavamaze

import com.wbillingsley.veautiful.{<, ^}
import lavamaze.StageSpoilerPaths.reachedGoal
import org.scalajs.dom
import org.scalajs.dom.Event
import org.scalajs.dom.raw.HTMLInputElement

import scala.scalajs.js

object StageBlobs extends Stage {
  import Headers._

  val editor = new CodeEditor(text =
    """let d = lookHere()
      |if (look(0) < d) {
      |  right(1)
      |} else if (look(1) < d) {
      |  down(1)
      |} else if (look(2) < d) {
      |  left(1)
      |} else if (look(3) < d) {
      |  up(1)
      |}
    """.stripMargin, rows=10, disabled=true)

  var reachedGoal = false
  val code = "+0001"
  val number = 9
  val name = "Enter the Blobs"

  val maze:Maze = new Maze("Stage 7",
    w = 12, h = 8,
    onGoal = () => {
      reachedGoal = true
      Routing.rerender()
    }
  )

  maze.makePath()
  maze.makeSpoilerPath()
  maze.showRoutes = true
  maze.routesConsiderMonsters = true
  maze.createBlobs(2)

  val run: (Event) => Unit = { x =>
    maze.setActionAlgorithm(editor.getText)
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

    challengeLayout(number, name, reachedGoal)(<.div(
      hgutter,

      split(
        textColumn(
          <.h2("Escapology"),
          <.p("The Blob Guards have arrived... how can we escape without running into a blob?"),
          <.p("The algorithm on this page won't always make it. All it's doing is deciding that jelly can't flow through blobs when it's numbering the squares"),
          <.p("Give it a few runs and see if it reaches the exit."),
          cardText(<.p(),
            editor
          ),
          cardText(<.p(),
            <.button(^.cls := "btn btn-primary", ^.onClick ==> run, "Run")
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
      )(
        textColumn(
          <.div(^.cls := "split2 split-top-right",
            <.div(),
            maze
          ),
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
