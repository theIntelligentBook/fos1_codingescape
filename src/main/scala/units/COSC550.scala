package units

import info.tweaked.model.plan.Prerequisite.minCP
import info.tweaked.model.unit._
import units.CBOK._


object COSC550 extends HasUnit {

  val it = new TeachingUnit(
    code = "COSC550",

    name = Some("Artificial Intelligence"),

    description = Seq(
      """
        |Artificial intelligence (AI) is a branch of Computer Science that deals with intelligent behaviour, learning, and adaptation in machines. It contributed to the state of the art in many areas, for example speech recognition, machine translation and robotics.
        |""".stripMargin,
      """
        |This unit introduces students to the history and foundations of artificial intelligence. It aims to assist students develop generic AI problem solving skills applicable to a wide-range of real-world problems. Topics are chosen from heuristic search, knowledge representation, logic, reasoning with uncertainty, machine learning and expert systems.
      """.stripMargin
    ),

    outcomes = Seq(
      "explain the history and foundations of artificial intelligence;",
      "apply advanced cognitive skills to analyse problem specifications and derive appropriate solutions for them;",
      "employ advanced technical skills to map solutions to selected artificial intelligence algorithms;",
      "design and implement efficient solutions in a high-level programming language; and",
      "demonstrate autonomy and expert judgement by reporting on a current application of AI."
    ),

    prerequisite = minCP(72), //COSC220 and

    taught = Seq(
      AssessmentDescription(
        title = "Lectures",
        cbok = Seq(abs(0), des(0), hw(0), data(0), prog(0), hf(0))
      ),
      AssessmentDescription(
        title = "Tutorials",
        cbok = Seq(abs(0), des(0), data(0), prog(0))
      )
    ),

    assessment = Seq(
      AssessmentDescription(
        title = "Practical problem solving assignment",
        percentage = 10,
        lo = Seq(1, 2, 3),
        cbok = Seq(abs(3), des(3), data(3), prog(3))
      ),
      AssessmentDescription(
        title = "Practical problem solving assignment",
        percentage = 10,
        lo = Seq(1, 2, 3),
        cbok = Seq(abs(3), des(3), data(3), prog(3))
      ),
      AssessmentDescription(
        title = "Implement the solution to an assignment problem in a high-level programming language",
        percentage = 20,
        lo = Seq(1, 2, 3, 4),
        cbok = Seq(abs(5), des(3), data(5), prog(5))
      ),
      AssessmentDescription(
        title = "Research report",
        percentage = 10,
        lo = Seq(1, 2, 3, 4, 5),
        cbok = Seq(abs(6), des(6), eth(2), prof(2), soci(4), data(6), unde(2), hf(2))
      ),
      AssessmentDescription(
        title = "Exam",
        description = Some("Supervised examination"),
        percentage = 50,
        lo = Seq(1, 2, 3),
        cbok = Seq(abs(3), des(3), data(2), prog(2))
      )
    )
  )

}
