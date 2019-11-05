package lavamaze

import com.wbillingsley.veautiful.{<, ^}
import org.scalajs.dom
import org.scalajs.dom.raw.HTMLInputElement

import scala.scalajs.js

object Stage3 extends Stage {
  import Headers._

  var reachedGoal = false
  val code = "+0110"
  val number = 3
  val name = "Cards of Doom"

  val maze:Maze = new Maze("Stage 2", onGoal = () => {
    reachedGoal = true
    println("Complete")
    Routing.afterAttach()
  })

  maze.makePath()

  def checkPassword(e:dom.Event): Unit = {
    val s = e.target match {
      case x:HTMLInputElement => x.value
    }
    if (s == "algorithm") {
      reachedGoal = true
      Routing.afterAttach()
    }
  }

  def render = {

    challengeLayout(number, name, reachedGoal)(<.div(
      hgutter,

      split(
        card("Password")(
          <.div(
            <("input")(^.cls := "form-control", ^.attr("type") := "text", ^.on("input") ==> checkPassword)
          )
        )
      )(
        card("Algorithms")(
          cardText(
            <.div(
              <.p("It's time we started writing programs that can cope with change. But first, let's play a game."),
              <.p(
                "In this task, your job is to beat one of the facilitators at Cards of Doom. Here are the rules:"
              ),
              <.p(
                "Each deck has 13 cards. On your turn, you must pick up 1, 2, or 3 cards. The player who takes the last card loses. It's the CARD of DOOM. The facilitator always goes first, and your job is to win."
              ),
              <.p(
                "So, first things first: CLAP YOUR HANDS so we know you're up to this stage and ready."
              )
            )
          )
        )
      ),
      hgutter,
      if (reachedGoal) {
        <.div(
          <.p(^.cls := "congrats", s"Code: $code"),
          <.p("Cards of Doom champion!")
        )
      } else <.div(),
      Stage.pageControls(reachedGoal)

    ))

  }

  def prev(): Unit = {
    Routing.routeTo(StageR(2))
  }

  def next(): Unit = {
    Routing.routeTo(StageR(4))
  }
}
