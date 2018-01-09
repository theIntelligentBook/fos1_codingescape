package units

import info.tweaked.model.unit._
import units.CBOK._


object MTHS120 extends HasUnit {

  val it = new TeachingUnit(
    code = "MTHS120",

    name = Some("Calculus and Linear Algebra 1"),

    description = Seq(
      """
        |This unit is designed to serve the needs of students in both the Life Sciences and Engineering. While lectures will be common to both streams, provision will be made to structure specialist tutorials with examples and applications relevant to the different groups.
        |""".stripMargin,
      """
        |Students contemplating enrolment in MTHS120 who are not familiar with the content of Year 12 Mathematics Extension 1, or its equivalent, should contact staff in the School of Science and Technology at mathsenquiries@une.edu.au before enrolling. In your e-mail please state your course of study and your mathematics background.
      """.stripMargin
    ),

    outcomes = Seq(
      "apply the concept of limit to a range of infinite sequences, and relate this to the concept of continuity in the context of specific functions;",
      "relate the concept of derivative to variable rates of change in natural processes, identify and apply appropriate techniques of differentiation to a range of functions, locate and classify maxima and minima of functions, and apply all of these to one-variable problems of optimisation;",
      "relate the concept of definite integral to area, volume and the general accumulation of a quantity prescribed initially in terms of density, compute integrals of functions via elementary anti-differentiation and elementary changes of variable; and",
      "solve systems of linear equations, and classify the solution sets as being either uniquely determined, underdetermined, or overdetermined."
    ),

    taught = Seq(
      AssessmentDescription(
        title = "Lectures",
        cbok = Seq(abs(0))
      ),
      AssessmentDescription(
        title = "Tutorials",
        cbok = Seq(abs(0))
      )
    ),

    assessment = Seq(

      AssessmentDescription(
        title = "Mathematical calculations and problem solving, 9 assignments",
        percentage = 40,
        lo = 1 to 4,
        cbok = Seq(abs(3))
      ),
      AssessmentDescription(
        title = "Exam",
        description = Some("Supervised examination"),
        percentage = 60,
        lo = Seq(1, 2, 3, 4),
        cbok = Seq(abs(3))
      )
    )
  )

}
