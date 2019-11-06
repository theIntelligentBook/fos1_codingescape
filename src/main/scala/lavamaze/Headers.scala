package lavamaze

import com.wbillingsley.veautiful.<.DElAppliable
import com.wbillingsley.veautiful.{<, DElement, VNode, ^}
import org.scalajs.dom.raw.HTMLTextAreaElement

object Headers {

  def stageHeader(stage:Int, name:String):VNode = {
    <.div(^.cls := "stage-header",
      <.div(^.cls := "media",
        <.img(^.cls := "stageninja", ^.src := "assets/ninja.png"),
        <.span(^.cls := "stagenumber", stage.toString),
        <.div(^.cls := "media-body",
          <.div(<.span(^.cls := "stagename", name))
        )
      )
    )
  }

  def hgutter = <.div(^.cls := "row hgutter")

  def card(s:String)(ac: DElAppliable *) = <.div(^.cls := "card",
    <.div(^.cls := "card-body",
      <.div(^.cls := "card-title", <.h4(s)),
      <.div(ac:_*)
    )
  )

  def card(ac: DElAppliable *) = <.div(^.cls := "card",
    <.div(^.cls := "card-body",
      <.div(ac:_*)
    )
  )

  def cardText(ac: DElAppliable *) = <.div(^.cls := "card-text", <.div(ac:_*))

  def textColumn(ac: DElAppliable *) = <.div(^.cls := "text-column", <.div(ac:_*))

  def split(l:DElement)(r:DElement) = <.div(^.cls := "split2",
    <.div(l),
    <.div(r)
  )


  def codeEditor(get: => String, set: (String) => Unit, rows:Int = 10, disabled:Boolean = false) = <.div(
    <.textarea(^.cls := "form-control", ^.attr("rows") := rows.toString, ^.attr("disabled") := disabled.toString, ^.on("change") ==> { (evt) =>
      evt.target match {
        case ta: HTMLTextAreaElement => set(ta.value)
      }
    },
      get
    )
  )


  def challengeLayout(i:Int, n:String, readyNext: => Boolean)(c:VNode) = {
    <.div(^.cls := "challenge-wrapper",
      <.div(^.cls := "challenge-header", stageHeader(i, n)),
      <.div(^.cls := "challenge", c),
      <.div(^.cls := "countdown-box", Countdown.render),
      <.div(^.cls := "stage-progress", Stage.progressBlock),
      <.div(^.cls := "page-controls", Stage.pageControls(readyNext))
    )
  }


}
