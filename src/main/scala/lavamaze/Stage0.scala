package lavamaze

import com.wbillingsley.veautiful.{<, ^}
import org.scalajs.dom
import org.scalajs.dom.raw.{HTMLInputElement, HTMLTextAreaElement}

import scala.concurrent.Future
import scala.scalajs.js

import scala.concurrent.ExecutionContext.Implicits.global

object Stage0 {
  import Headers._

  var reachedGoal = false

  var team:String = ""
  var names:String = ""
  var school:String = ""
  var user:String = ""

  def setUser(e:dom.Event) = e.target match {
    case u:HTMLInputElement => user = u.value
  }

  def setNames(e:dom.Event) = e.target match {
    case u:HTMLTextAreaElement => names = u.value
  }
  def setSchool(e:dom.Event) = e.target match {
    case u:HTMLInputElement => school = u.value
  }
  def setTeam(e:dom.Event) = e.target match {
    case u:HTMLInputElement => team = u.value
  }

  def csvify(s:String) = {
    "\"" + s.replace("\"", "\"\"") + "\""
  }

  def submit() = {
    import dom.ext.Ajax

    val dataSeq = Seq(
      new js.Date().toISOString(),
      user,
      names,
      school
    )

    if (!dataSeq.exists(_.trim.isEmpty)) {
      val data = dataSeq.map(csvify).mkString(",")

      val url = "/lavamaze/students"
      Ajax.post(url, data)
        .recoverWith {
          case x: Throwable => x.printStackTrace(); Future.failed(x)
        }
        .foreach { _ =>
          reachedGoal = true
          Routing.afterAttach()
        }
    }

  }


  def node() = {

    println(s"Rendering stage 0")

    <.div(
      stageHeader(0, "It's a lavaly day today"),
      hgutter,

      split(
        card("Welcome to the maze...")(
          cardText(
            <.p("First, we need to know who you are for security reasons"),
            <("form")(
              <.div(^.cls := "form-group",
                <("label")("Username *"),
                <("input")(^.attr("type") := "text", ^.cls := "form-control", ^.attr("required") := "required", ^.on("input") ==> setUser, ^.attr("placeholder") := "The one you logged in with")
              ),
              <.div(^.cls := "form-group",
                <("label")("Names *"),
                <("textarea")(^.attr("rows") := "2", ^.cls := "form-control", ^.attr("required") := "required", ^.on("input") ==> setNames, ^.attr("placeholder") := "Your real names")
              ),
              <.div(^.cls := "form-group",
                <("label")("School *"),
                <("input")(^.attr("type") := "text", ^.cls := "form-control", ^.attr("required") := "required", ^.on("input") ==> setSchool, ^.attr("placeholder") := "Your school")
              ),
              <.div(^.cls := "form-group",
                <("label")("Team name"),
                  <("input")(^.attr("type") := "text", ^.cls := "form-control", ^.on("input") ==> setTeam, ^.attr("placeholder") := "eg, Jumping jellybeans")
              )
            ),
            <.button(^.cls := "btn btn-primary", ^.onClick --> submit, "Save")
          )
        )
      )(
        <.div(
          if (reachedGoal) {
            <.div(
              <.p(^.cls := "congrats", s"Welcome, $team"),
              <.p("Time to get coding..."),
              <("div", "stage0")(^.cls := "btn-group",
                <.button(^.cls := "btn btn-outline-primary", ^.onClick --> Routing.routeTo(Routing.Stage1R), "Stage 1")
              )
            )
          } else <("div", "stage0")(^.cls := "btn-group",
            <.button(^.cls := "btn btn-outline-light", ^.onClick --> next, "Stage 1")
          )
        )
      )

    )

  }

  def next(): Unit = {
    Routing.routeTo(Routing.Stage2R)
  }

}
