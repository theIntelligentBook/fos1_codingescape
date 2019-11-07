package lavamaze

import com.wbillingsley.scatter.{Tile, TileSpace}
import com.wbillingsley.scatter.jstiles.{FunctionCall, IfElseTile, JSExpr, JSLang, JSNumber}
import com.wbillingsley.veautiful.logging.Logger
import com.wbillingsley.veautiful.{<, ^}
import org.scalajs.dom.Event

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport
import scala.util.Random

object StageCountdown extends Stage {
  import Headers._

  val logger:Logger = Logger.getLogger(StageCountdown.getClass)

  val editor = new CodeEditor(text = "", rows=8, disabled=false)

  var reachedGoal = false
  val code = "68"
  val number = 8
  val name = "Countdown"

  val maze:Maze = new Maze("Stage 4",
    defaultAction = () => {
      Idle
    },
    onGoal = () => {
      reachedGoal = true
      Routing.rerender()
    }
  )

  maze.makeSpoilerPath()
  maze.showRoutes = true

  @JSExport
  val scatterCanvas = new TileSpace(Some("example"), JSLang)((512, 640))
  val pt = new WhenTile(scatterCanvas, "When a new action is needed")
  pt.x = 2
  pt.y = 2

  val dt = new DeleteTile(scatterCanvas)
  dt.x = 420
  dt.y = 2

  scatterCanvas.tiles.appendAll(Seq(pt, dt))

  def addTile(t:Tile[JSExpr]) = {
    t.x = 250 + Random.nextInt(10)
    t.y = 100 + Random.nextInt(10)
    scatterCanvas.tiles.append(t)
    scatterCanvas.update()
    scatterCanvas.layout()
  }

  var error:Option[String] = None


  def addDownTile():Unit = {
    addTile(new SnippetTile(scatterCanvas, "down(1)", FunctionCall("down", Seq(JSNumber(1)))))
  }

  def addRightTile():Unit = {
    addTile(new SnippetTile(scatterCanvas, "right(1)", FunctionCall("right", Seq(JSNumber(1)))))
  }

  def addUpTile():Unit = {
    addTile(new SnippetTile(scatterCanvas, "up(1)", FunctionCall("up", Seq(JSNumber(1)))))
  }

  def addLeftTile():Unit = {
    addTile(new SnippetTile(scatterCanvas, "left(1)", FunctionCall("left", Seq(JSNumber(1)))))
  }

  def addOwnDistanceTile():Unit = {
    addTile(new SnippetTile(scatterCanvas, "lookHere()", FunctionCall("lookHere", Seq.empty), returnType = "Number"))
  }


  def addLookUpTile():Unit = {
    addTile(new SnippetTile(scatterCanvas, "lookUp()", FunctionCall("lookUp", Seq.empty), returnType = "Number"))
  }

  def addLookLeftTile():Unit = {
    addTile(new SnippetTile(scatterCanvas, "lookLeft()", FunctionCall("lookLeft", Seq.empty), returnType = "Number"))
  }

  def addLookDownTile():Unit = {
    addTile(new SnippetTile(scatterCanvas, "lookDown()", FunctionCall("lookDown", Seq.empty), returnType = "Number"))
  }

  def addLookRightTile():Unit = {
    addTile(new SnippetTile(scatterCanvas, "lookRight()", FunctionCall("lookRight", Seq.empty), returnType = "Number"))
  }

  def addIfElseTile():Unit = {
    addTile(new IfElseTile(scatterCanvas))
  }

  def addLessThanTile():Unit = {
    addTile(new LessThanTile(scatterCanvas))
  }

  def run():Unit = {
    maze.clear()
    maze.makeSpoilerPath()

    try {
      val programText = pt.toLanguage.toJS(0)
      error = None
      rerender()
      logger.info("Program text follows:")
      logger.info(programText)
      maze.setActionAlgorithm(programText)
    } catch {
      case x:Throwable =>
        error = Some(x.getMessage)
        rerender()
    }
  }

  def reset():Unit = {
    editor.setText("")
    maze.Ninja.x = 0
    maze.Ninja.y = 0
    maze.Ninja.action = Idle
  }

  def render = {

    challengeLayout(number, name, reachedGoal)(<.div(
      hgutter,

      split(
        textColumn(
          <.h2("Back through the flood"),
          <.p("We've numbered the squares for you. But you've still got some fiddly code to write."),
          <.p("Some new functions let you get the distance numbers from squares"),
          <("pre")(
            """ lookHere()  // the number of the square the ninja is on
              | lookUp()    // the number of the square above
              | lookDown()  // the number of the square below the ninja
              | lookLeft()  // the number of the square to the left
              | lookRight() // the number of the square to the right
              | ... < ...   // compares two numbers
              |""".stripMargin),
          <.p(
            "Once again, the goal is to get to the exit"
          ),
          hgutter,
          if (reachedGoal) <.div(
            <.p(^.cls := "congrats", s"Code: $code"),
            <.p("That ninja's reached the end. So, nearly, have we!")
          ) else <.div(),
          Stage.pageControls(reachedGoal)
        )
      )(
        textColumn(
          <.div(^.cls := "split2 split-top-right",
            <.div(),
            maze,
            hgutter,hgutter,
            <.div(^.cls := "btn-group-vertical align-top pr-1",
              <.button(^.cls := "btn btn-outline-secondary", ^.onClick --> addUpTile(), "up(1)"),
              <.button(^.cls := "btn btn-outline-secondary", ^.onClick --> addLeftTile(), "left(1)"),
              <.button(^.cls := "btn btn-outline-secondary", ^.onClick --> addRightTile(), "right(1)"),
              <.button(^.cls := "btn btn-outline-secondary", ^.onClick --> addDownTile(), "down(1)"),
              <.button(^.cls := "btn btn-outline-secondary", ^.onClick --> addOwnDistanceTile(), "lookHere()"),
              <.button(^.cls := "btn btn-outline-secondary", ^.onClick --> addLookUpTile(), "lookUp()"),
              <.button(^.cls := "btn btn-outline-secondary", ^.onClick --> addLookLeftTile(), "lookLeft()"),
              <.button(^.cls := "btn btn-outline-secondary", ^.onClick --> addLookRightTile(), "lookRight()"),
              <.button(^.cls := "btn btn-outline-secondary", ^.onClick --> addLookDownTile(), "lookDown()"),
              <.button(^.cls := "btn btn-outline-secondary", ^.onClick --> addIfElseTile(), "if ... else ..."),
              <.button(^.cls := "btn btn-outline-secondary", ^.onClick --> addLessThanTile(), ".. < ..."),
              <.button(^.cls := "btn btn-primary", ^.onClick --> run(), "Run")
            ),
            <.div(^.cls := "canvas-scroll",
              scatterCanvas,
              error.toSeq.map(s => <.p(^.cls := "error-message", s))
            )
          )
        )

      )
    ))

  }

}


