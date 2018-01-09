package units

import info.tweaked.model.unit._
import units.CBOK._

object COSC250 extends HasUnit {

  val it = new TeachingUnit(
    code = "COSC250",

    name = Some("Programming Paradigms"),

    description = Seq(
      """
        |A programming language gives programmers a way of modelling and expressing a computer program. Different programming languages are based on campus different ideas about how one thinks about computer programs.
        |""".stripMargin,
      """
        |This unit introduces students to the paradigms behind advanced programming languages that are used by many of the world's leading software companies. The course is taught predominantly using Scala, as a mixed-paradigm language that allows many concepts to be introduced.
        |""".stripMargin,
      """
        |The unit introduces modern functional programming, different type systems, and techniques for asynchronous, streaming, and reactive programming. These techniques are useful at a wide spectrum of scales – from making small problems simple, to modern event-based web systems, to “fast data” systems that process large flows of asynchronous data.
      """.stripMargin
    ),

    outcomes = Seq(
      "analyse how programming languages relate to models of reasoning about a computer program;",
      "write programs in a modern functional programming language;",
      "build systems that use abstractions for asynchronous programming and reactive streams;",
      "understand and apply advanced language features and type systems in the design of programs; and",
      "understand the use of functional programming in practice"
    ),

    taught = Seq(
      AssessmentDescription(
        title = "Lectures",
        percentage = 0,
        lo = Seq.empty,
        cbok = Seq(abs(0), des(0), net(0), prog(0), sys(0))
      ),
      AssessmentDescription(
        title = "Lab exercises",
        percentage = 0,
        lo = Seq.empty,
        cbok = Seq(abs(0), des(0), unde(0), data(0), net(0), prog(0), hf(0), sys(0))
      )
    ),

    assessment = Seq(
      AssessmentDescription(
        title = "Programming and analysis",
        percentage = 10,
        lo = 1 to 5,
        cbok = Seq(abs(3), des(3), prog(3))
      ),
      AssessmentDescription(
        title = "Programming and analysis",
        percentage = 20,
        lo = 1 to 5,
        cbok = Seq(abs(3), des(3), prog(3))
      ),
      AssessmentDescription(
        title = "Programming and analysis",
        percentage = 20,
        lo = 1 to 5,
        cbok = Seq(abs(5), des(5), prog(5), sys(3))
      ),
      AssessmentDescription(
        title = "Self-paced quizzes",
        percentage = 10,
        lo = 1 to 5,
        cbok = Seq(abs(2), des(2), unde(2), prog(2))
      ),
      AssessmentDescription(
        title = "Exam",
        percentage = 40,
        lo = Seq(1, 2, 3, 4, 5),
        cbok = Seq(abs(3), des(4), prog(4))
      )
    )
  )

}
