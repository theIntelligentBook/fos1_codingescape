package lavamaze

import com.wbillingsley.veautiful.{<, VNode, ^}
import org.scalajs.dom
import org.scalajs.dom.Node
import org.scalajs.dom.raw.{Event, HTMLTextAreaElement}

class CodeEditor(rows:Int = 10, disabled:Boolean = false, private var text:String = "") extends VNode {

  def setText(s:String) = {
    text = s
    ota.foreach { ta => ta.value = s }
  }

  def getText = text

  var ota:Option[HTMLTextAreaElement] = None

  /**
    * The dom node that this is currently attached to.
    *
    * Note that if a VNode uses more than one real node to implement itself, parent.get.domNode.get might not be
    * the same as domNode.get.getParent(), even if the gets were to succeed.
    */
  override def domNode: Option[Node] = ota

  def onChange(evt:Event) = {
    for { ta <- ota } {
      text = ta.value
    }
  }

  /**
    * Called to perform an attach operation -- ie, create the real DOM node and put it into
    * domNode
    */
  override def attach(): Node = {
    val ta = dom.document.createElement("textarea") match {
      case x:HTMLTextAreaElement => x
    }

    ta.value = text
    ta.classList.add("pre")
    ta.classList.add("form-control")
    ta.addEventListener("change", onChange)

    ta.setAttribute("rows", rows.toString)
    if(disabled) {
      ta.setAttribute("disabled", "true")
    }

    ota = Some(ta)
    ta
  }

  /**
    * Called to perform a detach operation -- ie, anything necessary to clean up the DOM node,
    * and then remove it from domNode so we know it's gone.
    */
  override def detach(): Unit = {
    ota = None
  }
}
