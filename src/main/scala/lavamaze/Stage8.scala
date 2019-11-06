package lavamaze

import com.wbillingsley.veautiful.{<, ^}
import org.scalajs.dom
import org.scalajs.dom.raw.HTMLInputElement

object Stage8 extends Stage {
  import Headers._

  var reachedGoal = false
  val code = "ESCAPED!"
  val number = 10
  val name = "Final conundrum"

  def checkPassword(e:dom.Event): Unit = {
    val s = e.target match {
      case x:HTMLInputElement => x.value
    }
    if (s.toUpperCase().trim() == "I ESCAPED!") {
      reachedGoal = true
      Routing.rerender()
    }
  }

  def render = {
    challengeLayout(number, name, false)(<.div(
      hgutter,
      textColumn(
        <.h2("The oldest code in the book"),
        <.p("Computers can only store binary. That means they have to represent letters as numbers."),
        <.p("This one's the 'oldest code in the book': ASCII."),
        <("pre")(
          """ 32  Space   71  G      81  Q
            | 33  !       72  H      82  R
            | 34  "       73  I      83  S
            | 35  #       74  J      84  T
            | 65  A       75  K      85  U
            | 66  B       76  L      86  V
            | 67  C       77  M      87  W
            | 68  D       78  N      88  X
            | 69  E       79  O      89  Y
            | 70  F       80  P      90  Z
            |""".stripMargin
        ),
        <.p(
          "Now you just need the numbers to translate. Where could they be?"
        ),
        card("Escape password")(
          cardText(
            <.p("Enter password to escape"),
            <("input")(^.cls := "form-control", ^.attr("type") := "text", ^.attr("maxlength") := "10", ^.on("input") ==> checkPassword)
          )
        ),
        hgutter,
        if (reachedGoal) {
          <.div(
            <.p(^.cls := "congrats", "Congratulations, coding ninja!")
          )
        } else <.div(),
        Stage.pageControls(false)
      )
    ))

  }

  def prev(): Unit = {
    Routing.routeTo(StageR(7))
  }

}
