package info.tweaked.model.hjson

import com.wbillingsley.handy.{Ref, RefOpt}
import Ref._
import com.wbillingsley.veautiful.courseexplore.CourseLoader
import info.tweaked.model.plan.{Course, NamedRule, Prerequisite}

import scala.scalajs.js

object CourseHjson {

  def namedRuleFromJson(obj:js.Dictionary[js.Dynamic]):Ref[NamedRule] = {
    def rule = for {
      text <- RefOpt(obj.get("rule"))
      parsed <- RequirementParsing.parseRequirement(text.asInstanceOf[String]).toRef
      rule <- RequirementParsing.translate(parsed)
    } yield rule

    for {
      r <- rule.option
    } yield {
      NamedRule(
        name=obj("name").asInstanceOf[String],
        rule=r.getOrElse(Prerequisite.Yes))
    }
  }

  def courseFromJson(obj:js.Dictionary[js.Dynamic]):Ref[Course] = {
    val rulesText = obj("rules").asInstanceOf[js.Array[js.Dictionary[js.Dynamic]]]

    for {
      rules <- rulesText.toSeq.toRefMany.flatMapOne(namedRuleFromJson).collect
    } yield {
      Course(
        short = obj("short").asInstanceOf[String],
        name = obj("name").asInstanceOf[String],
        rules = rules
      )
    }
  }




}
