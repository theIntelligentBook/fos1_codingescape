package info.tweaked.model.hjson

import com.wbillingsley.handy.Ref
import Ref._
import com.wbillingsley.veautiful.courseexplore.CourseLoader
import info.tweaked.model.plan.Prerequisite
import info.tweaked.model.unit.TeachingUnit

import scala.scalajs.js
import scala.util.Try
import scala.util.parsing.combinator._

object RequirementParsing {

  sealed trait ParseReq
  case class UnitCode(s:String) extends ParseReq
  case class All(units:Seq[UnitCode]) extends ParseReq
  case class Cp(cp:Int) extends ParseReq
  case class Or(t1: ParseReq, t2: ParseReq) extends ParseReq
  case class And(t1: ParseReq, t2: ParseReq) extends ParseReq
  case class NFrom(n:Int, units:Seq[UnitCode]) extends ParseReq

  case class RangeFrom(minCip:Int, maxCp:Int, units:Seq[UnitCode]) extends ParseReq

  object ReqParser extends RegexParsers with PackratParsers {

    lazy val unitCode: PackratParser[UnitCode] = """([A-Za-z]+[0-9]+)""".r ^^ { x => UnitCode(x.toString) }

    //lazy val number: PackratParser[Int] = """([0-9]+)""".r ^^ { _.toInt }

    lazy val cp: PackratParser[Cp] = """([0-9]+)cp""".r ^^ { x => Cp(x.stripSuffix("cp").toInt) }

    lazy val unitCodes: PackratParser[Seq[UnitCode]] = unitCode ~ rep("," ~> unitCode) ^^ {
      case uc ~ list => List(uc) ++ list
    }

    lazy val all:PackratParser[All] = unitCodes ^^ All.apply

    //def yes: Parser[Yes] = unitCodes ^^ { codes => Yes(codes) }

    lazy val nFrom: PackratParser[NFrom] = cp ~ "from" ~ unitCodes ^^ { case Cp(n) ~ _ ~ uc => NFrom(n, uc) }

    lazy val rangeFrom: PackratParser[RangeFrom] = cp ~ "to" ~ cp ~ "from" ~ unitCodes ^^ {
      case Cp(min) ~ _ ~ Cp(max) ~ _ ~ uc => RangeFrom(min, max, uc)
    }

    lazy val simpleTerm: PackratParser[_ <: ParseReq] = and | or | nFrom | rangeFrom | cp | all | unitCode ^^ { x => x}

    lazy val parened: PackratParser[_ <: ParseReq] = "(" ~> term <~ ")" ^^ { x => x }

    lazy val term: PackratParser[_ <: ParseReq] =  parened ||| simpleTerm ^^ { x => x }

    lazy val or: PackratParser[_ <: ParseReq] = (term <~ "or") ~ term ^^ { case lhs ~ rhs => Or(lhs, rhs) }

    lazy val and: PackratParser[_ <: ParseReq] = (term <~ "and") ~ term ^^ { case lhs ~ rhs => And(lhs, rhs) }

    def parseReq(s:String): ReqParser.ParseResult[ParseReq] = parseAll(term, s)
  }


  def parseRequirement(s:String) = Try {
    ReqParser.parseReq(s).get
  }

  def translate(pr:ParseReq):Ref[Prerequisite] = {

    pr match {
      case UnitCode(s) => for {
        unit <- CourseLoader.loadUnit(s)
      } yield Prerequisite.Contains(Seq(unit):_*)

      case All(units) => for {
        units <- units.map(_.s).toRefMany.flatMapOne[TeachingUnit](CourseLoader.loadUnit).collect
      } yield Prerequisite.Contains(units:_*)

      case Or(t1, t2) => for {
        r1 <- translate(t1)
        r2 <- translate(t2)
      } yield Prerequisite.Or(Seq(r1, r2))

      case And(t1, t2) => for {
        r1 <- translate(t1)
        r2 <- translate(t2)
      } yield Prerequisite.And(Seq(r1, r2))

      case Cp(n) => Prerequisite.MinCP(n).itself

      case NFrom(n, codes) => for {
        units <- codes.map(_.s).toRefMany.flatMapOne[TeachingUnit](CourseLoader.loadUnit).collect
      } yield Prerequisite.NumFrom(n, units:_*)

      case RangeFrom(min, max, codes) => for {
        units <- codes.map(_.s).toRefMany.flatMapOne[TeachingUnit](CourseLoader.loadUnit).collect
      } yield Prerequisite.RangeFrom(min, max, units:_*)
    }


  }


}
