package units

import info.tweaked.model.unit._
import units.CBOK._


object MTHS110 extends HasUnit {

  val it = new TeachingUnit(
    code = "MTHS110",

    name = Some("Quantitative Skills with Applications"),

    description = Seq(
      """
        |Mathematical models appear throughout the natural and social sciences. For example, growth of both cell populations and money can be modelled using exponential functions. In this unit, students will learn how to develop, understand and apply mathematical models, and see how the universality of mathematics connects seemingly disparate fields. Emphasis is placed on developing good mathematical intuition as well as technical problem-solving, allowing students to immediately apply the knowledge learned in this unit or continue on to the calculus-based mathematics units. This unit follows the Introduction to Quantitative Skills unit or NSW HSC Mathematics or equivalent.
        |""".stripMargin,
      """
        |Topics covered include functions and graphing review; exponential, logarithmic, and inverse functions and equations; trigonometric functions and equations (a unit circle approach); solving systems of linear equations using matrices; and sequences and series: solving first-order recurrence relations, arithmetic progressions, geometric progressions.
      """.stripMargin
    ),

    outcomes = Seq(
      "graph and apply exponential and logarithmic functions;",
      "solve exponential and logarithmic equations;",
      "graph and apply trigonometric functions and solve trigonometric equations;",
      "solve systems of linear equations using matrices;",
      "solve first-order recurrence relations, and",
      "apply arithmetic and geometric sequences and series."
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
        lo = 1 to 6,
        cbok = Seq(abs(3))
      ),
      AssessmentDescription(
        title = "Exam",
        description = Some("Supervised examination"),
        percentage = 60,
        lo = Seq(1, 2, 3, 4, 5, 6),
        cbok = Seq(abs(3))
      )
    )
  )

}
