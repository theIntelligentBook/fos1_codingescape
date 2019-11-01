package lavamaze

import org.scalajs.dom

import scala.collection.mutable
import scala.scalajs.js.annotation.{JSExport, _}

@JSExportTopLevel("Commands")
object Commands {

  @JSExport
  def ping() = {
    println("ping")
  }

  var activeMaze:Option[Maze] = None

  @JSExport
  def move(d:Int) = activeMaze.foreach { m => m.actionQueue.enqueue(() => m.Ninja.move(d))}

  @JSExport
  def right(i:Int) = activeMaze.foreach { m => m.actionQueue.enqueueAll(Seq.fill(i)(() => m.Ninja.move(Maze.EAST)))}

  @JSExport
  def down(i:Int) = activeMaze.foreach { m => m.actionQueue.enqueueAll(Seq.fill(i)(() => m.Ninja.move(Maze.SOUTH)))}

  @JSExport
  def left(i:Int) = activeMaze.foreach { m => m.actionQueue.enqueueAll(Seq.fill(i)(() => m.Ninja.move(Maze.WEST)))}

  @JSExport
  def up(i:Int) = activeMaze.foreach { m => m.actionQueue.enqueueAll(Seq.fill(i)(() => m.Ninja.move(Maze.NORTH)))}

  @JSExport
  def look(d:Int):Int = activeMaze match {
    case Some(m) => {
      val move = Move(d)
      m.goalDistance(m.Ninja.x + move.dx, m.Ninja.y + move.dy)
    }
    case _ => 99
  }

  @JSExport
  def canGoRight() = activeMaze match {
    case Some(m) => m.Ninja.canMove(Maze.EAST)
    case _ => false
  }

  @JSExport
  def canGoDown() = activeMaze match {
    case Some(m) => m.Ninja.canMove(Maze.SOUTH)
    case _ => false
  }

  /* Used for the draw loop */
  val actions = mutable.Map.empty[String, () => Unit]
  dom.window.setInterval(() => {
    for { action <- actions.values } action.apply()
  }, 1000/60.0)

}
