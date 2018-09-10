import info.tweaked.model.plan.Prerequisite

import scala.scalajs.js
import scala.util.Try
import scala.util.parsing.combinator._

object RequirementParsng {

  sealed trait ParseReq
  case class UnitCode(s:String) extends ParseReq
  case class Yes(units:Seq[UnitCode]) extends ParseReq
  case class Cp(cp:Int) extends ParseReq
  case class Or(t1: ParseReq, t2: ParseReq) extends ParseReq
  case class And(t1: ParseReq, t2: ParseReq) extends ParseReq
  case class NFrom(n:Int, units:Seq[UnitCode]) extends ParseReq

  object ReqParser extends RegexParsers with PackratParsers {

    lazy val unitCode: PackratParser[UnitCode] = """([A-Za-z]+[0-9]+)""".r ^^ { x => UnitCode(x.toString) }

    //lazy val number: PackratParser[Int] = """([0-9]+)""".r ^^ { _.toInt }

    lazy val cp: PackratParser[Cp] = """([0-9]+)cp""".r ^^ { x => Cp(x.stripSuffix("cp").toInt) }

    lazy val unitCodes: PackratParser[Seq[UnitCode]] = unitCode ~ rep("," ~> unitCode) ^^ {
      case uc ~ list => List(uc) ++ list
    }

    //def yes: Parser[Yes] = unitCodes ^^ { codes => Yes(codes) }

    lazy val nFrom: PackratParser[NFrom] = cp ~ "from" ~ unitCodes ^^ { case Cp(n) ~ _ ~ uc => NFrom(n, uc) }

    lazy val simpleTerm: PackratParser[_ <: ParseReq] = and | or | nFrom | cp | unitCode  ^^ { x => x}

    lazy val parened: PackratParser[_ <: ParseReq] = "(" ~> term <~ ")" ^^ { x => x }

    lazy val term: PackratParser[_ <: ParseReq] =  parened ||| simpleTerm ^^ { x => x }

    lazy val or: PackratParser[_ <: ParseReq] = (term <~ "or") ~ term ^^ { case lhs ~ rhs => Or(lhs, rhs) }

    lazy val and: PackratParser[_ <: ParseReq] = (term <~ "and") ~ term ^^ { case lhs ~ rhs => And(lhs, rhs) }

    lazy val all: PackratParser[_ <: ParseReq] = phrase(term)

    def parseReq(s:String): ReqParser.ParseResult[ParseReq] = parseAll(term, s)
  }

  def parse(s:String) = ReqParser.parseReq(s)

  parse("(COSC110 or COSC101 or SCI210) and 72cp from COSC101, COSC120")


}
