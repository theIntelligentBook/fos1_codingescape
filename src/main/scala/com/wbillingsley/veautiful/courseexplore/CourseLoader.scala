package com.wbillingsley.veautiful.courseexplore

import org.scalajs.dom
import dom.ext.Ajax

import scala.scalajs.js
import scala.concurrent.ExecutionContext.Implicits.global
import info.tweaked.model.hjson.{CourseHjson, Hjson, TUHjson, TermHjson}
import com.wbillingsley.handy._
import Ref._
import info.tweaked.model.plan.Course
import info.tweaked.model.unit.{TeachingUnit, Term}
import units.AllUnits

import scala.collection.mutable
import scala.concurrent.Future

object CourseLoader {

  /** caches teaching units. Note, Scala.js is single-threaded so this doesn't need to be concurrent. */
  val unitCache = mutable.Map.empty[String, Ref[TeachingUnit]]

  /** caches teaching units. Note, Scala.js is single-threaded so this doesn't need to be concurrent. */
  val courseCache = mutable.Map.empty[String, Ref[Course]]


  for { u <- AllUnits.units } unitCache.put(u.code, Future.successful(u).toRef )

  /** loads the terms and puts them in an accessible map */
  lazy val termsMap:Ref[Map[String, Term]] = {
    val terms = (for {
      req <- Ajax.get("assets/coursedata/terms.hjson", responseType = "text").toRef
      arr = Hjson.parse(req.responseText).asInstanceOf[js.Array[js.Dynamic]]
      js <- RefTraversableOnce(arr.toTraversable)
      parsed <- TermHjson.fromObj(js).toRef
    } yield parsed) recoverManyWith {
      case x:Throwable => x.printStackTrace(); RefManyFailed(x)
    }

    terms.collect.map(_.map(t => t.shortName -> t).toMap)
  }

  /** A map from trimester short name to a sequence of unit codes */
  lazy val offeringsMap:Ref[Map[String, Seq[String]]] = {
    import js.JSConverters._

    for {
      req <- Ajax.get("assets/coursedata/offerings.hjson", responseType = "text").toRef
      jsonDict = Hjson.parse(req.responseText).asInstanceOf[js.Dictionary[js.Array[String]]]
      scalified = jsonDict.map { case (key, v) => key -> v.toSeq }
      m:mutable.Map[String, Seq[String]] = scalified
    } yield m.toMap
  }

  def loadCourseList() = {
    val doc = Ajax.get("courses.hjson").map(xhr => Hjson.parse(xhr.responseText))
    doc
  }

  def loadUnit(code:String):Ref[TeachingUnit] = {
    unitCache.getOrElse(code, {
      val v = (for {
        req <- Ajax.get(s"assets/coursedata/units/$code.hjson", responseType = "text").toRef
        json = Hjson.parse(req.responseText).asInstanceOf[js.Dictionary[js.Dynamic]]
        u <- TUHjson.fromJson(json)
      } yield u) recoverWith {
        case x:Throwable => x.printStackTrace(); RefFailed(x)
      }

      unitCache.put(code, v)
      v
    })
  }

  def loadManyUnits(codes:Seq[String]):RefMany[TeachingUnit] = {
    for {
      code <- codes.toRefMany
      u <- loadUnit(code)
    } yield u
  }


  def loadCourse(code:String):Ref[Course] = {
    courseCache.getOrElse(code, {
      val v = (for {
        req <- Ajax.get(s"assets/coursedata/course/$code.hjson", responseType = "text").toRef
        json = Hjson.parse(req.responseText).asInstanceOf[js.Dictionary[js.Dynamic]]
        u <- CourseHjson.courseFromJson(json)
      } yield u) recoverWith {
        case x:Throwable => x.printStackTrace(); RefFailed(x)
      }

      courseCache.put(code, v)
      v
    })
  }


}
