package lavamaze

import com.wbillingsley.veautiful.{<, ^}
import org.scalajs.dom
import org.scalajs.dom.raw.HTMLInputElement

object Stage8 extends Stage {
  import Headers._

  var reachedGoal = false
  val code = "ESCAPED!"
  val number = 8
  val name = "Final conundrum"

  val maze:Maze = new Maze("Stage 8", onGoal = () => {
    reachedGoal = true
    println("Complete")
    Routing.afterAttach()
  })

  maze.makePath()

  def checkPassword(e:dom.Event): Unit = {
    val s = e.target match {
      case x:HTMLInputElement => x.value
    }
    if (s == "-1") {
      reachedGoal = true
      Routing.afterAttach()
    }
  }

  def render = {
    challengeLayout(number, name, false)(<.div(
      hgutter,

      split(
        card("Password")(
          <.p("Maximum two characters"),
          <.div(
            <("input")(^.cls := "form-control", ^.attr("type") := "text", ^.attr("maxlength") := "2", ^.on("input") ==> checkPassword)
          )
        )
      )(
        card("I hope you are watching the clock!")(
          cardText(
            <.div(
              <.p("And so our final conundrum is a 4-bit calculation..."),
              <("pre")(
                if (Stage1.reachedGoal) Stage1.code else "(undiscovered)"
              ),
              <("pre")(
                if (Stage2.reachedGoal) Stage2.code else "(undiscovered)"
              ),
              <("pre")(
                if (StageCardsOfDoom.reachedGoal) StageCardsOfDoom.code else "(undiscovered)"
              ),
              <("pre")(
                if (StageIf.reachedGoal) StageIf.code else "(undiscovered)"
              ),
              <("pre")(
                if (Stage5.reachedGoal) Stage5.code else "(undiscovered)"
              ),
              <("pre")(
                if (Stage6.reachedGoal) Stage6.code else "(undiscovered)"
              ),
              <("pre")(
                if (Stage7.reachedGoal) Stage7.code else "(undiscovered)"
              ),
              <("pre")(
                "answer, in decimals"
              )
            )
          )
        )
      ),
      hgutter,
      if (reachedGoal) {
        <.div(
          <.p(^.cls := "congrats", "ESCAPED! ESCAPED! ESCAPED!"),
          <.p("Congratulations, coding ninja!")
        )
      } else <.div(),
      Stage.pageControls(false)
    ))

  }

  def prev(): Unit = {
    Routing.routeTo(StageR(7))
  }

}
