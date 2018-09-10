package info.tweaked.model.hjson

import com.wbillingsley.handy._
import Ref._
import info.tweaked.model.plan.Prerequisite
import info.tweaked.model.unit.TeachingUnit

import scala.scalajs.js
import scala.util.{Success, Try}

object TUHjson {

  def fromJson(obj: js.Dictionary[js.Dynamic]):Ref[TeachingUnit] = {

    val req:RefOpt[Prerequisite] = for {
      text <- RefOpt(obj.get("prerequisite").map(_.toString))
      parsed <- RequirementParsing.parseRequirement(text).toRef
      r <- RequirementParsing.translate(parsed)
    } yield r

    for {
      r <- req.option
    } yield {
      TeachingUnit(
        code = obj("code").asInstanceOf[String],
        name = obj.get("name").asInstanceOf[Option[String]],
        description = obj.get("description").map(_.asInstanceOf[js.Array[String]].toSeq).getOrElse(Seq.empty),
        prerequisite = r.getOrElse(Prerequisite.Yes)

      )
    }

  }


}
