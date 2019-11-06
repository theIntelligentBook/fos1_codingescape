package lavamaze

import com.wbillingsley.scatter.{Tile, TileComponent, TileSpace}
import com.wbillingsley.scatter.jstiles.{FunctionCall, FunctionCallTile, JSExpr, JSLang, NumberInputTile, ProgramTile}
import com.wbillingsley.veautiful.logging.Logger
import com.wbillingsley.veautiful.{<, DiffNode, ^}
import lavamaze.Headers.{challengeLayout, hgutter, split, textColumn}

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("StageBlocks")
object StageBlocks extends Stage {

  val logger:Logger = Logger.getLogger(Stage1.getClass)

  var reachedGoal: Boolean = false

  override def code: String = "32"

  override def number: Int = 2

  override def name: String = "Coding's a drag?"

  val maze:Maze = new Maze("Stage Blocks", onGoal = () => {
    reachedGoal = true
    Routing.rerender()
  })
  maze.makePath()

  def run(): Unit = {
    val programText = pt.toLanguage.toJS(0)
    logger.info("Program text follows:")
    logger.info(programText)
    maze.runCode(programText)
  }

  @JSExport
  val scatterCanvas = new TileSpace(Some("example"), JSLang)((512, 384))
  val pt = new ProgramTile(scatterCanvas, <.button(^.cls := "btn btn-sm btn-primary", ^.onClick --> run(), "Run"))

  scatterCanvas.tiles.append(pt)

  override protected def render: DiffNode = {

    challengeLayout(number, name, reachedGoal)(<.div(
    hgutter,

    split(
      textColumn(
        <.div(
          <.h2("Blocks programming"),
          <.p(
            """
              | You might have seen blocks languages like Scratch or Blockly before.
              | These are languages that give you little blocks that you drag and drop together to form a program.
              | The blocks make it easier to avoid typing errors, but it is slower dragging blocks together than typing.
              |""".stripMargin),
          <.p(
            """
              | In this exercise, we've got our own blocks language ("Scatter") that when you compose it looks like
              | JavaScript. Drop the program blocks into the green tile and click Run to run the program.
              | Your goal, again, is to get the ninja to the goal square.
              |""".stripMargin
          ),
          <.p(
            """
              | If you need to pull a tile back out of a socket, press and hold the mouse button on it for a second.
              |""".stripMargin
          )
        )
      )
    )(
      <.div(
        maze,
        scatterCanvas,
        <.div(
          <.div(^.cls := "btn-group",
            <.button(^.cls := "btn btn-outline-secondary", ^.onClick --> addDownTile(), "down(_)"),
            <.button(^.cls := "btn btn-outline-secondary", ^.onClick --> addRightTile(), "right(_)"),
            <.button(^.cls := "btn btn-outline-secondary", ^.onClick --> addNumberInputTile(), "Number Input")
          )
        )
      )
    )))
  }

  def addTile(t:Tile[JSExpr]) = {
    t.x = 250
    t.y = 100
    scatterCanvas.tiles.append(t)
    scatterCanvas.update()
    scatterCanvas.layout()
  }

  def addDownTile():Unit = {
    addTile(new FunctionCallTile(scatterCanvas, "down", Seq("Number")))
  }

  def addRightTile():Unit = {
    addTile(new FunctionCallTile(scatterCanvas, "right", Seq("Number")))
  }

  def addNumberInputTile():Unit = {
    addTile(new NumberInputTile(scatterCanvas, initial = Some(1)))
  }


}
