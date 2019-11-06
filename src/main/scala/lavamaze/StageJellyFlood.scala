package lavamaze

import com.wbillingsley.veautiful.{<, DiffComponent, DiffNode, ^}
import org.scalajs.dom
import org.scalajs.dom.raw.HTMLInputElement

import scala.util.Random

object StageJellyFlood extends Stage {
  import Headers._

  var reachedGoal = false
  val code = "69"
  val number = 7
  val name = "Jelly Flood!"

  val maze:Maze = new Maze("Stage 5")
  maze.makeSpoilerPath()
  maze.check(maze.w - 1, maze.h - 1, 0)

  val jellyCells = for {
    y <- 0 until maze.h
    x <- 0 until maze.w
  } yield new JellyCell(x, y)


  def render = {

    challengeLayout(number, name, reachedGoal)(<.div(
      hgutter,

      split(
        textColumn(
          <.h2("Flood fill algorithm"),
          <.p(
            """
              | Imagine someone is pouring molten jelly on the goal square and it flows out at one square per turn.
              |""".stripMargin),
          <.p("After zero turns, it's just on the goal square."),
          <.p("After 1 turn, it'd move to the neighbouring squares."),
          <.p("After 2 turns, it'd move to their neighbours."),
          <.p("Let's animate our jelly flood! Use the controls on the right"),
          if (reachedGoal) {
            <.div(
              <.p(^.cls := "congrats", s"Code: $code"),
              <.p("Now all our ninja needs to think about is ", <("i")("'Which way will the jelly reach me from first?'")),
              <.p("So long as we keep moving to lower-numbered squares, we should reach the goal")
            )
          } else {
            <.div()

          },
          Stage.pageControls(reachedGoal)
        )
      )(
        textColumn(
          <.div(^.cls := "split2 split-top-right",
            <.div(),
            <.div(
              card("Jelly Flood")(
                cardText(
                  <.div(^.cls := "jelly-grid",
                    for {
                      y <- 0 until maze.h
                    } yield <.div(^.cls := "jelly-row",
                      for {
                        x <- 0 until maze.w
                      } yield {
                        jellyCells(y * maze.w + x)
                      }
                    )

                  ),
                  <.div()
                ),
                cardText(
                  <("div", "stage1ctrl")(^.cls := "btn-group",
                    <.button(^.cls := "btn btn-outline-secondary", ^.onClick --> { tick = 0; rerender() }, "Reset"),
                    <.button(^.cls := "btn btn-outline-primary", ^.onClick --> {
                      tick = tick + 1
                      if (tick >= maze.goalDistance(0, 0)) {
                        reachedGoal = true
                      }
                      rerender()
                    }, "Step")
                  )
                )
              )
            )

          )
        )
      )

    ))

  }

  var tick = 0

  class JellyCell(x:Int, y:Int) extends DiffComponent {

    var clicked = false

    val d = maze.goalDistance(x, y)
    val maxTick = maze.w * maze.h

    override protected def render: DiffNode = <.div(^.cls := "jelly-cell",
      if (tick >= d) {
        <.div(^.cls := (if (tick == d) "jelly active" else "jelly"), d.toString)
      } else {
        if (d < maxTick) {
          <.div(^.cls := "floor")
        } else {
          <.div(^.cls := "lava")
        }
      }
    )

  }

}
