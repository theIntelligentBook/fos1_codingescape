package units

import info.tweaked.model.unit._
import units.CBOK._

object COSC230 extends HasUnit {

  val it = new TeachingUnit(
    code = "COSC230",

    name = Some("Data Structures and Algorithms"),

    description = Seq(
      """
        | This unit introduces the fundamental data structures used in programming, and builds on campus the theoretical analysis of algorithm efficiency. Topics include: Object-Oriented programming in C++, algorithm complexity analysis, and data-structure implementations, including: linked lists; binary search trees; hash tables; stacks and queues; heaps and expression trees. Recursion and efficient sorting algorithms are also covered. Emphasis is on campus both theory and practical application.
      """.stripMargin
    ),

    outcomes = Seq(
      "write, compile, and run programs in C++ that take advantage of its object-oriented and memory management features;",
      "perform complexity analysis in order to determine the efficiency of a given algorithm;",
      "apply each of the data structures presented in this unit to a range of appropriate programming problems; and",
      "determine which data structure and algorithm pair should be used for the efficient solution of a range of different problems, and understand why this is the case in each instance."
    ),

    taught = Seq(
      AssessmentDescription(
        title = "Lectures",
        percentage = 0,
        lo = Seq.empty,
        cbok = Seq(abs(0), des(0), hw(0), prog(0))
      ),
      AssessmentDescription(
        title = "Lab exercises",
        percentage = 0,
        lo = Seq.empty,
        cbok = Seq(abs(0), des(0), hw(0), prog(0))
      )
    ),

    assessment = Seq(
      AssessmentDescription(
        title = "Programming assignment",
        percentage = 10,
        lo = Seq(1,2,3,4),
        cbok = Seq()
      ),
      AssessmentDescription(
        title = "Programming assignment",
        percentage = 10,
        lo = Seq(1,2,3,4),
        cbok = Seq(abs(4), des(4), hw(4), prog(4))
      ),
      AssessmentDescription(
        title = "Programming assignment",
        percentage = 10,
        lo = Seq(1,2,3,4),
        cbok = Seq(abs(4), des(4), hw(4), prog(4))
      ),
      AssessmentDescription(
        title = "Programming assignment",
        percentage = 10,
        lo = Seq(1,2,3,4),
        cbok = Seq(abs(4), des(4), hw(4), prog(4))
      ),
      AssessmentDescription(
        title = "Exam",
        percentage = 60,
        lo = Seq(1, 2, 4, 5, 6),
        cbok = Seq(abs(4), des(4), hw(4), prog(4))
      )
    )
  )

}
