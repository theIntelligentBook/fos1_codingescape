package lavamaze

import com.wbillingsley.scatter.{HBox, Socket, Tile, TileComponent, TileSpace, TileText}
import com.wbillingsley.scatter.jstiles.{FunctionCall, FunctionCallTile, IfElseTile, JSExpr, JSLang, JSNumber, ProgramTile}
import com.wbillingsley.veautiful.logging.Logger
import com.wbillingsley.veautiful.{<, ^}
import org.scalajs.dom.Event

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport
import scala.util.Random

object StageIf extends Stage {
  import Headers._

  val logger:Logger = Logger.getLogger(StageIf.getClass)

  var reachedGoal = false
  val code = "+0001"
  val number = 5
  val name = "If only..."

  @JSExport
  val scatterCanvas = new TileSpace(Some("example"), JSLang)((512, 384))
  val pt = new ProgramTile(scatterCanvas, <.button(^.cls := "btn btn-sm btn-primary", ^.onClick --> run(), "Run"))
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

  def addDownTile():Unit = {
    addTile(new SnippetTile(scatterCanvas, "down(1)", FunctionCall("down", Seq(JSNumber(1)))))
  }

  def addRightTile():Unit = {
    addTile(new SnippetTile(scatterCanvas, "right(1)", FunctionCall("right", Seq(JSNumber(1)))))
  }

  def addCanGoDownTile():Unit = {
    addTile(new SnippetTile(scatterCanvas, "canGoDown", FunctionCall("canGoDown", Seq.empty), returnType = "Boolean"))
  }

  def addCanGoRightTile():Unit = {
    addTile(new SnippetTile(scatterCanvas, "canGoRight", FunctionCall("canGoRight", Seq.empty), returnType = "Boolean"))
  }

  def addIfElseTile():Unit = {
    addTile(new IfElseTile(scatterCanvas))
  }


  val maze:Maze = new Maze("Stage 4",
    defaultAction = () => {
      //js.eval(editor.getText)
      Idle
    },
    onGoal = () => {
      reachedGoal = true
      println("Complete")
      Routing.afterAttach()
    }
  )

  maze.makePath()

  def run():Unit = {
    val programText = pt.toLanguage.toJS(0)
    logger.info("Program text follows:")
    logger.info(programText)
    maze.setActionAlgorithm(programText)
  }

  def reset():Unit = {
    maze.Ninja.x = 0
    maze.Ninja.y = 0
    maze.Ninja.action = Idle
  }

  def render = {

    challengeLayout(number, name, reachedGoal)(<.div(
      hgutter,

      split(
        textColumn(
          <.h2("Finding the way"),
          <.p(
            """
              | Up until now, we've given the ninja a fixed set of instructions. That only works if we know the maze
              | in advance.
              |""".stripMargin
          ),
          <.p(
            """
              | In this puzzle, we're going to give the Ninja a way of deciding what to do next. Instead of calling
              | our code once, it's going to call it whenever the ninja needs a new action to perform.
            """.stripMargin
          ),
          <.p(
            """
              | We're also introducing the "if" statement. For example
              |""".stripMargin
          ),
          <("pre")(
            """
              | if (raining) {
              |   take(umbrella)
              | } else {
              |   wear(sunscreen)
              | }
            """.stripMargin
          ),
          if (reachedGoal) {
            <.div(
              <.p(^.cls := "congrats", s"Code: $code"),
              <.p("Well that was down-right trivial! But you know we're going to spoil the party in the next stage...")
            )
          } else <.div(),
          Stage.pageControls(reachedGoal)
        )
      )(
        textColumn(
          <.div(^.cls := "split2 split-top-right",
            <.div(),
            maze,
            hgutter,hgutter,
            <.div(^.cls := "btn-group-vertical align-top pr-1",
              <.button(^.cls := "btn btn-outline-secondary", ^.onClick --> addDownTile(), "down(1)"),
              <.button(^.cls := "btn btn-outline-secondary", ^.onClick --> addRightTile(), "right(1)"),
              <.button(^.cls := "btn btn-outline-secondary", ^.onClick --> addCanGoDownTile(), "canGoDown"),
              <.button(^.cls := "btn btn-outline-secondary", ^.onClick --> addCanGoRightTile(), "canGoRight"),
              <.button(^.cls := "btn btn-outline-secondary", ^.onClick --> addIfElseTile(), "if ... else ...")
            ),
            <.div(^.cls := "canvas-scroll",
              scatterCanvas
            )
          )
        )
      )
    ))

  }

}

class SnippetTile(tileSpace:TileSpace[JSExpr], name:String, expr:JSExpr, override val returnType:String = "void") extends Tile(tileSpace) {

  override val tileContent = {
    HBox(
      TileText[JSExpr](name)
    )
  }

  override def toLanguage: JSExpr = expr

}