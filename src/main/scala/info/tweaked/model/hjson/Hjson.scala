package info.tweaked.model.hjson

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("hjson", JSImport.Namespace)
@js.native
object Hjson extends js.Object {
  /*~ If this module has methods, declare them as functions like so.
 */
  def parse(text: String, options: js.Dynamic = js.native): js.Dynamic = js.native
  def stringify(value: js.Any, options: js.Dynamic = js.native): String = js.native
}
