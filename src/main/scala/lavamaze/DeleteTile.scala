package lavamaze

import com.wbillingsley.scatter.{HBox, Socket, Tile, TileForeignObject, TileSpace, TileText, VBox}
import com.wbillingsley.scatter.jstiles.{JSBlank, JSExpr}
import com.wbillingsley.veautiful.{<, ^}

class DeleteTile (tileSpace:TileSpace[JSExpr], cls:String = "btn btn-danger") extends Tile(tileSpace, false, false, cssClass = "delete") {

  var remembered:Option[Tile[JSExpr]] = None

  val socket = new Socket[JSExpr](this, onChange = { s =>
    // remember the tile for undo
    remembered = s.content

    // delete the content
    s.content = None
  })

  override def returnType: String = ""

  override val tileContent = {
    VBox(
      TileText("Trash"),
      socket
    )
  }

  override def toLanguage: JSExpr = JSBlank

  def undo():Unit = {
    println("Undo")
  }
}