package lavamaze

import com.wbillingsley.veautiful.VNode
import example.ScalaJSExample
import lavamaze.Maze.{Lava, Tile}
import org.scalajs.dom
import org.scalajs.dom.{Node, html}
import org.scalajs.dom.raw.HTMLCanvasElement

import scala.collection.mutable


object Maze {

  val oneTile = 64
  val halfTile: Int = oneTile / 2
  val quarterTile: Int = oneTile / 4
  val eighthTile: Int = oneTile / 8

  val NORTH = 3
  val SOUTH = 1
  val EAST = 0
  val WEST = 2

  val lavaStyle = "rgb(100,0,0)"
  val lavaBlobStyle = "rgb(115,0,0)"
  val lavaBlobStroke = "rgb(110,0,0)"

  /*
  val lavaStyle = "rgb(90,0,0)"
  val lavaBlobStyle = "rgb(110,0,0)"
  val lavaBlobStroke = "rgb(100,0,0)"
*/

  val floorFill = "rgb(20,20,20)"
  val floorStroke = "rgb(120,120,120)"

  val goalFill = "rgb(20,20,80)"
  val goalStroke = "rgb(120,120,120)"

  val maskFill = "#7D5177"
  val maskStroke = "rgb(70,70,100)"
  val maskOpeningFill = "rgb(220, 200, 200)"
  val maskOpeningStroke ="rgb(70, 70, 100)"

  val ninjaHighlight = "rgba(0, 0, 255, 0.5)"

  val guardHighlight = "rgba(255, 0, 0, 0.5)"
  val guardHighlightStroke = "rgb(95, 0, 0)"
  val guardFill = "rgb(200,50,50)"
  val guardStroke = "rgb(200,70,70)"


  sealed trait Tile {
    def isPassable = true
  }
  case object Lava extends Tile {
    override def isPassable = false
  }
  case object Floor extends Tile
  case object Goal extends Tile

}

class Maze(name:String, val w:Int = 8, val h:Int = 6, defaultAction: () => Action = () => Idle, onGoal:() => Unit = { () => }) extends VNode {

  type Ctx2D = dom.CanvasRenderingContext2D

  val numPaths = 4
  val numMonsters = 2

  val cells:Seq[Array[Tile]] = for { y <- 0 until h } yield Array.fill[Tile](w)(Lava)
  cells(h - 1)(w - 1) = Maze.Goal

  val goalDist:Seq[Array[Int]] = for { y <- 0 until h } yield Array.fill[Int](w)(99)

  var running = true
  var monstersActive = true
  var showRoutes = false
  var routesConsiderMonsters = false

  var oc:Option[HTMLCanvasElement] = None

  val lavaBlobs = Array.fill(50)(LavaBlob(w * Maze.oneTile, h * Maze.oneTile))

  var blobGuards:Array[Blob] = Array.empty
  def createBlobs(i:Int): Unit = {
    blobGuards = Array.fill(i)(new Blob(w, h))
  }

  var actionQueue = mutable.Queue.empty[() => Action]

  def logFinished(): Unit = {
    println("Goal reached")
  }

  /**
    * The dom node that this is currently attached to.
    *
    * Note that if a VNode uses more than one real node to implement itself, parent.get.domNode.get might not be
    * the same as domNode.get.getParent(), even if the gets were to succeed.
    */
  override def domNode: Option[Node] = oc

  /**
    * Called to perform an attach operation -- ie, create the real DOM node and put it into
    * domNode
    */
  override def attach(): Node = {

    val c = dom.document.createElement("canvas") match {
      case cc:HTMLCanvasElement => cc
    }

    c.classList.add("maze")
    c.width = w * Maze.oneTile
    c.height = h * Maze.oneTile

    oc = Some(c)
    Commands.actions(name) = paint
    c
  }

  /**
    * Called to perform a detach operation -- ie, anything necessary to clean up the DOM node,
    * and then remove it from domNode so we know it's gone.
    */
  override def detach(): Unit = {
    Commands.actions.remove(name)
    oc = None
  }

  def makeSpoilerPath() = {
    makePath(2)
    cells(h-2)(w-1) = Maze.Floor
    cells(h-2)(w-2) = Maze.Floor
    cells(h-1)(w-2) = Maze.Floor
    for { x <- 0 until w } cells(h/2 - 1)(x) = Maze.Floor
    for { y <- 0 until h } cells(y)(w/2 - 1) = Maze.Floor
  }


  def makePath(offset:Int = 1) = {
    val ww = w - offset
    val hh = h - offset
    var x = 0
    var y = 0

    def randDist(max:Int) = Math.max(1, Math.random() * max).toInt

    var along:Boolean = Math.random() > 0.5
    while (x < ww || y < hh) {
      if (!along && y < hh) {
        val run = randDist(hh - y)
        for { i <- 0 until run } {
          //println(s" ${y+i} $x")
          cells(y + i)(x) = Maze.Floor
        }
        y = y + run
      } else if (x < ww) {
        val run = randDist(ww - x)
        for { i <- 0 until run } {
          //println(s" ${y} ${x+i}")
          cells(y)(x + i) = Maze.Floor
        }
        x = x + run
      }
      along = !along
    }
  }

  def goalDistance(x:Int, y:Int):Int = {
    if (x >= 0 && x < w && y >= 0 && y < h) {
      goalDist(y)(x)
    } else 99
  }

  def resetGoalDist(): Unit = {
    for { x <- 0 until w; y <- 0 until h} goalDist(y)(x) = 99
  }

  def monstersAt(x:Int, y:Int) = blobGuards.exists { b => b.x == x && b.y == y }

  def check(x:Int, y:Int, dist:Int): Unit = {
    goalDist(y)(x) = dist
    for { d <- 0 until 4 } {
      val move = Move(d)
      val xx = x + move.dx
      val yy = y + move.dy
      if (
        xx >= 0 && xx < w && yy >= 0 && yy < h && cells(yy)(xx).isPassable && goalDist(yy)(xx) > dist + 1 &&
        !(routesConsiderMonsters && monstersAt(xx, yy))
      ) {
        check(xx, yy, dist + 1)
      }
    }
  }

  def drawWidth = Maze.oneTile * w
  def drawHeight = Maze.oneTile * h

  def paint(): Unit = for { c <- oc } {

    val ctx = c.getContext("2d").asInstanceOf[Ctx2D]

    // Draw the lava
    ctx.fillStyle = Maze.lavaStyle
    ctx.fillRect(0, 0, drawWidth, drawHeight)
    for { blob <- lavaBlobs } blob.draw(ctx)


    def paintFloor(x:Int, y:Int): Unit = {
      ctx.fillStyle = Maze.floorFill
      ctx.strokeStyle = Maze.floorStroke
      ctx.fillRect(x * Maze.oneTile, y * Maze.oneTile, Maze.oneTile, Maze.oneTile)
      ctx.strokeRect(x * Maze.oneTile, y * Maze.oneTile, Maze.oneTile, Maze.oneTile)

      if (showRoutes) {
        ctx.fillStyle = Maze.goalStroke
        ctx.font = "20px sans-serif"

        ctx.fillText(goalDist(y)(x).toString, Maze.oneTile * x + 4, Maze.oneTile * y + 40, 56)
      }
    }

    def paintGoal(x:Int, y:Int): Unit = {
      ctx.fillStyle = Maze.goalFill
      ctx.strokeStyle =
      ctx.fillRect(x * Maze.oneTile, y * Maze.oneTile, Maze.oneTile, Maze.oneTile)

      ctx.fillStyle = Maze.goalStroke
      ctx.font = "20px sans-serif"
      ctx.fillText("GOAL", Maze.oneTile * x + 4, Maze.oneTile * y + 40, 56)
      ctx.strokeRect(x * Maze.oneTile, y * Maze.oneTile, Maze.oneTile, Maze.oneTile)
    }


    for {
      x <- 0 until w
      y <- 0 until h
    } {
      cells(y)(x) match {
        case Maze.Floor => paintFloor(x, y)
        case Maze.Goal => paintGoal(x, y)
        case _ => {}
      }
    }

    Ninja.paint(ctx)

    for { b <- blobGuards } b.paint(ctx)

  }


  object Ninja {
    var x = 0
    var y = 0

    var action:Action = Idle
    var alive = true

    def paint(ctx:dom.CanvasRenderingContext2D): Unit = {
      if (monstersAt(x, y)) {
        alive = false
      }

      if (action.done) {

        resetGoalDist()
        check(w-1, h-1, 0)

        x = x + action.dx
        y = y + action.dy

        if (cells(y)(x) == Maze.Goal && action != Idle) {
          onGoal()
          action = Idle
        } else {
          action = if (actionQueue.nonEmpty) {
            actionQueue.dequeue().apply()
          } else defaultAction()
        }
      }

      def tToP(i:Int) = i * Maze.oneTile
      val xx = tToP(x) + action.drawX
      val yy = tToP(y) + action.drawY

      ctx.fillStyle = Maze.ninjaHighlight
      ctx.fillRect(tToP(x), tToP(y), Maze.oneTile, Maze.oneTile)

      if (alive) {
        // head
        ctx.fillStyle = Maze.maskFill
        ctx.strokeStyle = Maze.maskStroke
        ctx.beginPath()
        ctx.moveTo(xx + Maze.halfTile, yy + Maze.halfTile)
        ctx.arc(xx + Maze.halfTile, yy + Maze.halfTile, Maze.quarterTile, 0, 2 * Math.PI)
        ctx.fill()

        // mask opening
        ctx.fillStyle = Maze.maskOpeningFill
        ctx.strokeStyle = Maze.maskOpeningStroke
        ctx.beginPath()
        ctx.moveTo(xx + Maze.halfTile, yy + Maze.halfTile)
        ctx.arc(xx + Maze.halfTile, yy + Maze.quarterTile + Maze.eighthTile, Maze.quarterTile, 0.5, Math.PI - 0.5)
        ctx.fill()

        // eyes
        ctx.fillStyle = "rgb(20,20,20)"
        ctx.beginPath()
        ctx.moveTo(xx + Maze.halfTile - Maze.eighthTile, yy + Maze.halfTile + 2)
        ctx.arc(xx + Maze.halfTile - Maze.eighthTile, yy + Maze.halfTile + 2, 2, 0, 2 * Math.PI)
        ctx.fill()
        ctx.beginPath()
        ctx.moveTo(xx + Maze.halfTile + Maze.eighthTile, yy + Maze.halfTile + 2)
        ctx.arc(xx + Maze.halfTile + Maze.eighthTile, yy + Maze.halfTile + 2, 2, 0, 2 * Math.PI)
        ctx.fill()
      } else {
        ctx.fillStyle = Maze.guardFill
        ctx.fillRect(xx + Maze.halfTile - 5, yy + Maze.halfTile - 25, 10, 50)
        ctx.fillRect(xx + Maze.halfTile - 15, yy + Maze.halfTile - 15, 30, 10)
      }

    }

    def canMove(d:Int):Boolean = alive && action.done && (d match {
      case Maze.EAST => x + 1 < w && cells(y)(x + 1).isPassable
      case Maze.WEST => x > 0 && cells(y)(x - 1).isPassable
      case Maze.SOUTH => y + 1 < h && cells(y + 1)(x).isPassable
      case Maze.NORTH => y > 0 && cells(y - 1)(x).isPassable
      case _ => false
    })

    def move(d:Int):Action = {
      if (action.done && canMove(d)) {
        Move(d)
      } else {
        Idle
      }
    }

    def moveNow(d:Int): Unit = {
      if (action.done && canMove(d)) {
        action = Move(d)
      }
    }

  }

  class Blob(w:Int, h:Int) {

    var x = 0
    var y = 0
    var action:Action = Idle

    def findRandomTile(): Unit = {
      do {
        x = (Math.random() * w).toInt
        y = (Math.random() * h).toInt
      } while (x == 0 || y == 0 || !cells(y)(x).isPassable)
    }

    findRandomTile()

    def randomMove():Action = {
      val avail = for { d <- 0 until 4 if {
        val m = Move(d)
        val xx = x + m.dx
        val yy = y + m.dy
        xx >= 0 && yy >= 0 && xx < w && yy < h && cells(yy)(xx).isPassable
      }} yield d

      Move(avail((Math.random() * avail.length).toInt))
    }

    def paint(ctx:dom.CanvasRenderingContext2D): Unit = {
      val time = System.currentTimeMillis()
      val theta = time * 6.3 / 1000

      if (action.done) {
        x = x + action.dx
        y = y + action.dy
        action = randomMove()
      }

      val blobWidth = (Maze.quarterTile + Maze.eighthTile) + (Maze.eighthTile * Math.sin(theta))
      val blobHeight = (Maze.quarterTile + Maze.eighthTile) + (Maze.eighthTile * Math.cos(theta))

      def tToP(i:Int) = i * Maze.oneTile

      // highlight active square
      ctx.fillStyle = Maze.guardHighlight
      ctx.strokeStyle = Maze.guardHighlightStroke
      ctx.fillRect(tToP(x), tToP(y), Maze.oneTile, Maze.oneTile)

      // draw the blob
      ctx.fillStyle = Maze.guardFill
      ctx.strokeStyle = Maze.guardStroke
      val xx = tToP(x) + action.drawX
      val yy = tToP(y) + action.drawY

      ctx.save()
      ctx.beginPath()
      ctx.translate(xx + Maze.halfTile, yy + Maze.halfTile)
      ctx.scale(blobWidth, blobHeight)
      ctx.arc(0, 0, 0.5, 0, 2 * Math.PI)
      ctx.restore()
      ctx.fill()
      ctx.stroke()
    }


  }

}

case class LavaBlob(w:Double, h:Double, var x:Double = 0, var y:Double = 0, var r:Double = 0) {

  var start = System.currentTimeMillis()

  def reset(): Unit = {
    x = Math.random() * w
    y = Math.random() * h
    r = Math.random() * 50
    start = System.currentTimeMillis()
  }

  def draw(ctx:dom.CanvasRenderingContext2D): Unit = {


    val t = System.currentTimeMillis() - start

    if (r < 1) {
      reset()
    } else {
      r = r * (1 - t/20000.0)
    }
    x = x + 0.1
    y = y + 0.1

    ctx.strokeStyle = Maze.lavaBlobStroke
    ctx.fillStyle = Maze.lavaBlobStyle
    ctx.beginPath()
    ctx.moveTo(x, y)
    ctx.arc(x, y, r, 0, Math.PI * 2)
    ctx.stroke()
    ctx.fill()

  }

}

sealed trait Action {
  def drawX = 0.0
  def drawY = 0.0

  def dx = 0
  def dy = 0

  def done = true
}
case object Idle extends Action
case class Move(d:Int) extends Action {
  val start = System.currentTimeMillis()
  val duration = 750.0

  def dt = (System.currentTimeMillis() - start)/duration

  override def done = dt >= 1.0

  override def drawX = d match {
    case Maze.EAST => dt * Maze.oneTile
    case Maze.WEST => -dt * Maze.oneTile
    case _ => 0.0
  }

  override def drawY = d match {
    case Maze.SOUTH => dt * Maze.oneTile
    case Maze.NORTH => -dt * Maze.oneTile
    case _ => 0.0
  }

  override def dx = d match {
    case Maze.EAST => 1
    case Maze.WEST => -1
    case _ => 0
  }

  override def dy = d match {
    case Maze.SOUTH => 1
    case Maze.NORTH => -1
    case _ => 0
  }

}


