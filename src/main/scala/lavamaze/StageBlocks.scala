package lavamaze

import com.wbillingsley.scatter.{Tile, TileComponent, TileSpace}
import com.wbillingsley.scatter.jstiles.{FunctionCall, FunctionCallTile, JSExpr, JSLang, NumberInputTile, ProgramTile}
import com.wbillingsley.veautiful.logging.Logger
import com.wbillingsley.veautiful.{<, DiffNode, ^}
import lavamaze.Headers.{challengeLayout, hgutter, split, textColumn}

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}
import scala.util.Random

@JSExportTopLevel("StageBlocks")
object StageBlocks extends Stage {

  val logger:Logger = Logger.getLogger(Stage1.getClass)

  var reachedGoal: Boolean = false

  override def code: String = "69"

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
  val scatterCanvas = new TileSpace(Some("example"), JSLang)((512, 640))
  val pt = new ProgramTile(scatterCanvas, <.button(^.cls := "btn btn-sm btn-primary", ^.onClick --> run(), "Run"))
  pt.x = 2
  pt.y = 2

  val dt = new DeleteTile(scatterCanvas)
  dt.x = 420
  dt.y = 2

  scatterCanvas.tiles.appendAll(Seq(pt, dt))

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
              | The blocks make it easier to avoid typing errors.
              |""".stripMargin),
          <.p(
            """
              | In this exercise, we'll use a blocks language that looks like JavaScript when it's plugged together,
              | like in the video below.
              |""".stripMargin
          ),
          <.p(
            <("video")(^.cls := "scatter-video", ^.alt := "Scatter tiles being dragged and dropped",
              ^.src := "assets/scatter.mp4",
              ^.attr("controls") := "true", ^.attr("autoplay") := "true", ^.attr("loop") := "true", ^.attr("muted") := "true")
          ),
          <.p(
            """
              | Put your program into the sockets under the Run button, and click Run.
              | If you need to pull a tile back out of a socket, press and hold the mouse button on it for a second.
              |""".stripMargin
          ),
          if (reachedGoal) {
            <.div(
              <.p("There's no blocking your ninja!"),
              <.p(^.cls := "congrats", s"Code: $code")
            )
          } else <.p("Your goal, again, is to get the ninja to the goal square."),
        )
      )
    )(
      textColumn(
        <.div(^.cls := "split2 split-top-right",
          <.div(),
          maze,
          hgutter,hgutter,
          <.div(^.cls := "btn-group-vertical align-top pr-1",
            <.button(^.cls := "btn btn-outline-secondary", ^.onClick --> addDownTile(), "down(_)"),
            <.button(^.cls := "btn btn-outline-secondary", ^.onClick --> addRightTile(), "right(_)"),
            <.button(^.cls := "btn btn-outline-secondary", ^.onClick --> addNumberInputTile(), "Number Input"),
            <.button(^.cls := "btn btn-outline-primary", ^.onClick --> maze.Ninja.reset(), "Reset")
          ),
          <.div(^.cls := "canvas-scroll",
            scatterCanvas
          )
        )
      )
    )))
  }

  def addTile(t:Tile[JSExpr]) = {
    t.x = 250 + Random.nextInt(10)
    t.y = 100 + Random.nextInt(10)
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
