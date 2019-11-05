package lavamaze

import com.wbillingsley.veautiful.{<, DiffComponent, ^}
import org.scalajs.dom

import scala.scalajs.js

object Countdown {

  var started:Option[Long] = None

  var timerId:Option[Int] = None

  def start() = {
    if (timerId.isEmpty) {
      started = Some(js.Date.now.toLong)
      timerId = Some(dom.window.setInterval(() => {
        Routing.rerender()
      }, 1000))
    }
  }

  def stop(): Unit = {
    timerId.foreach(i => dom.window.clearInterval(i))
    timerId = None
  }

  val initial = 45 * 60

  def escaped = Stage8.reachedGoal

  def remaining:(Long, Long) = started.map { s =>
    val elapsed = (js.Date.now.toLong - s) / 1000
    val remaining = initial - elapsed

    (remaining / 60, remaining % 60)
  } getOrElse( (initial / 60, initial % 60) )

  def render = {
    val (h, m) = remaining

    if (escaped) {
      <.div(^.cls := "countdown escaped",
        <.div("ESCAPED!")
      )
    } else {
      if (h >= 0 && m >= 0) {
        <.div(^.cls := "countdown",
          <("div", s"$m")(
            f"$h%02d:$m%02d"
          )
        )
      } else {
        <.div(^.cls := "countdown timeout",
          <.div("TIME!")
        )
      }
    }

  }

}
