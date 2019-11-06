package lavamaze

import com.wbillingsley.veautiful.logging.Logger
import org.scalajs.dom

import scala.collection.mutable
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExport, _}

@JSExportTopLevel("Commands")
object Commands {

  val logger:Logger = Logger.getLogger(Commands.getClass)

  @JSExport
  def ping() = {
    println("ping")
  }

  /* Used for the draw loop */
  val actions = mutable.Map.empty[String, () => Unit]
  dom.window.setInterval(() => {
    for { action <- actions.values } action.apply()
  }, 1000/60.0)

  /**
    * Dynamically composes a function, calling it with the given context.
    *
    * @param thisVal
    * @param args
    * @param code
    * @return
    */
  @JSExport
  def evalWithContext(thisVal: js.Any = new js.Object(), args:Seq[(String, js.Any)] = Seq.empty, code:String = ""):js.Dynamic = {
    val argNames = args.map(_._1)
    val func = new js.Function(argNames :+ code :_*)
    val argVals = args.map(_._2)
    func.call(thisVal, argVals:_*)
  }


}
