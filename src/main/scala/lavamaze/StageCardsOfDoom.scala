package lavamaze

import com.wbillingsley.veautiful.{<, ^}
import org.scalajs.dom
import org.scalajs.dom.raw.HTMLInputElement

import scala.scalajs.js
import scala.util.Random

object StageCardsOfDoom extends Stage {
  import Headers._

  var reachedGoal = false
  val code = "67"
  val number = 4
  val name = "Cards of Doom"

  var lost = 0

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
        textColumn(
          <.h2("Algorithms"),
          <.p(
            """
              | Let's play a game called Cards of Doom.
              |""".stripMargin),
          <.p(
            """
              | We start with 13 cards. On your turn, you can take 1, 2, or 3 cards. Then it's my turn and I take 1, 2, or 3 cards. And so on.
              |""".stripMargin),
          <.p("The person who picks up the last card (the Card of Doom) loses."),
          if (reachedGoal) {
            <.div(
              <.p("What a champion! You beat the computer"),
              <.p(^.cls := "congrats", s"Code: $code")
            )
          } else {
            if (lost < 2) {
              <.p(
                """
                  | I'll go first. To solve this puzzle, you have to win a round...
                  |""".stripMargin)
            } else {
              <.p(^.cls := "pulse-link",
                """
                  | Ok, you've lost two rounds. Don't worry - everybody does. CLAP so we know you're up to this stage
                  | and we'll show you how it works.
                  |""".stripMargin
              )
            }
          },
          Stage.pageControls(reachedGoal)
        )
      )(
        textColumn(
          card("Cards of Doom")(
            cardText(
              if (CardsOfDoom.remaining > 0) {
                if (CardsOfDoom.remaining > 1) {
                  s"There are ${CardsOfDoom.remaining} cards remaining."
                } else {
                  "There is 1 card remaining."
                }
              } else {
                if (CardsOfDoom.iWin) {
                  <.div(
                    <.p("Oh no! You took the Card of Doom! I win this round!"),
                    <.button(^.cls := "btn btn-outline-primary", "Play again", ^.onClick --> CardsOfDoom.reset(false))
                  )
                } else {
                  <.div(
                    <.p("Congratulations! I took the Card of Doom! You win!"),
                    <.button(^.cls := "btn btn-outline-primary", "Play again", ^.onClick --> CardsOfDoom.reset(true))
                  )
                }
              }
            ),
            cardText(^.cls := "cod-deck",
              for (i <- 0 until CardsOfDoom.remaining) yield {
                <.div(^.cls := s"cod-card cod-card-$i",
                  i match {
                    case 0 => "A"
                    case 10 => "J"
                    case 11 => "Q"
                    case 12 => "K"
                    case _ => (i+1).toString
                  }
                )
              }
            ),
            cardText(
              if (CardsOfDoom.remaining > 0) {
                if (CardsOfDoom.myTurn) {
                  <.div(
                    <.p(s"It's my turn. I will take ${ CardsOfDoom.iWillTake } cards"),
                    <.button(^.cls := "btn btn-outline-primary", "Play my turn", ^.onClick --> CardsOfDoom.playMyturn() )
                  )
                } else {
                  val max = if (CardsOfDoom.remaining > 3) 3 else CardsOfDoom.remaining

                  <.div(
                    <.p("It's your turn. How many cards will you take?"),
                    for { i <- 1 to max } yield {
                      <.button(^.cls := "btn btn-outline-primary", s"Take $i", ^.onClick --> CardsOfDoom.play(i))
                    }
                  )
                }
              } else {
                <.div()
              }
            )
          )
        )
      )

    ))

  }

  def prev(): Unit = {
    Routing.routeTo(StageR(2))
  }

  def next(): Unit = {
    Routing.routeTo(StageR(4))
  }

  object CardsOfDoom {

    var remaining = 13
    var myTurn = true

    var iWillTake = choose()

    def choose():Int = {
      if (remaining > 0) {
        val mod = (remaining - 1) % 4
        val max = if (remaining > 3) 3 else remaining
        if (mod > 0) mod else Random.nextInt(max) + 1
      } else 0
    }

    def play(i:Int):Unit = {
      if (!myTurn && i > 0 && i < 4 && i <= remaining) {
        remaining = remaining - i
        myTurn = true
        iWillTake = choose()

        if (iWin) lost = lost + 1

        rerender()
      }
    }

    def playMyturn():Unit = {
      if (myTurn && remaining > 0) {
        remaining = remaining - iWillTake
        myTurn = false

        if (youWin) reachedGoal = true

        rerender()
      }
    }

    def iWin = remaining == 0 && myTurn

    def youWin = remaining == 0 && !myTurn

    def reset(won:Boolean) = {
      remaining = 13
      myTurn = true
      iWillTake = choose()
      rerender()
    }

  }
}
