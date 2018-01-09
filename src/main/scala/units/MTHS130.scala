package units

import info.tweaked.model.unit._
import units.CBOK._


object MTHS130 extends HasUnit {

  val it = new TeachingUnit(
    code = "MTHS130",

    name = Some("Calculus and Linear Algebra 2"),

    description = Seq(
      """
        |In order to model complex systems such as electromagnetic oscillations or the spread of infectious diseases, scientists must apply advanced calculus techniques. This unit couples with the other Calculus and Linear Algebra units and teaches students important methods that can be applied across the natural and social sciences, but also provides a theoretical foundation that allows further study in mathematics.
        |""".stripMargin,
      """
        |Topics covered include linear mappings (determinant and eigenvectors); further techniques of integration and improper integrals; infinite series and Taylor series; parametric equations and implicit equations; and basic differential equations.
      """.stripMargin
    ),

    outcomes = Seq(
      "identify and apply appropriate techniques of integration, such as integration by parts, substitution, and partial fractions, to a range of functions of one variable;",
      "identify and apply appropriate tests for convergence of a range of infinite series, and relate this concept to the power series expansion and approximation of smooth functions;",
      "relate the concept of implicit parametrisation to the loci of a range of plane curves as represented in either cartesian or polar coordinates, and apply this with integration to compute arc-length;",
      "identify and apply appropriate techniques to solve elementary differential equations of first or second order, placed in the context of specific mathematical models from physical and biological science; and",
      "compute determinants, eigenvalues and eigenvectors of matrices in terms of real, or more generally, complex numbers where necessary, and appreciate their significance in mathematical modelling."
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
        lo = 1 to 5,
        cbok = Seq(abs(3))
      ),
      AssessmentDescription(
        title = "Exam",
        description = Some("Supervised examination"),
        percentage = 60,
        lo = Seq(1, 2, 3, 4, 5),
        cbok = Seq(abs(3))
      )
    )
  )

}
