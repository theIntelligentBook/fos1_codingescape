package lavamaze

import org.scalajs.dom

import scala.collection.mutable
import scala.scalajs.js.annotation._

@JSExportTopLevel("Commands")
object Commands {

  @JSExport
  def ping() = {
    println("ping")
  }

  var activeMaze:Option[Maze] = None

  @JSExport
  def right(i:Int) = activeMaze.foreach { m => m.actionQueue.enqueue(Seq.fill(i)(() => Move(Maze.EAST)):_*)}

  @JSExport
  def down(i:Int) = activeMaze.foreach { m => m.actionQueue.enqueue(Seq.fill(i)(() => Move(Maze.SOUTH)):_*)}

  /* Used for the draw loop */
  val actions = mutable.Map.empty[String, () => Unit]
  dom.window.setInterval(() => {
    for { action <- actions.values } action.apply()
  }, 1000/60.0)

}
