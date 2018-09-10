package info.tweaked.model.hjson

import info.tweaked.model.hjson.TermHjson.DateParser
import info.tweaked.model.time.{DayInYear, Month}
import info.tweaked.model.unit.Term

import scala.util.parsing.combinator._
import scala.scalajs.js
import scala.util.Try

object TermHjson {

  object DateParser extends RegexParsers {
    def number: Parser[Int] = """(0|[1-9]\d*)""".r ^^ { _.toInt }
    def month: Parser[Month] = """([a-z]+)""".r ^^ { name => Month.fromName(name) }
    def date: Parser[DayInYear] = number ~ month ~ number ^^ { case d ~ m ~ y => m.apply(d)(y) }

    def parseDate(dateStr:String): DateParser.ParseResult[DayInYear] = this.parse(date, dateStr)
  }

  def fromObj(t:js.Dynamic) = Try {

    val start = t.start.asInstanceOf[String]
    println(s"Start is $start")


    Term(
      name = t.name.asInstanceOf[String],
      shortName = t.shortName.asInstanceOf[String],
      start = DateParser.parseDate(t.start.asInstanceOf[String]).get,
      end = DateParser.parseDate(t.end.asInstanceOf[String]).get
    )
  }

}
